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

import com.om.app.model.Supplier;
import com.om.app.repo.SupplierRepository;
import com.om.app.services.SupplierService;

class SupplierServiceTest {
  
  @Mock
  private SupplierRepository supplierRepository;
  
  @InjectMocks
  private SupplierService supplierService;
  
  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  
  @Test
  void testSaveImage() {
    Supplier supplier = new Supplier();

    
    supplierService.saveImage(supplier);
 
    verify(supplierRepository, times(1)).save(supplier);
  }
  
  @Test
  void testGetAllActiveSupplier() {

    List<Supplier> suppliers = new ArrayList<>();
    suppliers.add(new Supplier());
    suppliers.add(new Supplier());
    when(supplierRepository.findAll()).thenReturn(suppliers);
    
    List<Supplier> result = supplierService.getAllActiveSupplier();

    verify(supplierRepository, times(1)).findAll();
    assertEquals(suppliers, result);
  }
  
  @Test
  void testGetSupplierById() {
    Long supplierId = 1L;
    Supplier supplier = new Supplier();

    when(supplierRepository.findById(supplierId)).thenReturn(Optional.of(supplier));
    
    Optional<Supplier> result = supplierService.getSupplierById(supplierId);
    
    verify(supplierRepository, times(1)).findById(supplierId);
    assertTrue(result.isPresent());
    assertEquals(supplier, result.get());
  }
}
