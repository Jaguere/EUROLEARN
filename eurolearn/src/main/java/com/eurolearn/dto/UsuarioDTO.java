package com.eurolearn.dto;

import java.sql.Date;

import com.eurolearn.models.UsuarioModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuarioDTO {

	private long cpf;
	private long rg; 
	private String nome;
	private String sobrenome;
	private String genero;
	private String email; 
	private Date dataNasc;
	
	private String senha;
	private Date dataUltimaSenha;
	private String nivelAcesso; 
	
	private String cargo;
	private String setor;
	private Date dataAdmissao;
	
	
	//faltam as outras entidades associadas
	
	public UsuarioDTO(UsuarioModel entity) {
		this.cpf = entity.getCpf();
		this.rg = entity.getRg();
		this.nome = entity.getNome();
		this.sobrenome = entity.getSobrenome();
		this.genero = entity.getGenero();
		this.email = entity.getEmail();
		this.dataNasc = entity.getDataNasc();
		
		this.senha = entity.getSenha();
		this.dataUltimaSenha = entity.getDataUltimaSenha();
		this.nivelAcesso = entity.getNivelAcesso();
		
		this.cargo = entity.getCargo();
		this.setor = entity.getSetor();
		this.dataAdmissao = entity.getDataAdmissao();
		
	}
}
