package com.pokemonreview.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.pokemonreview.api.models.Pokemon;

@RepositoryRestResource
public interface PokemonRepo extends JpaRepository<Pokemon, Integer> {

}
