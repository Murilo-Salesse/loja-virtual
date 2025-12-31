package salesse.lojavirtual.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import salesse.lojavirtual.model.Access;
import salesse.lojavirtual.service.AccessService;

@Controller
@RestController
@RequestMapping("/api/access")
public class AccessController {

	private AccessService accessService;

	public AccessController(AccessService accessService) {
		super();
		this.accessService = accessService;
	}

	@PostMapping("/save")
	public ResponseEntity<Access> saveAccess(@RequestBody Access access) {

		Access savedAccess = accessService.save(access);
		return new ResponseEntity<Access>(savedAccess, HttpStatus.OK);
	}

	@GetMapping("/find/{id}")
	public ResponseEntity<Access> getEntity(@PathVariable("id") Long idAccess) {

		Access foundedAccess = accessService.getById(idAccess);
		return new ResponseEntity<Access>(foundedAccess, HttpStatus.OK);
	}

	@GetMapping("/findByDescription")
	public ResponseEntity<List<Access>> getEntityByDescription(@RequestParam("description") String desc) {

		List<Access> foundedAccess = accessService.getByDesc(desc);
		return new ResponseEntity<>(foundedAccess, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteEntity(@PathVariable("id") Long idAccess) {

		accessService.delete(idAccess);
		return ResponseEntity.ok("Deletado com sucesso!");
	}

}
