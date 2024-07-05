package com.pokemonreview.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pokemonreview.api.dto.ReviewDto;
import com.pokemonreview.api.service.ReviewService;
import com.pokemonreview.api.service.ReviewServiceImpl;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api/")
public class ReviewController {

	private ReviewServiceImpl reviewService;
	
	@Autowired
	public ReviewController(ReviewServiceImpl reviewService) {
		this.reviewService=reviewService;
	}
	
	
	@PostMapping("pokemon/{pokemonId}/review")
	public ResponseEntity<ReviewDto> createReview(@PathVariable(value =
	"pokemonId") int pokemonId, @RequestBody ReviewDto reviewDto) {
		
	return ResponseEntity.ok(reviewService.createReview(pokemonId,
							 reviewDto));
	}
	
	
	@GetMapping("pokemon/{pokemonId}/reviews")
	public List<ReviewDto> getReviewByPokemonId(@PathVariable(value = 
											"pokemonId") int pokemonId) {
		return reviewService.getReviewByPokemonId(pokemonId);
	}
	
	@GetMapping("pokemon/{pokemonId}/review/{id}")
	public ResponseEntity<ReviewDto> getReviewById(@PathVariable(value =
			"pokemonId") int pokemonId,@PathVariable(value =
					"id") int Id ) {
				
			return ResponseEntity.ok(reviewService.getReviewById(
					Id, pokemonId));
			}
	
	@PutMapping("pokemon/{pokemonId}/review/{id}/update")
	public ResponseEntity<ReviewDto> updateReviewById(
			@PathVariable(value ="pokemonId") int pokemonId,
			@PathVariable(value ="id") int Id,
			@RequestBody ReviewDto review) {
		
		return ResponseEntity.ok(reviewService.
				updateReviewById(Id, pokemonId, review));
	}
	
	@DeleteMapping("pokemon/{pokemonId}/review/{id}/delete")
	public ResponseEntity<String> updateReviewById(
			@PathVariable(value ="pokemonId") int pokemonId,
			@PathVariable(value ="id") int Id) {
		reviewService.deleteReview(pokemonId, Id);
		return ResponseEntity.ok("The review has been deleted succesfully ");
	}
	
	
	
	
	
}
