package salesse.lojavirtual.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import salesse.lojavirtual.model.Access;
import salesse.lojavirtual.repository.AccessRepository;
import salesse.lojavirtual.service.AccessService;

@Controller
public class AccessController {

    private final AccessRepository accessRepository;
	
	@Autowired
	private final AccessService accessService = null;

    AccessController(AccessRepository accessRepository) {
        this.accessRepository = accessRepository;
    }

    @PostMapping("/saveaccess")
	public Access saveAccess(Access access) {
		
		return accessService.save(access);
	}
}
