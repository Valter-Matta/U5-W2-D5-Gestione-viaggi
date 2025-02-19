package com.gestione.viaggi.viaggi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gestione.viaggi.dipendente.Dipendente;
import com.gestione.viaggi.prenotazione.Prenotazione;
import com.gestione.viaggi.stato.Stato;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "viaggi")
public class Viaggio {
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE)
	private Long id;
	private String destinazione;
	private LocalDate data;
	private Stato stato;
	@JsonIgnoreProperties({"viaggio"})
	@OneToMany (mappedBy = "viaggio")
	private List<Prenotazione> prenotazioni;


}