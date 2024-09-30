package com.eurolearn.dto;

import java.sql.Date;

import com.eurolearn.models.FeedbackModel;
import com.eurolearn.models.UsuarioModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackDTO {

	private int idFeedback;
	private double indiceSatisfacao;
	private Date dataFeedback;
	
	private UsuarioModel usuario;
	
	//descomentar quanto tiver o treinamento agendado implementado
	//private TreinamentoAgendadoModel treinamentoAgendado 
	
	public FeedbackDTO(FeedbackModel model) {
		this.idFeedback = model.getIdFeedback();
		this.indiceSatisfacao = model.getIndiceSatisfacao();
		this.dataFeedback = model.getDataFeedback();
		
		this.usuario = model.getUsuario();
	}
	
	
	
	
	
}
