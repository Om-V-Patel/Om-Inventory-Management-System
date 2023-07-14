package op.om.ServiceTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.om.app.model.ImageGallery;
import com.om.app.repo.ImageGalleryRepository;
import com.om.app.services.ImageGalleryService;

class ImageGalleryServiceTest {

  @Mock
  private ImageGalleryRepository imageGalleryRepository;

  @InjectMocks
  private ImageGalleryService imageGalleryService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void testSaveImage() {
    ImageGallery imageGallery = new ImageGallery();
    // Set up any required data for the test

    imageGalleryService.saveImage(imageGallery);

    // Verify that the save method of the repository was called
    verify(imageGalleryRepository, times(1)).save(imageGallery);
  }

  @Test
  void testGetAllActiveImages() {
    // Set up a list of images to be returned by the repository
    List<ImageGallery> images = new ArrayList<>();
    images.add(new ImageGallery());
    images.add(new ImageGallery());
    when(imageGalleryRepository.findAll()).thenReturn(images);

    List<ImageGallery> result = imageGalleryService.getAllActiveImages();

    // Verify that the repository method was called and the correct list was returned
    verify(imageGalleryRepository, times(1)).findAll();
    assertEquals(images, result);
  }

  @Test
  void testGetImageById() {
    Long imageId = 1L;
    ImageGallery image = new ImageGallery();
    // Set up any required data for the test
    when(imageGalleryRepository.findById(imageId)).thenReturn(Optional.of(image));

    ImageGallery result = imageGalleryService.getImageById(imageId);

    // Verify that the repository method was called and the correct image was returned
    verify(imageGalleryRepository, times(1)).findById(imageId);
    assertEquals(image, result);
  }
}
