package salesse.lojavirtual;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import salesse.lojavirtual.controller.AccessController;
import salesse.lojavirtual.model.Access;
import salesse.lojavirtual.repository.AccessRepository;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.ObjectMapper;

//@Profile("test")
@SpringBootTest
public class ApplicationTests {

	@Autowired
	private AccessRepository accessRepository;

	@Autowired
	private AccessController accessController;

	@Autowired
	private WebApplicationContext webApplicationContext;

	/* Teste do endpoint de salvar acesso */
	@Test
	@Transactional
	public void testSaveAccess() throws JacksonException, Exception {
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.webApplicationContext);
		MockMvc mockMvc = builder.build();
		ObjectMapper objectMapper = new ObjectMapper();

		Access access = new Access();
		access.setDescription("ROLE_COMPRADOR");

		ResultActions response = mockMvc.perform(
				MockMvcRequestBuilders.post("/api/access/save").content(objectMapper.writeValueAsString(access))
						.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON));

		String contentAsString = response.andReturn().getResponse().getContentAsString();
		Access objResponse = objectMapper.readValue(contentAsString, Access.class);

		assertEquals(access.getDescription(), objResponse.getDescription());
	}

	/* Teste do endpoint de deletar acesso */
	@Test
	@Transactional
	public void testDeleteAccess() throws Exception {
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.webApplicationContext);
		MockMvc mockMvc = builder.build();

		Access access = new Access();
		access.setDescription("ROLE_DELETADA");
		access = accessRepository.save(access);

		ResultActions response = mockMvc.perform(MockMvcRequestBuilders.delete("/api/access/delete/" + access.getId())
				.accept(MediaType.APPLICATION_JSON));

		int status = response.andReturn().getResponse().getStatus();
		String content = response.andReturn().getResponse().getContentAsString();

		assertEquals(200, status);
		assertEquals("Deletado com sucesso!", content);

		assertFalse(accessRepository.existsById(access.getId()));
	}

	/* Teste do endpoint de buscar acesso por ID */
	@Test
	@Transactional
	public void testFindAccessById() throws Exception {
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.webApplicationContext);
		MockMvc mockMvc = builder.build();
		ObjectMapper objectMapper = new ObjectMapper();

		Access access = new Access();
		access.setDescription("ROLE_OBTER_ID");
		access = accessRepository.save(access);

		ResultActions response = mockMvc.perform(
				MockMvcRequestBuilders.get("/api/access/find/" + access.getId()).accept(MediaType.APPLICATION_JSON));

		int status = response.andReturn().getResponse().getStatus();
		String content = response.andReturn().getResponse().getContentAsString();

		// Converte JSON para objeto
		Access accessResponse = objectMapper.readValue(content, Access.class);

		assertEquals(200, status);
		assertEquals(access.getDescription(), accessResponse.getDescription());
		assertEquals(access.getId(), accessResponse.getId());
		assertTrue(accessRepository.existsById(access.getId()));
	}

	/* Teste do endpoint de buscar acesso por descrição */
	@Test
	@Transactional
	public void testFindAccessByDescription() throws Exception {
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.webApplicationContext);
		MockMvc mockMvc = builder.build();
		ObjectMapper objectMapper = new ObjectMapper();

		accessRepository.deleteAll();

		Access access1 = new Access();
		access1.setDescription("ROLE_COMPRADOR");
		access1 = accessRepository.save(access1);

		Access access2 = new Access();
		access2.setDescription("ROLE_ADMIN");
		accessRepository.save(access2);

		ResultActions response = mockMvc.perform(
				MockMvcRequestBuilders.get("/api/access/findByDescription")
						.param("description", "COMPRADOR")
						.accept(MediaType.APPLICATION_JSON));

		int status = response.andReturn().getResponse().getStatus();
		String content = response.andReturn().getResponse().getContentAsString();

		Access[] accessArray = objectMapper.readValue(content, Access[].class);
		List<Access> accessList = Arrays.asList(accessArray);

		assertEquals(200, status);
		assertEquals(1, accessList.size());
		assertEquals("ROLE_COMPRADOR", accessList.get(0).getDescription());
		assertEquals(access1.getId(), accessList.get(0).getId());
	}

	@Test
	public void testRegisterAccess() {
		Access access = new Access();

		access.setDescription("ROLE_ADMIN");
		access = accessController.saveAccess(access).getBody();

		assertEquals(true, access.getId() > 0);
		assertEquals("ROLE_ADMIN", access.getDescription());
	}

}
