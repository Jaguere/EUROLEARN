package com.eurolearn.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eurolearn.dto.FeedbackDTO;
import com.eurolearn.models.FeedbackModel;
import com.eurolearn.repository.FeedbackRepository;

@Service
public class FeedbackService {

	@Autowired
	private FeedbackRepository repository;
	
	@Transactional(readOnly = true)
	public List<FeedbackDTO> findAll(){
		List<FeedbackDTO> list = new ArrayList<FeedbackDTO>();
		
		for(FeedbackModel model : repository.findAll()) {
			list.add(new FeedbackDTO(model));
		}
		
		return list;
	}
	
	@Transactional(readOnly = true)
	public FeedbackDTO findById(int id) {
		return new FeedbackDTO(repository.findById(id).orElseThrow(
				() -> new IllegalArgumentException("Recurso não encontrado - id"+id)));
	}
	
	@Transactional
	public FeedbackDTO insert(FeedbackDTO dto) {
		FeedbackModel model = new FeedbackModel(); 
		copyDtoToEntity(dto, model);
		return new FeedbackDTO(repository.save(model));
	}
	
	@Transactional
	public FeedbackDTO update(int id, FeedbackDTO dto) {
		try {
			FeedbackModel entity = repository.getReferenceById(id);
			copyDtoToEntity(dto, entity);
			return new FeedbackDTO(repository.save(entity));
		} catch (Exception e) {
			throw new IllegalArgumentException("Recurso não encontrado - id"+id);
		}
	}
	
	@Transactional
	public void delete(int id) {
		if(!repository.existsById(id)) {
			throw new IllegalArgumentException("Recurso não existe - id"+id);
		}
		repository.deleteById(id);
	}
	
	
	private void copyDtoToEntity(FeedbackDTO dto, FeedbackModel model) {
		model.setIdFeedback(dto.getIdFeedback());
		model.setDataFeedback(dto.getDataFeedback());
		model.setIndiceSatisfacao(dto.getIndiceSatisfacao());
		model.setUsuario(dto.getUsuario());
	}
	
	
	
}
