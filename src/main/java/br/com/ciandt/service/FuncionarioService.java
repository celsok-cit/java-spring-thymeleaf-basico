package br.com.ciandt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ciandt.models.Funcionario;
import br.com.ciandt.repository.FuncionarioRepository;

@Component
public class FuncionarioService {
	
	@Autowired
	FuncionarioRepository repository;
	
	public List<Funcionario> buscarFuncionarios() {
		
		return (List<Funcionario>) repository.findAll();
	}
	
	public Funcionario salvar(Funcionario funcionario) {
		
		return repository.save(funcionario);
	}

	public void remover(Long id) {
		repository.deleteById(id);
	}

	public Funcionario atualizar(Funcionario funcionario) {
		return repository.save(funcionario);
	}
	
	public Funcionario buscarPorId(Long id) {
		return repository.findById(id).orElse(new Funcionario());
	}

	public List<Funcionario> buscarPorNome(String nome) {
		return repository.findByNomeContainingIgnoreCase(nome);
	}

}
