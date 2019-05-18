package br.com.ciandt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ciandt.models.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{
	
	public List<Funcionario> findByNomeContainingIgnoreCase(String nome);

}
