package com.gestione.viaggi.prenotazione;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gestione.viaggi.dipendente.Dipendente;
import com.gestione.viaggi.viaggi.Viaggio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "prenotazioni")
public class Prenotazione {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "viaggio_id")
	@JsonIgnoreProperties({"prenotazioni"})
	private Viaggio viaggio;

	@ManyToOne
	@JoinColumn(name = "dipendente_id")
	@JsonIgnoreProperties({"prenotazioni"})
	private Dipendente dipendente;

	private LocalDate dataRichiesta;
	private String note;


}