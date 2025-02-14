package com.gestione.viaggi.prenotazione;

import com.gestione.viaggi.postResponse.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prenotazioni")
public class PrenotazioneController {
	@Autowired
	private PrenotazioneService prenotazioneService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PostResponse createPrenotazione( @RequestBody PrenotazioneRequest prenotazione) {
		return prenotazioneService.createPrenotazione(prenotazione);
	}
}
