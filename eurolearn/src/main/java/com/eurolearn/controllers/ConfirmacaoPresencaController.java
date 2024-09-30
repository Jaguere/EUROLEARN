package com.eurolearn.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eurolearn.dto.ConfirmacaoPresencaDTO;
import com.eurolearn.models.TreinamentoAgendadoModel;
import com.eurolearn.models.UsuarioModel;
import com.eurolearn.services.ConfirmacaoPresencaService;
import com.eurolearn.services.TreinamentoAgendadoService;
import com.eurolearn.services.UsuarioService;

@Controller
@RequestMapping("/confirmacoes")
public class ConfirmacaoPresencaController {
	
	@Autowired
	private ConfirmacaoPresencaService service;
	
	@Autowired
	private TreinamentoAgendadoService treinamentoService;
	
	@Autowired
	private UsuarioService userService;
//	
//	@GetMapping
//	public String findAll() {
//		
//	}
	
	@PostMapping("/{idTreinamentoAgendado}/{idUsuario}")
	public String insert(@PathVariable("idTreinamentoAgendado") int idTreinamentoAgendado, @PathVariable("idUsuario") long cpfUsuario) {
		ConfirmacaoPresencaDTO dto = new ConfirmacaoPresencaDTO();
		dto.setDataConfirmacao(LocalDate.now());
		
		UsuarioModel usuario = userService.findByIdModel(cpfUsuario);
		
		dto.setUsuario(usuario);
		
		TreinamentoAgendadoModel treinamentoEntity = new TreinamentoAgendadoModel();
		treinamentoService.copyDtoToEntity(treinamentoService.findById(idTreinamentoAgendado), treinamentoEntity);
		
		dto.setTreinamentoAgendado(treinamentoEntity);
		service.insert(dto, idTreinamentoAgendado);
		
		return "redirect:/treinamentos";
	}
	
}
