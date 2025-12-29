package salesse.lojavirtual.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import salesse.lojavirtual.model.Access;
import salesse.lojavirtual.repository.AccessRepository;

@Service
public class AccessService {

	@Autowired
	private AccessRepository accessRepository;
	
	public Access save(Access access) {
		
		/*Qualquer tipo de validação antes de salvar*/
		return accessRepository.save(access);
	}
}
