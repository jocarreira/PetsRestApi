package br.com.jocarreira.petz.modelo;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;

import br.com.jocarreira.petz.controller.dto.PetDto;

@Entity
public class Pet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descricao;
	@Enumerated(EnumType.STRING)
	private Categoria categoria;
	
	public Pet() {}
	
	public Pet(String nome, Categoria categoria) {
		this.descricao = nome;
		this.categoria = categoria;
	}
	
	public Pet(Long id, String nome, Categoria categoria) {
		this.id = id;
		this.descricao = nome;
		this.categoria = categoria;
	}

	public Long getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void atualizar(@Valid PetDto vo) {
		this.descricao = vo.getDescricao();
		this.categoria = vo.getCategoria();
	}


}
