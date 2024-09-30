package com.eurolearn.dto;

import java.util.ArrayList;
import java.util.List;

import com.eurolearn.models.TipoTreinamentoModel;
import com.eurolearn.models.TreinamentoAgendadoModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TipoTreinamentoDTO {
	
	private int idTipoTreinamento;
	
	private String nome; 
	
	private String descricao; 
	
	private String topico;
	
	private List<TreinamentoAgendadoModel> treinamentosAgendados = new ArrayList<>();
	
	public TipoTreinamentoDTO(TipoTreinamentoModel entity) {
		this.idTipoTreinamento = entity.getIdTipoTreinamento();
		this.nome = entity.getNome();
		this.descricao = entity.getDescricao();
		this.topico = entity.getTopico();
		
//		for(TreinamentoAgendadoModel treinamentoAgendado : entity.getTreinamentosAgendados()) {
//			this.treinamentosAgendados.add(treinamentoAgendado);
//		}
	}
}
