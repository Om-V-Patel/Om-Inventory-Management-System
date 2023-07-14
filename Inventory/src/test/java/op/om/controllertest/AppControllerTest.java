package op.om.controllertest;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import com.om.app.repo.UserRepository;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.om.app.Controller.AppController;
import com.om.app.contactus.ContactRepository;
import com.om.app.model.User;
import com.om.app.repo.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppController.class)
@WithMockUser(username="user", roles= {"USER","ADMIN"})
@AutoConfigureMockMvc
public class AppControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private UserRepository userRepo;
	@MockBean
	private ContactRepository contRepo;
	@MockBean
	private JavaMailSender mailSender;
	@InjectMocks
    private AppController appController;

	@Test
	public void HomePageTest() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/");

		mockMvc.perform(request).andExpect(status().is2xxSuccessful())
				.andExpect(view().name("index1.html")).andReturn();
	}
	@Test
	public void PageTest() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/1");

		mockMvc.perform(request).andExpect(status().is2xxSuccessful())
				.andExpect(view().name("index.html")).andReturn();
	}
	@Test
	public void RegisterPageTest() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/register");

		mockMvc.perform(request).andExpect(status().is2xxSuccessful())
				.andExpect(view().name("signup_form")).andReturn();
	}
	@Test
	public void outhPageTest() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/home");

		mockMvc.perform(request).andExpect(status().is2xxSuccessful())
				.andExpect(view().name("Outhlogged")).andReturn();
	}
	@Test
	public void SuccessorPageTest() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/demo");

		mockMvc.perform(request).andExpect(status().is2xxSuccessful())
				.andExpect(view().name("successor")).andReturn();
	}
	
	@Test
	public void ContactpageTest() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/Contact");

		mockMvc.perform(request).andExpect(status().is2xxSuccessful())
				.andExpect(view().name("Contactpage")).andReturn();
	}
	
	@Test
	public void AboutPageTest() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/AboutUs");

		mockMvc.perform(request).andExpect(status().is2xxSuccessful())
				.andExpect(view().name("AboutUspage")).andReturn();
	}
	@Test
	public void insertPageTest() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/insertdata").contentType(MediaType.APPLICATION_JSON).content("{\r\n"
				+ "    \"email\":\"ps@gmail.com\",\r\n"
				+ "    \"firstName\":\"OM\",\r\n"
				+ "    \"lastName\":\"PATEL\",\r\n"
				+ "    \"subject\":\"Product Price Related\"\r\n"
				+ "}");
		mockMvc.perform(request).andExpect(status().is2xxSuccessful())
				.andExpect(view().name("contact_success")).andReturn();
	}
	@Test
	public void sendemailPageTest() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/sendemail");

		mockMvc.perform(request).andExpect(status().is2xxSuccessful())
				.andExpect(view().name("emailsend")).andReturn();
	}
	
	@Test
	public void allusersPageTest() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/users");

		mockMvc.perform(request).andExpect(status().is2xxSuccessful())
				.andExpect(view().name("userspage")).andReturn();
	}
//	 @Test
//	    public void testProcessRegister_SuccessfulRegistration() throws Exception {
//	        User user = new User();
//	        user.setEmail("om@gmail.comn");
//	        user.setPassword("Om@1234");
//
//	        mockMvc.perform(MockMvcRequestBuilders.post("/process_register")
//	             .flashAttr("user", user))
//	                .andExpect(MockMvcResultMatchers.status().isOk())
//	                .andExpect(MockMvcResultMatchers.view().name("register_success"));
//	        Mockito.verify(userRepo).save(any(User.class));
//	    }

//	@Test
//    public void testProcessRegister_SuccessfulRegistration() throws Exception {
//        // Prepare the test data
//        User user = new User();
//        user.setEmail("om@gmail.com");
//        user.setPassword("testpassword");
//    Mockito.when(userRepo.save(Mockito.any(User.class))).thenReturn(user);
//        mockMvc.perform(MockMvcRequestBuilders.post("/process_register")
//                .param("username", "testuser")
//                .param("password", "testpassword"))
//                .andDo(print())
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.view().name("register_success"));
//
//        // Verify that the UserRepository save method was called with the correct user object
//        Mockito.verify(userRepo, Mockito.times(1)).save(Mockito.any(User.class));
//    }
}
