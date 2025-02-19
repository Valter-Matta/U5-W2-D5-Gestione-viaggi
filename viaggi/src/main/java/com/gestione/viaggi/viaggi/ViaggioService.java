package com.gestione.viaggi.viaggi;

import com.gestione.viaggi.postResponse.PostResponse;
import com.gestione.viaggi.stato.Stato;
import jakarta.persistence.EntityNotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;



import java.util.List;

import static java.util.stream.Collectors.toList;

@Service

@RequiredArgsConstructor
public class ViaggioService {
	private final ViaggioRepository repository;


	public List<ViaggioResponse> findAll () {
		List<Viaggio> viaggi = repository.findAll();
		return viaggi.stream().map(this::viaggioResponseFromViaggio).collect(toList());
	}
	public ViaggioResponse findViaggioResponseById (Long id) {
		Viaggio viaggio = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Viaggio not found with id " + id));
		return viaggioResponseFromViaggio(viaggio);
	}

	public Viaggio findViaggioById (Long id) {
		return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Viaggio not found with id " + id));
	}

	public PostResponse save (ViaggioRequest viaggio) {
		Viaggio entity = new Viaggio();
		BeanUtils.copyProperties(viaggio, entity);
		repository.save(entity);
		PostResponse resp = new PostResponse();
		resp.setId(entity.getId());
		return resp;
	}

	public Viaggio update (Long id, ViaggioRequest viaggioDetails) {
		Viaggio entityMod = repository.findById(id).orElseThrow(() -> new RuntimeException("Viaggio non trovato"));
		entityMod.setDestinazione(viaggioDetails.getDestinazione());
		entityMod.setData(viaggioDetails.getData());
		entityMod.setStato(viaggioDetails.getStato());
		return repository.save(entityMod);
	}

	public void delete (Long id) {
		repository.deleteById(id);
	}

	public Viaggio updateStatoViaggio (Long id, Stato statoViaggio) {
		Viaggio viaggio = repository.findById(id).orElseThrow(() -> new RuntimeException("Viaggio non trovato"));
		viaggio.setStato(statoViaggio);
		return repository.save(viaggio);
	}


	public ViaggioResponse viaggioResponseFromViaggio (Viaggio viaggio) {
		ViaggioResponse response = new ViaggioResponse();
		BeanUtils.copyProperties(viaggio, response);
		return response;
	}
}
