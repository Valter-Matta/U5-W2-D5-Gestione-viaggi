package com.gestione.viaggi.viaggi;

import com.gestione.viaggi.stato.Stato;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViaggioRequest {
	private String destinazione;
	private LocalDate data;
	private Stato stato;
}
