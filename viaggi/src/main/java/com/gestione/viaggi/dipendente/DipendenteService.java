package com.gestione.viaggi.dipendente;

import com.cloudinary.Cloudinary;
import com.gestione.viaggi.dipendente.emailService.EmailService;
import com.gestione.viaggi.postResponse.PostResponse;
import jakarta.mail.MessagingException;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@Service
@Validated
@RequiredArgsConstructor
public class DipendenteService {
	private final DipendenteRepository repository;
	private final EmailService emailService;
	private final Cloudinary cloudinary;

	private String subject = "Conferma registrazione";
	private String body = "<h1>Le diamo il benvenuto nel nostro servizio, inizi subito a prenotare viaggi</h1>";


	public List<DipendenteResponse> findAll () {
		List<Dipendente> dipendenti = repository.findAll();
		return dipendenti.stream().map(this::dipendenteResponseFromDipendente).collect(toList());
	}

	public DipendenteResponse findDipendenteResponseById (Long id) {
		Dipendente dipendente = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Dipendente not found with id " + id));
		return dipendenteResponseFromDipendente(dipendente);
	}

	public Dipendente findById (Long id) {
		return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Dipendente not found with id " + id));
	}


	public PostResponse save (@Valid DipendenteRequest request) {
		if (repository.existsByEmail(request.getEmail())) {
			throw new EntityExistsException("Email gia esistente");
		}
		Dipendente dipendente = new Dipendente();
		BeanUtils.copyProperties(request, dipendente);
		repository.save(dipendente);
		PostResponse resp = new PostResponse();
		resp.setId(dipendente.getId());
		try {
			emailService.sendEmail(dipendente.getEmail(), subject, body);
		} catch (MessagingException e) {
			System.out.println("errore nell invio della e-mail");
		}
		return resp;
	}

	public Dipendente update (Long id, @Valid DipendenteRequest request) {
		Dipendente entity = repository.findById(id)
			.orElseThrow(() -> new EntityNotFoundException("Dipendente not found with id " + id));
		BeanUtils.copyProperties(request, entity);
		return repository.save(entity);
	}

	public void delete (Long id) {
		if (!repository.existsById(id)) {
			throw new EntityNotFoundException("Dipendente not found with id " + id);
		}
		repository.deleteById(id);
	}

	public Dipendente uploadImmagine (Long id, MultipartFile file) {
		Dipendente entity = repository.findById(id)
			.orElseThrow(() -> new EntityNotFoundException("Dipendente not found with id " + id));
		try {

			Map<?, ?> result = cloudinary.uploader().upload(file.getBytes(),
				Cloudinary.asMap("folder", "gestione-blogging-images", "public_id", file.getOriginalFilename()));
			String immagine = result.get("secure_url").toString();
			entity.setImmagineProfilo(immagine);
			return repository.save(entity);

		} catch (IOException e) {
			throw new RuntimeException("errore durante l upload dell immagine");
		}

	}

	public DipendenteResponse dipendenteResponseFromDipendente (Dipendente dipendente) {
		DipendenteResponse dipendenteResponse = new DipendenteResponse();
		BeanUtils.copyProperties(dipendente, dipendenteResponse);
		return dipendenteResponse;
	}


}
