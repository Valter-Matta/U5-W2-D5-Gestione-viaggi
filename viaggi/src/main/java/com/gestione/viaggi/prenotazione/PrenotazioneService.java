package com.gestione.viaggi.prenotazione;

import com.gestione.viaggi.dipendente.Dipendente;
import com.gestione.viaggi.dipendente.DipendenteRepository;
import com.gestione.viaggi.postResponse.PostResponse;
import com.gestione.viaggi.viaggi.Viaggio;
import com.gestione.viaggi.viaggi.ViaggioRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;

import java.util.List;

@Service
@Validated
@RequiredArgsConstructor
public class PrenotazioneService {

	private final PrenotazioneRepository prenotazioneRepository;

	private final DipendenteRepository dipendenteRepository;

	private final ViaggioRepository viaggioRepository;

	public PostResponse createPrenotazione ( @Valid PrenotazioneRequest prenotazione) {
		Dipendente dipendente = dipendenteRepository.findById(prenotazione.getDipendenteId()).orElseThrow(() -> new RuntimeException("Dipendente non trovato"));
		Viaggio viaggio = viaggioRepository.findById(prenotazione.getViaggioId()).orElseThrow(() -> new RuntimeException("Viaggio non trovato"));

		if (prenotazioneRepository.existsByDipendenteAndDataRichiesta(dipendente, prenotazione.getDataRichiesta())) {
			throw new EntityExistsException("Dipendente già impegnato in questa data");
		}

		Prenotazione entity = new Prenotazione();
		BeanUtils.copyProperties(prenotazione, entity);
		entity.setDipendente(dipendente);
		entity.setViaggio(viaggio);

		prenotazioneRepository.save(entity);
		PostResponse resp = new PostResponse();
		resp.setId(entity.getId());
		return resp;
	}
}
