package salesse.lojavirtual.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import salesse.lojavirtual.model.Access;
import salesse.lojavirtual.service.AccessService;

@Controller
public class AccessController {

	private AccessService accessService;

	public AccessController(AccessService accessService) {
		super();
		this.accessService = accessService;
	}

	@PostMapping("/saveaccess")
	public Access saveAccess(Access access) {

		return accessService.save(access);
	}
}
