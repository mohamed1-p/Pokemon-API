package com.pokemonreview.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pokemonreview.api.dto.PokemonDto;
import com.pokemonreview.api.dto.PokemonResponse;
import com.pokemonreview.api.models.Pokemon;
import com.pokemonreview.api.service.PokemonServiceImpl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api")
public class PokemonController {

	private PokemonServiceImpl pokemonService;
	
	
	@Autowired
	public PokemonController(PokemonServiceImpl pokemonService) {
		this.pokemonService = pokemonService;
	}

	@GetMapping("/poke")
	 public ResponseEntity<PokemonResponse> getPokemon(
	@RequestParam(value = "pageNo", defaultValue = "0",
	required = false)int pageNo,
	@RequestParam(value="pageSize",defaultValue = "10",
	required = false)int pageSize) {
		
	        return new ResponseEntity<>(pokemonService.
	        		getAllPokemon(pageNo,pageSize),HttpStatus.OK);
	 }
	
	@GetMapping("/poke/{id}")
	public ResponseEntity<PokemonDto> getPoke(@PathVariable int id) {
		return  new ResponseEntity<>(pokemonService.getPokemonById(id)
				,HttpStatus.OK);
	}
	
	@PostMapping("poke/create")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<PokemonDto> createPokemon(
			@RequestBody PokemonDto pokee) {
		
		
		return new ResponseEntity<>(pokemonService.createPokemon(pokee)
									,HttpStatus.CREATED);
	}
	
	
	@PutMapping("poke/{id}/update")
	public ResponseEntity<PokemonDto> putPokemon(
			@PathVariable("id") int id,
			@RequestBody PokemonDto pokemonDto) {
		PokemonDto response = pokemonService.updatePokemon(pokemonDto, id);
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("poke/{id}/delete")
	public ResponseEntity<String> deletePokemon(
			@PathVariable("id") int id){
		
		pokemonService.deletePokemon(id);
				
		return ResponseEntity.ok("the delete operation is done ");
		
	}
	
	
	
}
