package com.gestione.viaggi.prenotazione;


import com.gestione.viaggi.dipendente.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
	public boolean existsByDipendenteAndDataRichiesta (Dipendente dipendente, LocalDate dataRichiesta);
}
