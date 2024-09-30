package com.eurolearn.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eurolearn.dto.ConfirmacaoPresencaDTO;
import com.eurolearn.dto.TreinamentoAgendadoDTO;

@Service
public class MonitorPresencaService {

	@Autowired
	private ConfirmacaoPresencaService confirmacaoService;
	
	@Autowired
	private TreinamentoAgendadoService treinamentoService;
	
	public double calcularTaxaPresenca() {
		List<ConfirmacaoPresencaDTO> confirmacoes = confirmacaoService.findAll();
		List<TreinamentoAgendadoDTO> treinamentos = treinamentoService.findAll();
		
		double contadorConfirmacao = confirmacoes.size(); 
		
		double contadorMembro = 0; 
		
		for(TreinamentoAgendadoDTO treinamento : treinamentos) {
			contadorMembro += treinamento.getUsuarios().size();
		}
		
		System.out.println("CÁLCULO DA TAXA DE PRESENÇA => QUANTIDADE DE CONFIRMAÇÕES: =====> "+contadorConfirmacao);
		System.out.println("CÁLCULO DA TAXA DE PRESENÇA => QUANTIDADE DE MEMBROS: =====> "+contadorMembro);
		
		return contadorConfirmacao/contadorMembro;
	}
	
	public List<Integer> getData(){
		List<ConfirmacaoPresencaDTO> confirmacoes = confirmacaoService.findAll();
		List<TreinamentoAgendadoDTO> treinamentos = treinamentoService.findAll();
		
		Integer qtdConfirmacoes = confirmacoes.size(); 
		Integer qtdMembros = 0;
		
		for(TreinamentoAgendadoDTO treinamento : treinamentos) {
			qtdMembros += treinamento.getUsuarios().size();
		}
		
		qtdMembros -= qtdConfirmacoes;
		
		return List.of(qtdConfirmacoes, qtdMembros);
	}
	
	
	
}
