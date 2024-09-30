package com.eurolearn.models;

import java.time.LocalDate;

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

@Entity
@Table(name="CONFIRMACOESPRESENCA")
@Getter
@Setter
@AllArgsConstructor 
@NoArgsConstructor
public class ConfirmacaoPresencaModel {

	//atributos de relacionamento
	@ManyToOne()
	@JoinColumn(name="idTreinamentoAgendado", nullable=true)
	private TreinamentoAgendadoModel treinamentoAgendado;
	
	@ManyToOne()
	@JoinColumn(name="cpf", nullable=true)
	private UsuarioModel usuario;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idConfirmacao;
	
	private LocalDate dataConfirmacao; 
	
	
}
