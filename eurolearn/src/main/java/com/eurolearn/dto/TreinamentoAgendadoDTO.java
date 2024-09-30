package com.eurolearn.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.eurolearn.models.ConfirmacaoPresencaModel;
import com.eurolearn.models.GrupoUsuariosModel;
import com.eurolearn.models.TipoTreinamentoModel;
import com.eurolearn.models.TreinamentoAgendadoModel;
import com.eurolearn.models.UsuarioModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TreinamentoAgendadoDTO {

	private int idTreinamentoAgendado;
	private String nome;
	private Date data;
	private String localizacao;
	
	private TipoTreinamentoModel tipoTreinamento;
	
	private List<ConfirmacaoPresencaModel> confirmacoes = new ArrayList<>();
	
	private List<UsuarioModel> usuarios  = new ArrayList<>();
	
	private List<GrupoUsuariosModel> grupos  = new ArrayList<>();
	
	public TreinamentoAgendadoDTO(TreinamentoAgendadoModel entity) {
		this.idTreinamentoAgendado = entity.getIdTreinamentoAgendado();
		this.nome = entity.getNome();
		this.data = entity.getData();
		this.localizacao = entity.getLocalizacao();
		
		this.tipoTreinamento = entity.getTipoTreinamento();
		
		for(ConfirmacaoPresencaModel confirmacao : entity.getConfirmacoes()) {
			this.confirmacoes.add(confirmacao);
		}
		
		for(UsuarioModel usuario : entity.getUsuarios()) {
			this.usuarios.add(usuario);
		}
		
		for(GrupoUsuariosModel grupo : entity.getGrupos()) {
			this.grupos.add(grupo);
		}
		
		//adicionar para os atributos restantes que estão comentados na classe Model
	}
	
}
