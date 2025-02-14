package com.gestione.viaggi.viaggi;

import com.gestione.viaggi.postResponse.PostResponse;
import com.gestione.viaggi.stato.Stato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/viaggi")
public class ViaggioController {
	@Autowired
	private ViaggioService viaggioService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PostResponse save (@RequestBody ViaggioRequest viaggio) {
		return viaggioService.save(viaggio);
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<ViaggioResponse> findAll () {
		return viaggioService.findAll();
	}

	@GetMapping ("/{id}")
	public ViaggioResponse findById (@PathVariable Long id) {
		return viaggioService.findViaggioResponseById(id);
	}

	@PutMapping ("/{id}")
	public Viaggio update (@PathVariable Long id, @RequestBody ViaggioRequest viaggio) {
		return viaggioService.update(id, viaggio);
	}

	@DeleteMapping ("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete (@PathVariable Long id) {
		viaggioService.delete(id);
	}

	@PatchMapping ("/{id}/stato")
	public Viaggio updateStatoViaggio (@PathVariable Long id, @RequestParam Stato statoViaggio) {
		return viaggioService.updateStatoViaggio(id, statoViaggio);
	}

}
