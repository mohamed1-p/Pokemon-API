package com.pokemonreview.api.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="users")
@Data
@NoArgsConstructor
public class UserEntity {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private int id;
	private String userName;
	private String password;
	
	@ManyToMany
	@JoinTable(name = "user_roles", joinColumns= @JoinColumn
			  (name = "user_id",referencedColumnName = "id"),
			  inverseJoinColumns = @JoinColumn(name = "Role_id",
			  referencedColumnName = "id"))
	private List<RolesEntity> roles = new ArrayList<>();

}
