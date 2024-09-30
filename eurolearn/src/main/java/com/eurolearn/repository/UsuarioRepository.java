package com.eurolearn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eurolearn.models.UsuarioModel;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {
	
	  List<UsuarioModel> findByNome(String nome);
	  
	  List<UsuarioModel> findByCpf(long cpf);
	
	  List<UsuarioModel> findByEmail(String email);
}
