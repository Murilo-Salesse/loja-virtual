package salesse.lojavirtual;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import salesse.lojavirtual.controller.AccessController;
import salesse.lojavirtual.model.Access;

@SpringBootTest
class ApplicationTests {

	@Autowired
	private final AccessController accessController = null;

	@Test
	public void testRegisterAccess() {
		Access access = new Access();

		access.setDescription("ROLE_ADMIN");
		accessController.saveAccess(access);
	}

}
