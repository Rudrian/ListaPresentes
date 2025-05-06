package com.Presenca.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Presenca.Entity.ListaPresenca;
import com.Presenca.Service.ListaPresencaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Modulo Lista - ", description = "API REST - GERENCIAMENTO DE LISTA DE PRESENÇAS")
@RestController
@RequestMapping(value = "/ListaPresentes")
public class ListaPresencaController {

	private final ListaPresencaService listaPresencaService;

	@Autowired
	public ListaPresencaController(ListaPresencaService listaPresencaService) {
		this.listaPresencaService = listaPresencaService;
	}

	@Operation(summary = "Localizar aluno por ID")
	@GetMapping("/{id}")
	public ResponseEntity<ListaPresenca> getProductById(@PathVariable Long id) {
		ListaPresenca listaPresenca = listaPresencaService.getListaPresencaById(id);
		if (listaPresenca != null) {
			return ResponseEntity.ok(listaPresenca);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	@Operation(summary = "Localizar todos alunos")
	@GetMapping("/")
	public ResponseEntity<List<ListaPresenca>> getAllListaPresenca() {
		List<ListaPresenca> listaPresencas = listaPresencaService.getAllListaPresenca();
		return ResponseEntity.ok(listaPresencas);
	}

	@Operation(summary = "Adicionar aluno")
	@PostMapping("/")
	public ResponseEntity<ListaPresenca> criarListaPresenca(@RequestBody @Valid ListaPresenca listaPresenca) {
		ListaPresenca criarListaPresenca = listaPresencaService.salvarListaPresenca(listaPresenca);
		return ResponseEntity.status(HttpStatus.CREATED).body(criarListaPresenca);
	}

	@Operation(summary = "Alterar aluno por ID")
	@PutMapping("/{id}")
	public ResponseEntity<ListaPresenca> updateListaPresenca(@PathVariable Long id,
			@RequestBody @Valid ListaPresenca listaPresenca) {
		ListaPresenca updatedListaPresenca = listaPresencaService.updateListaPresenca(id, listaPresenca);
		if (updatedListaPresenca != null) {
			return ResponseEntity.ok(updatedListaPresenca);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Operation(summary = "Excluir aluno por ID")
	@DeleteMapping("/{id}")
	public ResponseEntity<ListaPresenca> deleteListaPresenca(@PathVariable Long id) {
		boolean deleted = listaPresencaService.deleteListaPresenca(id);
		if (deleted) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}