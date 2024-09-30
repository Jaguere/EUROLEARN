package com.eurolearn.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="GRUPOSUSUARIOS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GrupoUsuariosModel {

	//atributos de relacionamento
	@ManyToMany
	@JoinTable(name = "MEMBROSGRUPOS",
	   joinColumns = @JoinColumn(name = "idGrupo"),
	   inverseJoinColumns = @JoinColumn(name = "cpf"))
	private List<UsuarioModel> usuarios;
	
	@ManyToMany
	@JoinTable(name = "GRUPOSMEMBROSTREINAMENTOS",
	   joinColumns = @JoinColumn(name = "idGrupo"),
	   inverseJoinColumns = @JoinColumn(name = "idTreinamentoAgendado"))
	private List<TreinamentoAgendadoModel> treinamentosAgendados;
	
	@Id
	private int idGrupo;
	private String nomeGrupo;
	
}
