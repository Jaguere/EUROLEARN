package com.eurolearn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eurolearn.models.TipoTreinamentoModel;

public interface TipoTreinamentoRepository extends JpaRepository<TipoTreinamentoModel, Integer> {

}
