package com.gestione.viaggi.dipendente;

import com.gestione.viaggi.postResponse.PostResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping ("/api/dipendente")

public class DipendenteController {
	@Autowired
	private DipendenteService dipendenteService;

	@PostMapping
	@ResponseStatus (HttpStatus.CREATED)
	public PostResponse save (@RequestBody DipendenteRequest request) {
		return dipendenteService.save(request);
	}

	@GetMapping
	@ResponseStatus (HttpStatus.OK)
	public List<DipendenteResponse> findAll () {
		return dipendenteService.findAll();
	}

	@GetMapping ("/{id}")
	@ResponseStatus (HttpStatus.OK)
	public DipendenteResponse findById (@PathVariable Long id) {
		return dipendenteService.findDipendenteResponseById(id);
	}

	@PutMapping ("/{id}")
	@ResponseStatus (HttpStatus.OK)
	public Dipendente update (@PathVariable Long id, @RequestBody DipendenteRequest request) {
		return dipendenteService.update(id, request);
	}

	@DeleteMapping ("/{id}")
	@ResponseStatus (HttpStatus.NO_CONTENT)
	public void delete (@PathVariable Long id) {
		dipendenteService.delete(id);
	}

	@PatchMapping(path = "/{id}/immagine",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Dipendente uploadImmagine(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
		return dipendenteService.uploadImmagine(id, file);
	}
}