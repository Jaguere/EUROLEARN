package com.eurolearn.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eurolearn.dto.UsuarioDTO;
import com.eurolearn.models.UsuarioModel;
import com.eurolearn.repository.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	@Transactional(readOnly = true)
	public List<UsuarioDTO> findAll(){
			List<UsuarioModel> list = repository.findAll();
			return list.stream().map(UsuarioDTO::new).collect(Collectors.toList());
	}
	
	public List<UsuarioDTO> findByNome(String nome){
		List<UsuarioDTO> results = new ArrayList<UsuarioDTO>();
		for(UsuarioModel entity : repository.findByNome(nome)) {
			results.add(new UsuarioDTO(entity));
		}
		return results;
	}
	
	public List<UsuarioDTO> findByCpf(long cpf){
		List<UsuarioDTO> results = new ArrayList<UsuarioDTO>();
		for(UsuarioModel entity : repository.findByCpf(cpf)) {
			results.add(new UsuarioDTO(entity));
		}
		return results;
	}
	
	public List<UsuarioDTO> findByEmail(String email){
		List<UsuarioDTO> results = new ArrayList<UsuarioDTO>(); 
		for(UsuarioModel entity : repository.findByEmail(email)) {
			results.add(new UsuarioDTO(entity));
		}
		return results;
	}
	
	@Transactional
	public UsuarioDTO insert(UsuarioDTO dto) {
		UsuarioModel entity = new UsuarioModel();
		copyUserDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new UsuarioDTO(entity);
	}
	
	 @Transactional(readOnly = true)
	    public UsuarioDTO findById(long id) {

	    	UsuarioModel usuario = repository.findById(id).orElseThrow(
	                () -> new IllegalArgumentException("Recurso inválido - " + id)
	        );
	        return new UsuarioDTO(usuario);
	 }
	 
	 	public UsuarioModel findByIdModel(long id) {
	 		return repository.findById(id).orElseThrow(
	                () -> new IllegalArgumentException("Recurso inválido - " + id)
	        );
	 	}
	 
	 
	 @Transactional
		public UsuarioDTO update(long id, UsuarioDTO dto) {
			try {
				UsuarioModel usuario = repository.getReferenceById(id);
				copyUserDtoToEntity(dto, usuario);
				usuario = repository.save(usuario);
				return new UsuarioDTO(usuario);
			} catch (EntityNotFoundException e) {
				throw new IllegalArgumentException("Recurso não encontrado"); 
			}
		}
	 
	 @Transactional
		public void delete(long id) {
			if(!repository.existsById(id)) {
				throw new IllegalArgumentException("Tipo de treinamento inválido - id: "+id);
			}
			try {
				repository.deleteById(id);
			} catch (Exception e) {
				throw new IllegalArgumentException("Tipo de treinamento inválido - id: "+id);
			}
		}
	
	//mudei o nome do método, se der pau olhar aq
	public static void copyUserDtoToEntity(UsuarioDTO dto, UsuarioModel entity) {
		entity.setCpf(dto.getCpf());
		entity.setRg(dto.getRg());
		entity.setNome(dto.getNome());
		entity.setSobrenome(dto.getSobrenome());
		entity.setGenero(dto.getGenero());
		entity.setEmail(dto.getEmail());
		entity.setDataNasc(dto.getDataNasc());
	
		entity.setSenha(dto.getSenha());
		entity.setDataUltimaSenha(dto.getDataUltimaSenha());
		entity.setNivelAcesso(dto.getNivelAcesso());
		entity.setCargo(dto.getCargo());
		entity.setSetor(dto.getSetor());
		entity.setDataAdmissao(dto.getDataAdmissao());
		//falta adicionar o código que pega a lista de elementos associados na dto e passa pra entity.
	}
	
	
}
	

