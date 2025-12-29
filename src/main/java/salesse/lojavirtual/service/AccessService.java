package salesse.lojavirtual.service;


import org.springframework.stereotype.Service;

import salesse.lojavirtual.model.Access;
import salesse.lojavirtual.repository.AccessRepository;

@Service
public class AccessService {

	private AccessRepository accessRepository;

	public AccessService(AccessRepository accessRepository) {
		super();
		this.accessRepository = accessRepository;
	}

	public Access save(Access access) {

		/* Qualquer tipo de validação antes de salvar */
		return accessRepository.save(access);
	}
}
