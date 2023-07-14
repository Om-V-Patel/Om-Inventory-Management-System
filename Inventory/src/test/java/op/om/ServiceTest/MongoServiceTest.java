package op.om.ServiceTest;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.om.app.model.Mongo;
import com.om.app.repo.MongoRepo;
import com.om.app.services.MongoService;

class MongoServiceTest {

  @Mock
  private MongoRepo mongoRepo;

  @InjectMocks
  private MongoService mongoService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void testSavedata() {
    Mongo mongo = new Mongo();


    mongoService.savedata(mongo);

    verify(mongoRepo, times(1)).save(mongo);
  }
}

