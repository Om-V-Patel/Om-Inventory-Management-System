package op.om.controllertest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.om.app.Controller.StoreImageController;
import com.om.app.model.ImageGallery;
import com.om.app.repo.MongoRepo;
import com.om.app.services.ImageGalleryService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StoreImageController.class)
@WithMockUser(username="user", roles= {"USER","ADMIN"})
@AutoConfigureMockMvc
public class ImagestoreTests {

    @Autowired
    private MockMvc mockMvc;

	@MockBean
	private MongoRepo MRepo;

	@MockBean
	private ImageGalleryService imageGalleryService;
	@InjectMocks
    private StoreImageController StoreImageController ;
	 @Autowired
	    private WebApplicationContext webApplicationContext;
    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testCreateProduct() throws Exception {
        MockMultipartFile file = new MockMultipartFile("image", "test.jpg", MediaType.IMAGE_JPEG_VALUE, "image data".getBytes());
        
        mockMvc.perform(MockMvcRequestBuilders.multipart("/image/saveImageDetails")
                .file(file)
                .param("name", "limca")
                .param("price", "100")
                .param("quantity", "10")
                .param("description", "limca is good"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Product Saved With File - test.jpg"))
                .andDo(print());
    }


//    @Test
//    public void testShowProductDetails() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/image/imageDetails")
//                .param("id", "1"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.view().name("imagedetails"))
//                .andDo(print());
//    }
    @Test
    public void testShowProductDetails() throws Exception {
        Long id = 1L;
        ImageGallery imageGallery = new ImageGallery();
        imageGallery.setId(id);
        imageGallery.setDescription("goodlimca");
        imageGallery.setName("limca");
        imageGallery.setPrice(100);
        imageGallery.setQuantity(10);

        when(imageGalleryService.getImageById(id)).thenReturn(imageGallery);

        mockMvc.perform(MockMvcRequestBuilders.get("/image/imageDetails")
                .param("id", id.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("imagedetails"))
                .andExpect(MockMvcResultMatchers.model().attribute("id", id))
                .andExpect(MockMvcResultMatchers.model().attribute("description", "goodlimca"))
                .andExpect(MockMvcResultMatchers.model().attribute("name", "limca"))
                .andExpect(MockMvcResultMatchers.model().attribute("price", 100.0))
                .andExpect(MockMvcResultMatchers.model().attribute("quantity", 10.0));
    }

    @Test
    public void testShow() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/image/show"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("images"))
                .andDo(print());
    }
}
