package br.com.jocarreira.petz.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.jocarreira.petz.controller.dto.ClienteDto;
import br.com.jocarreira.petz.modelo.Cliente;
import br.com.jocarreira.petz.repository.ClienteRepository;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping
	public List<ClienteDto> listar(@RequestParam(required = false) String nome) {
			//,@PageableDefault(sort = "nome", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		List<Cliente> clientes; 

		if (nome == null) {
			clientes = clienteRepository.findAll();
		} else {
			clientes = clienteRepository.findByNome(nome);
		}
		return ClienteDto.converter(clientes);
	}
	
	@GetMapping("/{id}")
	public ClienteDto detalhar(@PathVariable Long id) {
		Optional<Cliente> optionalCliente = clienteRepository.findById(id);
		Cliente cliente = null;
		if (optionalCliente.isPresent()) {
			cliente = optionalCliente.get();
		}
		return new ClienteDto(cliente);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<ClienteDto> cadastrar(@RequestBody @Valid ClienteDto vo, UriComponentsBuilder uriBuilder) {
		Cliente cliente = vo.converterToCliente();
		clienteRepository.save(cliente);
		
		URI uri = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).body(new ClienteDto(cliente));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ClienteDto> atualizar(@PathVariable Long id, @RequestBody @Valid ClienteDto vo) {		
		Optional<Cliente> optional = clienteRepository.findById(id);
		if (optional.isPresent()) {
			Cliente atual = optional.get();
			System.out.println(atual.getCpf());
			atual.atualizar(vo);
			clienteRepository.save(atual);
			return ResponseEntity.ok(new ClienteDto(atual));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> excluir(@PathVariable Long id) {
		Optional<Cliente> optional = clienteRepository.findById(id);
		if (optional.isPresent()) {
			clienteRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
