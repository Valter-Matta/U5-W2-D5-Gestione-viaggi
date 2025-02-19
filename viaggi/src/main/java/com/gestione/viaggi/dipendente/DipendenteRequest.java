package com.gestione.viaggi.dipendente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DipendenteRequest {

	@NotBlank (message = "username non può essere vuoto")
	private String username;
	@NotBlank (message = "Il nome non può essere vuoto")
	private String nome;
	@NotBlank (message = "Il cognome non può essere vuoto")
	private String cognome;
	@NotBlank (message = "l'email non può essere vuoto")
	@Email(message = "l'email non è valida")
	private String email;
}
