package op.om.controllertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.ui.Model;

import com.om.app.Controller.AppController;
import com.om.app.Controller.SupplierController;
import com.om.app.contactus.ContactRepository;
import com.om.app.model.Supplier;
import com.om.app.repo.SupplierRepository;
import com.om.app.repo.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SupplierController.class)
@WithMockUser(username="user", roles= {"USER","ADMIN"})
@AutoConfigureMockMvc
public class SupplierControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private UserRepository userRepo;
	@MockBean
	private SupplierRepository SupRepo;
	@MockBean
	private JavaMailSender mailSender;

	@Test
	public void showSupplierForm() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/Supplier");

		mockMvc.perform(request).andExpect(status().is2xxSuccessful())
				.andExpect(view().name("supplier")).andReturn();
	}
//	@Test
//	public void adddata() throws Exception {
//		RequestBuilder request = MockMvcRequestBuilders.post("/insertsupplierdata").contentType(MediaType.APPLICATION_JSON).content("{\r\n"
//				+ "    \"name\":\"ompatel\",\r\n"
//				+ "    \"email\":\"op@gmail.com\",\r\n"
//				+ "    \"description\":\"very good products are available\",\r\n"
//				+ "    \"productname\":\"limca\",\r\n"
//				+ "}");
//
//		mockMvc.perform(request).andExpect(status().is2xxSuccessful())
//				.andExpect(view().name("supplier_success")).andReturn();
//	}
	@Test
	public void listSupplier() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/Suppliersdata");

		mockMvc.perform(request).andExpect(status().is2xxSuccessful())
				.andExpect(view().name("sdata")).andReturn();
	}
	

   
    

}
