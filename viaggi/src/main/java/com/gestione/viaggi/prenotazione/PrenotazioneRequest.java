package com.gestione.viaggi.prenotazione;

import com.gestione.viaggi.dipendente.Dipendente;
import com.gestione.viaggi.viaggi.Viaggio;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrenotazioneRequest {
	@NotNull (message = "la data non può essere vuota")
	private LocalDate dataRichiesta;
	private String note;
	private Long DipendenteId;
	private Long ViaggioId;
}
