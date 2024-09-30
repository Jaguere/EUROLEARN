package com.eurolearn.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eurolearn.dto.ConfirmacaoPresencaDTO;
import com.eurolearn.models.ConfirmacaoPresencaModel;
import com.eurolearn.models.TreinamentoAgendadoModel;
import com.eurolearn.repository.ConfirmacaoPresencaRepository;
import com.eurolearn.repository.TreinamentoAgendadoRepository;
import com.eurolearn.repository.UsuarioRepository;

@Service
public class ConfirmacaoPresencaService {
	
	@Autowired
	private ConfirmacaoPresencaRepository repository;
	
	@Autowired
	private UsuarioRepository userRepository;
	
	@Autowired
	private TreinamentoAgendadoRepository treinamentoRepository;
	
	public List<ConfirmacaoPresencaDTO> findAll(){
		List<ConfirmacaoPresencaDTO> list = new ArrayList<ConfirmacaoPresencaDTO>();
		for(ConfirmacaoPresencaModel entity : repository.findAll()) {
			list.add(new ConfirmacaoPresencaDTO(entity));
		}
		return list;
	}
	
	public ConfirmacaoPresencaDTO findById(int idConfirmacao) {
		return new ConfirmacaoPresencaDTO(repository.findById(idConfirmacao).orElseThrow(() -> new IllegalArgumentException("Recurso não encontrado! - ID:"+idConfirmacao) ));
	}
	
	public ConfirmacaoPresencaDTO insert(ConfirmacaoPresencaDTO dto, int idTreinamentoAgendado) {
		
		ConfirmacaoPresencaModel entity = new ConfirmacaoPresencaModel();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		
		TreinamentoAgendadoModel treinamentoEntity = treinamentoRepository.findById(idTreinamentoAgendado).orElseThrow(null);
		treinamentoEntity.getConfirmacoes().add(entity);
		treinamentoEntity = treinamentoRepository.save(treinamentoEntity);
	
		return new ConfirmacaoPresencaDTO(entity);
	}
	
	
	
	public void copyDtoToEntity(ConfirmacaoPresencaDTO dto, ConfirmacaoPresencaModel entity) {
		entity.setIdConfirmacao(dto.getIdConfirmacao());
		entity.setDataConfirmacao(dto.getDataConfirmacao());
		entity.setTreinamentoAgendado(dto.getTreinamentoAgendado());
		entity.setUsuario(dto.getUsuario());
	}
	
}
