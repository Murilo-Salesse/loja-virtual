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

		return accessRepository.save(access);
	}

	public Void delete(Long idAccess) {

		accessRepository.deleteById(idAccess);
		return null;
	}
}
