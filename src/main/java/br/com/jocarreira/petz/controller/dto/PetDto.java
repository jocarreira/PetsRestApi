package br.com.jocarreira.petz.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.Page;

import br.com.jocarreira.petz.modelo.Categoria;
import br.com.jocarreira.petz.modelo.Cliente;
import br.com.jocarreira.petz.modelo.Pet;

public class PetDto {

	@NotNull 
	@NotEmpty 
	@Length(min = 2 , max = 60)
	private String descricao;
	private Categoria categoria;
	
	public PetDto() {}
	
	public PetDto(Pet pet) {
		this.descricao = pet.getDescricao();
		this.categoria = pet.getCategoria();
	}

	public String getDescricao() {
		return descricao;
	}

	public Categoria getCategoria() {
		return categoria;
	}
	
	public static List<PetDto> converter(List<Pet> clientes) {
		return clientes.stream().map(PetDto::new).collect(Collectors.toList());
	}
	
	public static Page<PetDto> converter(Page<Pet> clientes) {
		return clientes.map(PetDto::new);
	}

	public Pet converterToCliente() {
		return new Pet(this.descricao, this.categoria);
	}
}
