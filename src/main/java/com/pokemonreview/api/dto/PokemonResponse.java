package com.pokemonreview.api.dto;

import java.util.List;

import lombok.Data;

@Data
public class PokemonResponse {

	private List<PokemonDto> content;
	private int pageNumber;
	private int pageSize;
	private long totalElements;
	private int totalPages;
	private boolean last;
}
