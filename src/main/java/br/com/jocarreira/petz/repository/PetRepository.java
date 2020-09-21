package br.com.jocarreira.petz.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jocarreira.petz.modelo.Categoria;
import br.com.jocarreira.petz.modelo.Pet;

public interface PetRepository extends JpaRepository<Pet, Long>{

	List<Pet> findByCategoria(Categoria categoria);
	
	Page<Pet> findByCategoria(Categoria categoria, Pageable paginacao);
	
}
