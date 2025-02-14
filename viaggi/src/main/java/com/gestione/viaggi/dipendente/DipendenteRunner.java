package com.gestione.viaggi.dipendente;


import com.gestione.viaggi.faker.FakerConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Order (1)
public class DipendenteRunner implements CommandLineRunner {
	private final DipendenteService DipendenteService;
	private final FakerConfig fakerConfig;

	@Override
	public void run (String... args) throws Exception {
		/*for (int i = 0; i < 2; i++) {
			DipendenteRequest dipendente = new DipendenteRequest();
			dipendente.setUsername(fakerConfig.faker().leagueOfLegends().champion());
			dipendente.setNome(fakerConfig.faker().name().firstName());
			dipendente.setCognome(fakerConfig.faker().name().lastName());
			dipendente.setEmail(fakerConfig.faker().internet().emailAddress());
			DipendenteService.save(dipendente);
		}*/
	}


}
