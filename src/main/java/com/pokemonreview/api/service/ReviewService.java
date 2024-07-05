package com.pokemonreview.api.service;

import java.util.List;

import com.pokemonreview.api.dto.ReviewDto;

public interface ReviewService {
	
	ReviewDto createReview(int pokemonId, ReviewDto reviewDto);
	List<ReviewDto> getReviewByPokemonId(int id);
	ReviewDto getReviewById(int id , int pokemonId);
	ReviewDto updateReviewById(int id, int pokemonId, ReviewDto reviewDto);
	void deleteReview(int pokemonId, int id);
}
