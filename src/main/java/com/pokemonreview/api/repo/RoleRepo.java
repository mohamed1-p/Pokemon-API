package com.pokemonreview.api.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pokemonreview.api.models.RolesEntity;


public interface RoleRepo extends JpaRepository<RolesEntity, Integer>{

	Optional<RolesEntity> findByName(String name);
}
