package salesse.lojavirtual.service;

import java.util.List;

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

	public Access getById(Long id) {

		return accessRepository.findById(id).orElseThrow(() -> new RuntimeException("ID não encontrado"));
	}

	public List<Access> getByDesc(String desc) {

		return accessRepository.findAccessDesc("%" + desc.toUpperCase() + "%");
	}

	public Void delete(Long idAccess) {

		accessRepository.deleteById(idAccess);
		return null;
	}
}
