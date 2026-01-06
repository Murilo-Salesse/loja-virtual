package salesse.lojavirtual.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import salesse.lojavirtual.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	@Query(value = "select u from users u where u.login = ?1")
	User findUserByLogin(String login);
}
