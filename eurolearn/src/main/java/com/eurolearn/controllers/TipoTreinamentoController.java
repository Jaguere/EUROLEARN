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

import com.eurolearn.dto.TipoTreinamentoDTO;
import com.eurolearn.services.TipoTreinamentoService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/tipostreinamento")
public class TipoTreinamentoController {
	
	@Autowired
	private TipoTreinamentoService service;
	
	//vai precisar do service dos treinamentos agendados depois
	
	@GetMapping("/form")
	public String loadFormTipoTreinamento(Model model) {
		model.addAttribute("tipoTreinamentoDTO", new TipoTreinamentoDTO());
		return "tipotreinamento/novo-tipo-treinamento";
	}
	
	@PostMapping()
    public String insert(@Valid TipoTreinamentoDTO ttDTO,
                         BindingResult result,
                         RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "tipotreinamento/novo-tipo-treinamento";
        }
        ttDTO = service.insert(ttDTO);
        attributes.addFlashAttribute("mensagem", "Tipo produto salvo com sucesso!");
        return "redirect:/tipostreinamento";
    }
	
    @GetMapping()
    public String findAll(Model model) {
        model.addAttribute("tipostreinamento", service.findAll());
        for(TipoTreinamentoDTO tt : service.findAll()) {
        	System.out.println(tt.getNome());
        }
        return "tipotreinamento/listar-tipos-treinamento";
    }
	
    @GetMapping("/{id}")
    public String findById(@PathVariable("id") int id, Model model) {
    	TipoTreinamentoDTO ttdto = service.findById(id);
    	model.addAttribute("tipotreinamentodto", ttdto);
    	return "tipotreinamento/editar-tipo-treinamento";
    }
    
    @PutMapping("/{id}")
    public String update(@PathVariable("id") int id, Model model, @Valid TipoTreinamentoDTO ttDTO, BindingResult result) {
    	if(result.hasErrors()) {
    		ttDTO.setIdTipoTreinamento(id);
    		return "tipotreinamento/editar-tipo-treinamento";
    	}
    	service.update(id, ttDTO);
    	return "redirect:/tipostreinamento";
    }
    
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
    	service.delete(id);
    	return "redirect:/tipostreinamento";
    }
    
}
