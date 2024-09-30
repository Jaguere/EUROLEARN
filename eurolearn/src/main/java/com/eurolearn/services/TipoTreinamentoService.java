package com.eurolearn.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eurolearn.dto.TipoTreinamentoDTO;
import com.eurolearn.models.TipoTreinamentoModel;
import com.eurolearn.repository.TipoTreinamentoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TipoTreinamentoService {

	@Autowired
	private TipoTreinamentoRepository TTRepository;
	
	@Transactional(readOnly = true)
	public List<TipoTreinamentoDTO> findAll() {
		List<TipoTreinamentoModel> list = TTRepository.findAll();
		return list.stream().map(TipoTreinamentoDTO::new).collect(Collectors.toList());
	}
	
	@Transactional
	public TipoTreinamentoDTO insert(TipoTreinamentoDTO dto) {
		TipoTreinamentoModel entity = new TipoTreinamentoModel();
		copyDtoToEntity(dto, entity);
		entity = TTRepository.save(entity);
		return new TipoTreinamentoDTO(entity);
	}
	
    @Transactional(readOnly = true)
    public TipoTreinamentoDTO findById(int id) {

    	TipoTreinamentoModel tipoTreinamento = TTRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Recurso inválido - " + id)
        );
        return new TipoTreinamentoDTO(tipoTreinamento);
    }
	
	@Transactional
	public TipoTreinamentoDTO update(int id, TipoTreinamentoDTO dto) {
		try {
			TipoTreinamentoModel tipoTreinamento = TTRepository.getReferenceById(id);
			copyDtoToEntity(dto, tipoTreinamento);
			tipoTreinamento = TTRepository.save(tipoTreinamento);
			return new TipoTreinamentoDTO(tipoTreinamento);
		} catch (EntityNotFoundException e) {
			throw new IllegalArgumentException("Recurso não encontrado"); 
		}
	}
	
	@Transactional
	public void delete(int id) {
		if(!TTRepository.existsById(id)) {
			throw new IllegalArgumentException("Tipo de treinamento inválido - id: "+id);
		}
		try {
			TTRepository.deleteById(id);
		} catch (Exception e) {
			throw new IllegalArgumentException("Tipo de treinamento inválido - id: "+id);
		}
	}
	
	
	
	private void copyDtoToEntity(TipoTreinamentoDTO dto, TipoTreinamentoModel entity) {
		entity.setDescricao(dto.getDescricao());
		entity.setNome(dto.getNome());
		entity.setTopico(dto.getTopico());
		
		//falta adicionar o código que pega a lista de elementos associados na dto e passa pra entity.
	}
	
}
