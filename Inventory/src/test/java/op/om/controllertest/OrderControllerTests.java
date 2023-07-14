package op.om.controllertest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.om.app.Controller.OrderController;
import com.om.app.Controller.SupplierController;
import com.om.app.model.ImageGallery;
import com.om.app.model.Mongo;
import com.om.app.repo.MongoRepo;
import com.om.app.services.ImageGalleryService;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderController.class)
@WithMockUser(username="user", roles= {"USER","ADMIN"})
@AutoConfigureMockMvc
public class OrderControllerTests {
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
		RequestBuilder request = MockMvcRequestBuilders.get("/mongo");

		mockMvc.perform(request).andExpect(status().is2xxSuccessful())
				.andExpect(view().name("MongoForm")).andReturn();
	}
//	@Test
//	@Disabled
//	public void insertPageTest() throws Exception {
//		RequestBuilder request = MockMvcRequestBuilders.post("/insertmongodata").contentType(MediaType.APPLICATION_JSON).content("{\r\n"
//		+ "    \"rackid\":\"7\",\r\n"
//			+ "    \"name\":\"Icecream\",\r\n"
//			+ "    \"quantity\":\"10\",\r\n"
//				+ "   \r\n"
//				+ "}");
//		mockMvc.perform(request).andExpect(status().is2xxSuccessful())
//				.andExpect(view().name("supplier_success")).andReturn();
//}
	


	
	@Test
    public void testUpdatedata() {
        // Mock the dependencies
        Long id = 1L;
        ImageGallery imageGallery = new ImageGallery();
        imageGallery.setQuantity(10);
        Mongo mongo = new Mongo();
        mongo.setQuantity(5);

        Mockito.when(imageGalleryService.getImageById(id)).thenReturn(imageGallery);
        Mockito.when(MRepo.findByRackid(id)).thenReturn(mongo);

      
       // String result = orderController.updatedata(id);

      
       // Assert.assertEquals("updated", result);

        
        //Assert.assertEquals(5.0, imageGallery.getQuantity(), 0.001);

        
        //Mockito.verify(imageGalleryService).saveImage(imageGallery);
    }
	
}
