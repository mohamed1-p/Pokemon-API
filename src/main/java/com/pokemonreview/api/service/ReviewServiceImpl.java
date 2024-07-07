package com.pokemonreview.api.service;

import java.util.List;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pokemonreview.api.dto.ReviewDto;
import com.pokemonreview.api.exception.PokemonNotFoundException;
import com.pokemonreview.api.exception.ReviewNotFoundException;
import com.pokemonreview.api.models.Pokemon;
import com.pokemonreview.api.models.Review;
import com.pokemonreview.api.repo.PokemonRepo;
import com.pokemonreview.api.repo.ReviewRepo;

@Service
public class ReviewServiceImpl implements ReviewService {
	
	private ReviewRepo reviewRepo;
	private PokemonRepo pokemonRepo;
	
	
	@Autowired
	public ReviewServiceImpl(ReviewRepo reviewRepo,
			PokemonRepo pokemonRepo) {
		this.pokemonRepo=pokemonRepo;
		this.reviewRepo=reviewRepo;
	}

	@Override
	public ReviewDto createReview(int pokemonId, ReviewDto reviewDto) {
		Review review =mapToEntity(reviewDto);
		Pokemon pokemon = pokemonRepo.findById(pokemonId).orElseThrow(()->
		new PokemonNotFoundException("pokemon not found at idddd"));
		
		review.setPokemon(pokemon);
		
		Review newReview = reviewRepo.save(review);
		return mapToDto(newReview);
	}

	@Override
	public List<ReviewDto> getReviewByPokemonId(int id) {
		Pokemon pokemon = pokemonRepo.findById(id).orElseThrow(()->
		new PokemonNotFoundException("pokemon not found at this idd"));
		List<Review> reviews = reviewRepo.findByPokemonId(id);
		if(reviews.isEmpty()) {
			throw new ReviewNotFoundException("the Pokemon doesn't have reviews");
		}
		return reviews.stream().map(review -> mapToDto(review)).
				collect(Collectors.toList());
	}

	
	@Override
	public ReviewDto getReviewById(int id, int pokemonId) {
		Pokemon pokemon = pokemonRepo.findById(pokemonId).orElseThrow(()->
		new PokemonNotFoundException("pokemon not found at this idd"));
		
		Review review = reviewRepo.findById(id).orElseThrow(()->new 
		ReviewNotFoundException("the review you entered doesn't exist"));
		
		if(review.getPokemon().getId() != pokemon.getId()) {
			throw new ReviewNotFoundException("the id doesn't match");
		}
		return mapToDto(review);
	}
	
	
	@Override
	public ReviewDto updateReviewById(int id, int pokemonId,
			ReviewDto reviewDto) {
		
		Review review = reviewRepo.findById(id).orElseThrow(()
			-> new ReviewNotFoundException("the review doesn't exist"));
		
		if(pokemonId != review.getPokemon().getId()) {
			throw new PokemonNotFoundException("the pokemon doesn't exist");
		}
		else 
		{
		review.setContent(reviewDto.getContent());
		review.setTitle(reviewDto.getTitle());
		review.setStars(reviewDto.getStars());
		}
		
		Review updateReview = reviewRepo.save(review);
		
		return mapToDto(updateReview);
	}
	
	@Override
	public void deleteReview(int pokemonId, int id){
		Review review = reviewRepo.findById(id).orElseThrow(()
				-> new ReviewNotFoundException("the review doesn't exist"));
			
		if(pokemonId != review.getPokemon().getId()) {
			throw new PokemonNotFoundException("the pokemon doesn't exist");
		}
		
		reviewRepo.delete(review);
	}
	
	
	
	
	private ReviewDto mapToDto(Review review) {
		ReviewDto reviewDto = new ReviewDto();
		reviewDto.setId(review.getId());
		reviewDto.setTitle(review.getTitle());
		reviewDto.setContent(review.getContent());
		reviewDto.setStars(review.getStars());
		
		return reviewDto;
	}
	
	
	private Review mapToEntity(ReviewDto reviewDto) {
		Review review= new Review();
		review.setId(reviewDto.getId());
		review.setContent(reviewDto.getContent());
		review.setTitle(reviewDto.getTitle());
		review.setStars(reviewDto.getStars());
		return review;
	}

	

	

}
