package com.gestione.viaggi.dipendente;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gestione.viaggi.prenotazione.Prenotazione;
import com.gestione.viaggi.viaggi.Viaggio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "dipendenti")
public class Dipendente {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	private String nome;
	private String cognome;
	private String email;
	private String immagineProfilo;
	@OneToMany(mappedBy = "dipendente")
	@JsonIgnoreProperties({"dipendente"})
	private List<Prenotazione> prenotazioni;


}