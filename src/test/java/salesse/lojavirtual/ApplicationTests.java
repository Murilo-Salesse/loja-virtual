package salesse.lojavirtual;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import junit.framework.TestCase;
import salesse.lojavirtual.controller.AccessController;
import salesse.lojavirtual.model.Access;

@SpringBootTest
public class ApplicationTests extends TestCase{

	@Autowired
	private AccessController accessController;

	@Test
	public void testRegisterAccess() {
		Access access = new Access();

		access.setDescription("ROLE_ADMIN");
		
		access = accessController.saveAccess(access).getBody();
		
		assertEquals(true, access.getId() > 0);
		assertEquals("ROLE_ADMIN", access.getDescription());
	}

}
