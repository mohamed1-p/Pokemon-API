package com.pokemonreview.api.service;



import com.pokemonreview.api.dto.PokemonDto;
import com.pokemonreview.api.dto.PokemonResponse;

public interface PokemonService {
	
	PokemonDto createPokemon(PokemonDto pokemonDto);
	PokemonResponse getAllPokemon(int PageNo, int PageSize);
	PokemonDto getPokemonById(int id);
	PokemonDto updatePokemon(PokemonDto pokemonDto, int id);
	void deletePokemon(int id );
}
