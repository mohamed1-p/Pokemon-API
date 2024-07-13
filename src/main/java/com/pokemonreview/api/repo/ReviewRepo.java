package com.pokemonreview.api.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.pokemonreview.api.models.Review;


@RepositoryRestResource
public interface ReviewRepo extends JpaRepository<Review,Integer>{

	List<Review> findByPokemonId(int Pokemonid);
	
	//querynative
	
}
