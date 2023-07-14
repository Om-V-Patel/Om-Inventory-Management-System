package op.om.controllertest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.om.app.Controller.OrderController;
import com.om.app.Controller.Outh2Controller;
import com.om.app.repo.MongoRepo;
import com.om.app.services.ImageGalleryService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Outh2Controller.class)
@WithMockUser(username = "user", roles = { "USER", "ADMIN" })
@AutoConfigureMockMvc
public class OuthController {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private MongoRepo MRepo;
	@MockBean
	private ImageGalleryService imageGalleryService;
	@InjectMocks
	private OrderController orderController;

	@Test
	public void OrderPageTest() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/hello");

		mockMvc.perform(request).andExpect(status().is2xxSuccessful()).andExpect(view().name("Outhlogged")).andReturn();
	}
}
