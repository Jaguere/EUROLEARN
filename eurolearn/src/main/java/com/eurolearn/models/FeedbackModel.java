package com.eurolearn.models;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="FEEDBACKS")
@Getter
@Setter
public class FeedbackModel {
	
	//atributos de relacionamento
	
	//descomentar quando tiver o treinamento agendado feito
//	@ManyToOne
//	@JoinColumn(name="idTreinamentoAgendado", nullable = true)
//	private TreinamentoAgendadoModel treinamentoAgendado;
	
	@ManyToOne
	@JoinColumn(name="cpf", nullable = true)
	private UsuarioModel usuario;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idFeedback;
	
	private double indiceSatisfacao;
	private Date dataFeedback;
	
}
