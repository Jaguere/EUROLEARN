package com.eurolearn.models;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "TREINAMENTOSAGENDADOS")
public class TreinamentoAgendadoModel {
	
	//atributos de relacionamento
	@ManyToOne
	@JoinColumn(name = "idTipoTreinamento")
	private TipoTreinamentoModel tipoTreinamento;
	
//	@OneToMany(mappedBy="treinamentoAgendado")
//	private List<AlertaModel> alertas;
//	
//	@OneToMany(mappedBy="treinamentoAgendado")
//	private List<JustificativaNaoConfirmacaoModel> justificativas;
	
	@OneToMany(mappedBy="treinamentoAgendado")
	private List<ConfirmacaoPresencaModel> confirmacoes;
	
	//descomentar quando implementar os treinamentos agendados
//	@OneToMany(mappedBy="treinamentoAgendado")
//	private List<FeedbackModel> feedbacks;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "MEMBROSTREINAMENTO",
			   joinColumns = @JoinColumn(name = "idTreinamentoAgendado"),
			   inverseJoinColumns = @JoinColumn(name = "cpf"))
	private List<UsuarioModel> usuarios;
	
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "GRUPOSMEMBROSTREINAMENTOS",
			   joinColumns = @JoinColumn(name = "idTreinamentoAgendado"),
			   inverseJoinColumns = @JoinColumn(name = "idGrupo"))
	private List<GrupoUsuariosModel> grupos;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTreinamentoAgendado;
	
	private String nome;
	private Date data;
	private String localizacao;
	
	
}
