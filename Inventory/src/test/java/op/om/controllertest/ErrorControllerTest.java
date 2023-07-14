package op.om.controllertest;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import javax.servlet.RequestDispatcher;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.om.app.Controller.ErrorController;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ErrorController.class)
@WithMockUser(username="user", roles= {"USER","ADMIN"})
@AutoConfigureMockMvc
class ErrorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testHandleError_404() throws Exception {
        mockMvc.perform(get("/error")
                .requestAttr(RequestDispatcher.ERROR_STATUS_CODE, HttpStatus.NOT_FOUND.value()))
                .andExpect(status().isOk())
                .andExpect(view().name("error404"));
    }

    @Test
    void testHandleError_500() throws Exception {
        mockMvc.perform(get("/error")
                .requestAttr(RequestDispatcher.ERROR_STATUS_CODE, HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .andExpect(status().isOk())
                .andExpect(view().name("error500"));
    }

    @Test
    void testHandleError_Default() throws Exception {
        mockMvc.perform(get("/error"))
                .andExpect(status().isOk())
                .andExpect(view().name("errorpage"));
    }
}
