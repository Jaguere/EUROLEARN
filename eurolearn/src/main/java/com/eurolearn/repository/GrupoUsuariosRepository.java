package com.eurolearn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eurolearn.models.GrupoUsuariosModel;

public interface GrupoUsuariosRepository extends JpaRepository<GrupoUsuariosModel, Integer> {

}
