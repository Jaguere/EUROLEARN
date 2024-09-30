package com.eurolearn.dto;

import java.time.LocalDate;

import com.eurolearn.models.ConfirmacaoPresencaModel;
import com.eurolearn.models.TreinamentoAgendadoModel;
import com.eurolearn.models.UsuarioModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConfirmacaoPresencaDTO {
	
	private int idConfirmacao;
	private LocalDate dataConfirmacao;
	private TreinamentoAgendadoModel treinamentoAgendado;
	private UsuarioModel usuario;
	
	public ConfirmacaoPresencaDTO(ConfirmacaoPresencaModel model) {
		this.idConfirmacao = model.getIdConfirmacao();
		this.dataConfirmacao = model.getDataConfirmacao();
		this.treinamentoAgendado = model.getTreinamentoAgendado();
		this.usuario = model.getUsuario();
	}
	
	
}
