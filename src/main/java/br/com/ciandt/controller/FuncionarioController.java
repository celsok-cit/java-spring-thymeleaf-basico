package br.com.ciandt.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.ciandt.models.Funcionario;
import br.com.ciandt.service.FuncionarioService;

@Controller
public class FuncionarioController {

	@Autowired
	FuncionarioService funcionarioService;
	
	@RequestMapping(path = "/")
	public String index() {
		return "index";
	}
	
	@RequestMapping(path = "/", method = RequestMethod.GET)
    public String obterFuncionarios(Model model) {
        
		model.addAttribute("funcionarios", funcionarioService.buscarFuncionarios());
        return "index";
    }
	
	@RequestMapping(path = "/cadastrar", method = RequestMethod.GET)
    public String cadastrar(Model model) {
		
        model.addAttribute("funcionario", new Funcionario());
        return "funcionario";
    }
	
	@RequestMapping(path = "/cadastrar/funcionario", method = RequestMethod.POST,  params="action=salvar")
    public String salvarFuncionario(@Valid Funcionario funcionario, BindingResult result, Model model) {
		
		if (result.hasErrors()) {
            return "funcionario";
        }
		
        funcionarioService.salvar(funcionario);		
        return "redirect:/";
    }
	
	@RequestMapping(path = "/cadastrar/funcionario", method = RequestMethod.POST,  params="action=cancelar")
    public String cancelarEdicaoFuncionario(Funcionario funcionario) {        		
        return "redirect:/";
    }
	
	@RequestMapping(path = "/editar/{id}", method = RequestMethod.GET)
    public String editProduct(Model model, @PathVariable(value = "id") String id) {
        
		model.addAttribute("funcionario", funcionarioService.buscarPorId(Long.valueOf(id)));
        return "funcionario";
    }
	
	@RequestMapping(path = "/remover/{id}", method = RequestMethod.GET)
    public String remover(@PathVariable(name = "id") String id) {
		
		Funcionario funcionario = funcionarioService.buscarPorId(Long.valueOf(id));
		
		if (funcionario.getId() != null) {
			funcionarioService.remover(Long.valueOf(id));	
		} else {
			throw new IllegalArgumentException("Invalid user Id:" + id);
		}
		
        return "redirect:/";
    }
	
	@RequestMapping(path = "/buscar-por-nome", method = RequestMethod.GET)
    public String buscarPorNome(Model model, @RequestParam String nome) {
		
		model.addAttribute("funcionarios", funcionarioService.buscarPorNome(nome));
		
        return "index";
    }
	
	@RequestMapping(path = "/ordenar-por-nome-crescente", method = RequestMethod.GET)
    public String ordenarPorNome(Model model) {
		
		List<Funcionario> funcionarios = funcionarioService.buscarFuncionarios();
		
		funcionarios.sort((a, b) -> a.getNome().compareTo(b.getNome()));
		
		model.addAttribute("funcionarios", funcionarios);
		
        return "index";
    }
	
	@RequestMapping(path = "/ordenar-por-nome-decrescente", method = RequestMethod.GET)
    public String ordenarPorNomeDecrescente(Model model) {
		
		List<Funcionario> funcionarios = funcionarioService.buscarFuncionarios();
		
		funcionarios.sort((a, b) -> b.getNome().compareTo(a.getNome()));
		
		model.addAttribute("funcionarios", funcionarios);
		
		return "index";
		
	}
       

}
