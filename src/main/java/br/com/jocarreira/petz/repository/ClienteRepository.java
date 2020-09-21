package br.com.jocarreira.petz.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;

import br.com.jocarreira.petz.modelo.Cliente;

public interface ClienteRepository  extends JpaRepository<Cliente, Long> {

	List<Cliente> findByNome(String nome);
	
	Page<Cliente> findByNome(String nome, Pageable paginacao);

}
