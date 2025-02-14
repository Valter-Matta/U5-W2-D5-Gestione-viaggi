package com.gestione.viaggi.prenotazione;


import com.gestione.viaggi.dipendente.DipendenteService;
import com.gestione.viaggi.faker.FakerConfig;
import com.gestione.viaggi.viaggi.ViaggioService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
@Order(3)

public class PrenotazioneRunner implements CommandLineRunner {
	private final PrenotazioneService prenotazioneService;
	private final FakerConfig fakerConfig;
	private final DipendenteService dipendenteService;
	private final ViaggioService ViaggioService;

	@Override
	public void run (String... args) throws Exception {

		//creo una prenotazione di prova
		//Prenotazione prenotazione = new Prenotazione();
		//prenotazione.setDataRichiesta(LocalDate.now());
		//prenotazioneService.createPrenotazione(dipendenteService.findById(1L).getId(), ViaggioService.findViaggioById(1L).getId(), prenotazione);
	}


}
