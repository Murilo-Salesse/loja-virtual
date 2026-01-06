package salesse.lojavirtual.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import salesse.lojavirtual.model.Access;

@Repository
@Transactional
public interface AccessRepository extends JpaRepository<Access, Long> {

	@Query("select a from Access a where upper(trim(a.description)) like ?1")
	List<Access> findAccessDesc(String description);
}
