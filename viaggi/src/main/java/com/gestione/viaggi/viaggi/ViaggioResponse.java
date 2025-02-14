package com.gestione.viaggi.viaggi;

import com.gestione.viaggi.prenotazione.Prenotazione;
import com.gestione.viaggi.stato.Stato;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViaggioResponse {
	private Long id;
	private String destinazione;
	private LocalDate data;
	private Stato stato;
	private List<Prenotazione> prenotazioni;
}
