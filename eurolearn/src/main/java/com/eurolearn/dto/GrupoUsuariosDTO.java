package com.eurolearn.dto;

import java.util.ArrayList;
import java.util.List;

import com.eurolearn.models.GrupoUsuariosModel;
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
public class GrupoUsuariosDTO {

	private int idGrupo;
	private String nomeGrupo; 
	
	private List<UsuarioModel> usuarios = new ArrayList<UsuarioModel>(); 
	private List<TreinamentoAgendadoModel> treinamentosAgendados = new ArrayList<TreinamentoAgendadoModel>();
	
	public GrupoUsuariosDTO(GrupoUsuariosModel entity) {
		this.idGrupo = entity.getIdGrupo();
		this.nomeGrupo = entity.getNomeGrupo();
		
		for(UsuarioModel usuario : entity.getUsuarios()) {
			this.usuarios.add(usuario);
		}
		
		for(TreinamentoAgendadoModel treinamento : entity.getTreinamentosAgendados()){
			this.treinamentosAgendados.add(treinamento);
		}
		
	}
	
}
