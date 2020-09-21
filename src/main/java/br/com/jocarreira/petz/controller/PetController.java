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

import br.com.jocarreira.petz.controller.dto.PetDto;
import br.com.jocarreira.petz.modelo.Categoria;
import br.com.jocarreira.petz.modelo.Pet;
import br.com.jocarreira.petz.repository.PetRepository;

@RestController
@RequestMapping("/pets")
public class PetController {

	@Autowired
	private PetRepository petRepository;
	
	@GetMapping
	public List<PetDto> listar(@RequestParam(required = false) Categoria categoria) {
			//, @PageableDefault(sort = "descricao", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		List<Pet> pets; 
		if (categoria == null) {
			pets = petRepository.findAll();
		} else {
			pets = petRepository.findByCategoria(categoria);
		}
		return PetDto.converter(pets);
	}
	
	@GetMapping("/{id}")
	public PetDto detalhar(@PathVariable Long id) {
		Optional<Pet> optionalPet = petRepository.findById(id);
		Pet pet = null;
		if (optionalPet.isPresent()) {
			pet = optionalPet.get();
		}
		return new PetDto(pet);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<PetDto> cadastrar(@RequestBody @Valid PetDto vo, UriComponentsBuilder uriBuilder) {
		Pet pet = vo.converterToCliente();
		petRepository.save(pet);
		
		URI uri = uriBuilder.path("/clientes/{id}").buildAndExpand(pet.getId()).toUri();
		return ResponseEntity.created(uri).body(new PetDto(pet));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<PetDto> atualizar(@PathVariable Long id, @RequestBody @Valid PetDto vo) {		
		Optional<Pet> optional = petRepository.findById(id);
		if (optional.isPresent()) {
			Pet atual = optional.get();
			atual.atualizar(vo);
			petRepository.save(atual);
			return ResponseEntity.ok(new PetDto(atual));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> excluir(@PathVariable Long id) {
		Optional<Pet> optional = petRepository.findById(id);
		if (optional.isPresent()) {
			petRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
}
