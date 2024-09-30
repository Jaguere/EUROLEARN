package com.eurolearn.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eurolearn.dto.TreinamentoAgendadoDTO;
import com.eurolearn.models.TreinamentoAgendadoModel;
import com.eurolearn.models.UsuarioModel;
import com.eurolearn.repository.TreinamentoAgendadoRepository;
import com.eurolearn.repository.UsuarioRepository;

@Service
public class TreinamentoAgendadoService {

	@Autowired
	private TreinamentoAgendadoRepository repository;
	
	@Autowired
	private UsuarioRepository userRepository;
	
	public List<TreinamentoAgendadoDTO> findAll(){
		
		List<TreinamentoAgendadoDTO> results = new ArrayList<>();
		
		for(TreinamentoAgendadoModel entity : repository.findAll()) {
			results.add(new TreinamentoAgendadoDTO(entity));
		}
		
		return results;
	}
	
	public TreinamentoAgendadoDTO findById(int idTreinamentoAgendado) {
		return new TreinamentoAgendadoDTO(repository.findById(idTreinamentoAgendado).orElseThrow(() -> new IllegalArgumentException()));
	}
	
	public TreinamentoAgendadoDTO insert(TreinamentoAgendadoDTO dto) {
		TreinamentoAgendadoModel entity = new TreinamentoAgendadoModel();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new TreinamentoAgendadoDTO(entity);
	}
	
	public TreinamentoAgendadoDTO insertUserIntoTreinamento(int idTreinamentoAgendado, long idUser) {
		
		List<UsuarioModel> usuarioEntityList = userRepository.findByCpf(idUser);
		
		TreinamentoAgendadoModel treinamentoEntity = repository.findById(idTreinamentoAgendado).orElseThrow(() -> new IllegalArgumentException("Treinamento não encontrado!"));
		
		for(UsuarioModel usuarioEntity : usuarioEntityList) {
			treinamentoEntity.getUsuarios().add(usuarioEntity);
		}
		
		return new TreinamentoAgendadoDTO(repository.save(treinamentoEntity));
	}
	
	@Transactional
	public TreinamentoAgendadoDTO update(int idTreinamentoAgendado, TreinamentoAgendadoDTO dto) {
		try {
			TreinamentoAgendadoModel entity = repository.getReferenceById(idTreinamentoAgendado);
			
			System.out.println("=========================TESTE DO UPDATE=================");
			System.out.println("VALOR DO IDTREINAMENTOAGENDADO NA ENTITY QUE VEIO DO BANCO! "+entity.getIdTreinamentoAgendado());
			
			updateCopyDtoToEntity(dto, entity);
			
			System.out.println("VALOR DO IDTREINAMENTOAGENDADO NA ENTITY DEPOIS DO COPY! "+entity.getIdTreinamentoAgendado());
			
			entity = repository.save(entity);
			return new TreinamentoAgendadoDTO(entity);
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	public void delete(int idTreinamentoAgendado) {
		if(repository.existsById(idTreinamentoAgendado)) {
			repository.deleteById(idTreinamentoAgendado);
		} else {
			throw new IllegalArgumentException("Recurso não existe - ID:"+idTreinamentoAgendado);
		}
	}
	
	public static void copyDtoToEntity(TreinamentoAgendadoDTO dto, TreinamentoAgendadoModel entity) {
		entity.setIdTreinamentoAgendado(dto.getIdTreinamentoAgendado());
		entity.setNome(dto.getNome());
		entity.setData(dto.getData());
		entity.setLocalizacao(dto.getLocalizacao());
		
		entity.setTipoTreinamento(dto.getTipoTreinamento());
		entity.setConfirmacoes(dto.getConfirmacoes());
		entity.setUsuarios(dto.getUsuarios());
		entity.setGrupos(dto.getGrupos());
		
		//falta os atributos que estão comentados na classe Model.
	}
	
	public void updateCopyDtoToEntity(TreinamentoAgendadoDTO dto, TreinamentoAgendadoModel entity) {
		entity.setNome(dto.getNome());
		entity.setData(dto.getData());
		entity.setLocalizacao(dto.getLocalizacao());
		
		entity.setUsuarios(dto.getUsuarios());
//		entity.setGrupos(dto.getGrupos());
	}
	
}
