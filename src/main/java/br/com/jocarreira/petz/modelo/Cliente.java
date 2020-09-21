package br.com.jocarreira.petz.modelo;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.jocarreira.petz.controller.dto.ClienteDto;

@Entity
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 45)
	private String nome;
	@Column(length = 60)
	private String endereco;
	
	@Column(length = 11)
	private String cpf;
	
	private LocalDateTime dataCriacao  = LocalDateTime.now();
	
	public Cliente() {}
	
	public Cliente(String nome, String endereco, String cpf) {
		this.nome = nome;
		this.endereco = endereco;
		this.cpf = cpf;
		this.dataCriacao = LocalDateTime.now();
	}
	public Cliente(Long id, String nome, String endereco, String cpf) {
		this.id = id;
		this.nome = nome;
		this.endereco = endereco;
		this.cpf = cpf;
		this.dataCriacao = LocalDateTime.now();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void atualizar(ClienteDto vo) {
		this.nome = vo.getNome();
		this.endereco = vo.getEndereco();
	}
	
	

}
