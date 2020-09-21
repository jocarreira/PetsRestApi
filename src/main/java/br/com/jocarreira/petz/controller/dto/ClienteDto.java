package br.com.jocarreira.petz.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.Page;

import br.com.jocarreira.petz.modelo.Cliente;

public class ClienteDto {

	private Long id;
	@NotNull 
	@NotEmpty 
	@Length(min = 2 , max = 45)
	private String nome;
	@NotNull 
	@NotEmpty 
	@Length(min = 10 , max = 60)
	private String endereco;
	@NotNull 
	@NotEmpty 
	@Length(min = 11 , max = 11)
	private String cpf;
	
	public ClienteDto() {}
	
	public ClienteDto(Cliente cliente) {
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.endereco = cliente.getEndereco();
		this.cpf = cliente.getCpf();
	}
	
	public String getNome() {
		return nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public String getCpf() {
		return cpf;
	}
	
	public static List<ClienteDto> converter(List<Cliente> clientes) {
		return clientes.stream().map(ClienteDto::new).collect(Collectors.toList());
	}
	
	public static Page<ClienteDto> converter(Page<Cliente> clientes) {
		return clientes.map(ClienteDto::new);
	}

	public Long getId() {
		return id;
	}

	public Cliente converterToCliente() {
		return new Cliente(this.id, this.nome, this.endereco, this.cpf);
	}
}
