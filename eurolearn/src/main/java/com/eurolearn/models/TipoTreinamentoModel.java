package com.eurolearn.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name="TIPOSTREINAMENTO")

public class TipoTreinamentoModel {
	
	//atributos de relacionamento 
//	@OneToMany(mappedBy="tipoTreinamento")
//	private List<TreinamentoAgendadoModel> treinamentosAgendados;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTipoTreinamento;
	
	private String nome; 
	private String descricao; 
	private String topico;
	
}
