package com.eurolearn.models;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name="JUSTIFICATIVASNAOCONFIRMACAO")
public class JustificativaNaoConfirmacaoModel {

	//atributos de relacionamento
	@ManyToOne()
	@JoinColumn(name="idTreinamentoAgendado", nullable = true)
	private TreinamentoAgendadoModel treinamentoAgendado;
	
	@ManyToOne()
	@JoinColumn(name = "cpf", nullable = true)
	private UsuarioModel usuario;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idJustificativa; 
	private String motivoJustificativa;
	private Date dataJustificativa;
	
	
	
}
