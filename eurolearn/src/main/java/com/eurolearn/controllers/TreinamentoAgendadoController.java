package com.eurolearn.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eurolearn.dto.TipoTreinamentoDTO;
import com.eurolearn.dto.TreinamentoAgendadoDTO;
import com.eurolearn.services.TipoTreinamentoService;
import com.eurolearn.services.TreinamentoAgendadoService;
import com.eurolearn.services.UsuarioService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/treinamentos")
public class TreinamentoAgendadoController {
	
	@Autowired
	private TreinamentoAgendadoService service;
	
	@Autowired
	private TipoTreinamentoService tipoService;
	
	@Autowired
	private UsuarioService userService;
	
	@ModelAttribute("tipos")
	public List<TipoTreinamentoDTO> tipos(){
		return tipoService.findAll();
	}
	
	@GetMapping()
	public String findAll(Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		System.out.println(" ====================== >ATRIBUTOS NA SESSION DO FINDALL!!");
		
		System.out.println(session.getAttribute("userId"));
		
		model.addAttribute("treinamentos", service.findAll());
		return "treinamento/listar-treinamentos";
	}
	
	@GetMapping("/{id}")
	public String findById(Model model, @PathVariable("id") int idTreinamentoAgendado) {
		model.addAttribute("treinamentoAgendadoDTO", service.findById(idTreinamentoAgendado));
		return "treinamento/treinamento-detalhe";
	}
	
	@GetMapping("/membros/form/{id}")
	public String openMembrosForm(Model model, @PathVariable("id") int idTreinamentoAgendado) {
		model.addAttribute("usuariosDTO", userService.findAll());
		model.addAttribute("treinamentoAgendadoDTO", service.findById(idTreinamentoAgendado));
		return "treinamento/treinamento-adicionar-membro";
	}
	
	@GetMapping("/qrcode/{id}")
	public String openQrCode(Model model, @PathVariable("id") int idTreinamentoAgendado) {
		model.addAttribute("idTreinamentoAgendado", idTreinamentoAgendado);
		return "treinamento/qr-code";
	}
	
	@GetMapping("/form")
	public String openInsertForm(Model model) {
		model.addAttribute("treinamentoAgendadoDTO", new TreinamentoAgendadoDTO());
		return "treinamento/treinamento-agendar";
	}
	
	@PostMapping()
	public String insert(@Valid TreinamentoAgendadoDTO dto, RedirectAttributes attributes, BindingResult result) {
		if(result.hasErrors()) {
			return "treinamento/agendar-treinamento";
		}
		dto = service.insert(dto);
		attributes.addFlashAttribute("mensagem", "Treinamento agendado com sucesso!");
		return "redirect:/treinamentos";
	}
	
	
//	@PostMapping("/{id}/membros/{userId}")
//	public String includeUserInTreinamento(Model model, @PathVariable("id") int idTreinamentoAgendado, @PathVariable("userId") long idUsuario) {
//		model.addAttribute("treinamentoAgendadoDTO", service.insertUserIntoTreinamento(idTreinamentoAgendado, idUsuario));
//		return "redirect:/treinamentos";
//	}
	
	@PutMapping("/{id}")
	public String update(@PathVariable("id") int idTreinamentoAgendado, Model model, @Valid TreinamentoAgendadoDTO dto, BindingResult result) {
		if(result.hasErrors()) {
			dto.setIdTreinamentoAgendado(idTreinamentoAgendado);
			return "redirect:/treinamentos/"+idTreinamentoAgendado+"/membros/form";
		}
		dto = service.update(idTreinamentoAgendado, dto);
		model.addAttribute("TreinamentoAgendadoDTO", dto); 
		return "redirect:/treinamentos";
	}
	
	
	

}
