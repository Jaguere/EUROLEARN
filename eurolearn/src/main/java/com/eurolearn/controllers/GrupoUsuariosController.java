package com.eurolearn.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eurolearn.dto.GrupoUsuariosDTO;
import com.eurolearn.dto.UsuarioDTO;
import com.eurolearn.services.GrupoUsuariosService;
import com.eurolearn.services.UsuarioService;
import com.eurolearn.util.Filtro;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/grupos")
public class GrupoUsuariosController {

	@Autowired
	public GrupoUsuariosService service; 
	
	@Autowired
	private UsuarioService userService;
	
	//Descomentar assim que desenvolver o treinamento agendado
//	@Autowired
//	private TreinamentoAgendadoService treinamentoService;
	
	@ModelAttribute("usuarios")
	public List<UsuarioDTO> usuarios(){
		return userService.findAll();
	}
	
	
	//talvez precise de um modelAttribute de treinamentos
	
	@GetMapping()
	public String findAll(Model model) {
		model.addAttribute("grupos", service.findAll());
		return "grupo/listar-grupos";
	}
	
	@GetMapping("/{id}")
	public String findById(@PathVariable("id") int id, Model model) {
		GrupoUsuariosDTO grupo = service.findById(id);
		model.addAttribute("grupoDTO", grupo);
		return "grupo/editar-grupo";
	}
	
	@GetMapping("/form")
	public String loadForm(Model model) {
		model.addAttribute("grupoDTO", new GrupoUsuariosDTO());
		return "grupo/novo-grupo";
	}
	
	@PostMapping
	public String insert(@Valid GrupoUsuariosDTO dto, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			return "grupo/novo-grupo";
		}
		dto = service.insert(dto);
		attributes.addFlashAttribute("mensagem", "grupo criado com sucesso");
		return "redirect:/grupos";
	}
	
	@GetMapping("/{id}/usuarios")
	public String loadInsertUsersIntoGroupForm(@PathVariable("id") int idGrupo) {
		//não precisa adicionar os usuários no model porque já esta via annotation nesse controller
		return "grupo/grupo-adicionar-usuarios.html";
	}
	
	//controller que será o path do botão 'Adicionar usuários' depois da escolha dos usuários no combobox (filtrado ou não);
	
	@PostMapping("/{id}/usuarios")
	public String insertUsersIntoGroup(@PathVariable("id") int idGrupo,  List<UsuarioDTO> usersDTO, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Erro na validação de usuários");
			return "grupo/grupo-adicionar-usuarios.html";
		}
		service.insertUsersIntoGroup(idGrupo, usersDTO);
		attributes.addFlashAttribute("mensagem", "Usuários adicionados ao grupo com sucesso");
		return "grupo/grupo-adicionar-usuarios.html";
	}
	
	//controller que será acionado pelo botão 'Filtrar por' => redireciona para uma página contendo os usuários filtrados
	@GetMapping("/{id}/usuarios/filtro")
	public String filterUsersForInsert(@PathVariable("id") int idGrupo, Filtro filtro, Model model) {
		
		if(filtro.getTipoFiltro().equalsIgnoreCase("nome")) {
			model.addAttribute("usuariosFiltrados", userService.findByNome(filtro.getValor()));
		} else if(filtro.getTipoFiltro().equalsIgnoreCase("cpf")) {
			model.addAttribute("usuariosFiltrados", userService.findByCpf(Long.parseLong(filtro.getValor())));
		} else if(filtro.getTipoFiltro().equalsIgnoreCase("email")) {
			model.addAttribute("usuariosFiltrados", userService.findByEmail(filtro.getValor()));
		} else {
			throw new IllegalArgumentException("Tipo do filtro não reconhecido - "+filtro.getTipoFiltro());
		}
		
		return "grupo/grupo-adicionar-usuarios-filtrados.html";
	}
	
	
	@PutMapping("/{id}")
	public String update(@PathVariable("id") int id, @Valid GrupoUsuariosDTO dto, BindingResult result) {
		if(result.hasErrors()) {
			return "grupo/editar-grupo";
		}
		service.update(dto, id);
		return "redirect:/grupos";
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") int id, RedirectAttributes attributes) {
		service.delete(id);
		attributes.addFlashAttribute("mensagem", "Deletado com sucesso");
		return "redirect:/grupos";
	}
}
