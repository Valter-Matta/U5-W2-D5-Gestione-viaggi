package com.gestione.viaggi.dipendente;


import org.springframework.data.jpa.repository.JpaRepository;

public interface DipendenteRepository extends JpaRepository<Dipendente, Long> {
	public boolean existsByEmail(String email);
}
