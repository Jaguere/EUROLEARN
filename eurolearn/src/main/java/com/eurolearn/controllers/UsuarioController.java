package com.eurolearn.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eurolearn.dto.UsuarioDTO;
import com.eurolearn.services.UsuarioService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService service;
	
	@GetMapping()
	public String findAll(Model model) {
		model.addAttribute("usuarios", service.findAll());
		return "usuario/listar-usuarios";
	}
	
	@GetMapping("/form")
	public String loadForm(Model model) {
		model.addAttribute("usuarioDTO", new UsuarioDTO());
		return "usuario/novo-usuario";
	}
	
	@GetMapping("/{id}")
	public String findById(@PathVariable("id") Long cpf, Model model) {
		UsuarioDTO dto = service.findById(cpf);
		model.addAttribute("usuarioDTO", dto);
		return "usuario/editar-usuario";
	}
	
	@PostMapping()
	public String insert(@Valid UsuarioDTO usuarioDTO
			, BindingResult result
			, RedirectAttributes attributes
			, Model model) {
		if(result.hasErrors()) {
			return "redirect:/usuarios";
		}
		
		usuarioDTO = service.insert(usuarioDTO);
		
		
		attributes.addFlashAttribute("mensagem", "Usuário salvo com sucesso");
		return "redirect:/usuarios";
	}
	
	@PutMapping("/{id}")
	public String update(@PathVariable("id") Long cpf, Model model, @Valid UsuarioDTO dto, BindingResult result) {
		if(result.hasErrors()) {
			dto.setCpf(cpf);
			return "usuario/editar-usuario";
		}
		dto = service.update(cpf, dto);
		model.addAttribute(dto);
		return "redirect:/usuarios";
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") Long cpf) {
		service.delete(cpf);
		return "redirect:/usuarios";
	}

}
