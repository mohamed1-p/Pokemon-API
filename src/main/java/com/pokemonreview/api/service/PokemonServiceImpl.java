package com.pokemonreview.api.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pokemonreview.api.dto.PokemonDto;
import com.pokemonreview.api.dto.PokemonResponse;
import com.pokemonreview.api.exception.PokemonNotFoundException;
import com.pokemonreview.api.models.Pokemon;
import com.pokemonreview.api.repo.PokemonRepo;

@Service
public class PokemonServiceImpl implements PokemonService {
	
	private PokemonRepo pokemonRepo;
	
	@Autowired
	public PokemonServiceImpl(PokemonRepo pokemonRepo) {
		this.pokemonRepo =  pokemonRepo;
	}

	@Override
	public PokemonDto createPokemon(PokemonDto pokemonDto) {
		
		Pokemon pokemon = new Pokemon();
		pokemon.setName(pokemonDto.getName());
		pokemon.setType(pokemonDto.getType());
		
		Pokemon newPokemon = pokemonRepo.save(pokemon);
		
		PokemonDto pokemonResponse = new PokemonDto();
		pokemonResponse.setId(newPokemon.getId());
		pokemonResponse.setName(newPokemon.getName());
		pokemonResponse.setType(newPokemon.getType());
		
		
		return pokemonResponse;
	}
	

	@Override
	public PokemonResponse getAllPokemon(int PageNo, int PageSize) {

		Pageable pageable =PageRequest.of(PageNo, PageSize);
		Page<Pokemon> pokemons = pokemonRepo.findAll(pageable);
		
		List<Pokemon> listOfPokemon= pokemons.getContent();
		List<PokemonDto> content = listOfPokemon.stream().map(p-> 
								mapToDto(p)).collect(Collectors.toList());
		PokemonResponse pokemonResponse = new PokemonResponse();
		pokemonResponse.setContent(content);
		pokemonResponse.setPageNumber(pokemons.getNumber());
		pokemonResponse.setPageSize(pokemons.getSize());
		pokemonResponse.setTotalElements(pokemons.getTotalElements());
		pokemonResponse.setTotalPages(pokemons.getTotalPages());
		pokemonResponse.setLast(pokemons.isLast());
		return pokemonResponse;
		
	}
	
	
	@Override
	public PokemonDto getPokemonById(int id) {
	Pokemon pokemon = pokemonRepo.findById(id).orElseThrow(()->
	new PokemonNotFoundException("Wrong Id, couldn't find Pokemon"));
	
		return mapToDto(pokemon);
	}
	
	
	
	@Override
	public PokemonDto updatePokemon(PokemonDto pokemonDto, int id) {
		Pokemon pokemon = pokemonRepo.findById(id).orElseThrow(()->
		new PokemonNotFoundException("Wrong Id, couldn't Update Pokemon"));
		
		pokemon.setName(pokemonDto.getName());
		pokemon.setType(pokemonDto.getType());
		
		Pokemon updatePokemon = pokemonRepo.save(pokemon);
		
		return mapToDto(updatePokemon);
	}

	
	@Override
	public void deletePokemon(int id) {
		Pokemon pokemon = pokemonRepo.findById(id).orElseThrow(()->
		new PokemonNotFoundException("Wrong Id, couldn't delete Pokemon"));
		pokemonRepo.delete(pokemon);
		
	}
	
	
	
	private PokemonDto mapToDto(Pokemon pokemon) {
		PokemonDto pokemonDto = new PokemonDto();
		pokemonDto.setId(pokemon.getId());
		pokemonDto.setName(pokemon.getName());
		pokemonDto.setType(pokemon.getType());
		
		return pokemonDto;
	}
	
	
	private Pokemon mapToEntity(PokemonDto pokemonDto) {
		Pokemon pokemon = new Pokemon();
		pokemon.setName(pokemonDto.getName());
		pokemon.setType(pokemonDto.getType());
		return pokemon;
	}

	

	
	
	

}
