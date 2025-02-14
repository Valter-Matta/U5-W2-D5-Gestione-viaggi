package com.gestione.viaggi.viaggi;


import com.gestione.viaggi.faker.FakerConfig;
import com.gestione.viaggi.stato.Stato;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
@Order(2)
public class ViaggioRunner implements CommandLineRunner {
	private final ViaggioService ViaggioService;
	private final FakerConfig fakerConfig;

	@Override
	public void run (String... args) throws Exception {

		for (int i = 0; i < 20; i++) {
			ViaggioRequest viaggio = new ViaggioRequest();
			viaggio.setDestinazione(fakerConfig.faker().address().city());
			viaggio.setData(LocalDate.now());
			viaggio.setStato(Stato.IN_PROGRAMMA);
			ViaggioService.save(viaggio);
		}
	}


}
