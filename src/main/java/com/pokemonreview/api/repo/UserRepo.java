package com.pokemonreview.api.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pokemonreview.api.models.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, Integer> {

	Optional<UserEntity> findByUserName(String userName);
	Boolean existsByUserName(String Username);
}
