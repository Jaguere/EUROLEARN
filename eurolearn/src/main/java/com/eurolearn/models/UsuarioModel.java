package com.eurolearn.models;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@Table(name = "USUARIOS")
public class UsuarioModel {
	
	//atributos de relacionamento
	@OneToMany(mappedBy="usuario")
	private List<AlertaModel> alertas;
	
	@OneToMany(mappedBy="usuario")
	private List<JustificativaNaoConfirmacaoModel> justificativas;
	
	@OneToMany(mappedBy="usuario")
	private List<ConfirmacaoPresencaModel> confirmacoes;
	
//	@OneToMany(mappedBy="usuario")
//	private List<FeedbackModel> feedbacks;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "MEMBROSGRUPOS",
			   joinColumns = @JoinColumn(name = "cpf"),
			   inverseJoinColumns = @JoinColumn(name = "idGrupo"))
	private List<GrupoUsuariosModel> grupos;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "MEMBROSTREINAMENTOS",
			   joinColumns = @JoinColumn(name = "cpf"),
			   inverseJoinColumns = @JoinColumn(name = "idTreinamentoAgendado"))
	private List<TreinamentoAgendadoModel> treinamentosAgendados;
	
//	@OneToOne()
//	@JoinColumn(name = "idProfissional", nullable = true)
//	private UsuarioProfissionalModel usuarioProfissional;
//	
//	@OneToOne()
//	@JoinColumn(name = "idAutenticacao", nullable = true)
//	private UsuarioAutenticacaoModel usuarioAutenticacao;
	
	
	//atributos da entidade
	@Id
	private long cpf;
	
	private long rg;
	private String nome;
	private String sobrenome;
	private String genero;
	private String email;
	private Date dataNasc;
	
	//informações dos outros models (previously Profissional e Aut)
	private String senha;
	private Date dataUltimaSenha;
	private String nivelAcesso;
	
	private String cargo;
	private String setor;
	private Date dataAdmissao;
	
}
