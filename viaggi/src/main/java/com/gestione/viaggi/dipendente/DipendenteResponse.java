package com.gestione.viaggi.dipendente;

import com.gestione.viaggi.prenotazione.Prenotazione;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DipendenteResponse {
	private Long id;
	private String username;
	private String nome;
	private String cognome;
	private String email;
	private String immagineProfilo;
	@OneToMany (mappedBy = "dipendente")
	private List<Prenotazione> prenotazioni;
}
