package tn.esprit.rh.achat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.rh.achat.entities.Produit;
import tn.esprit.rh.achat.repositories.ProduitRepository;
import tn.esprit.rh.achat.services.ProduitServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    ProduitRepository productRepository;

    @InjectMocks
    ProduitServiceImpl productService;

    List<Produit> products = new ArrayList<Produit>() {
        {
            add(new Produit());
            add(new Produit());
            add(new Produit());
        }
    };
    Produit product = new Produit(1L);
    Produit product1 = new Produit(1L); //null;


    @Test
    void testRetrieveAll() {
        Mockito.when(productRepository.findAll()).thenReturn(products);
        List<Produit> retrievedProducts = productService.retrieveAllProduits();
        Assertions.assertEquals(3, retrievedProducts.size(), "The expected size is : " + products.size());
    }
    @Test
    void testAddProduct() {
        Mockito.when(productRepository.save(Mockito.any(Produit.class))).thenReturn(product);
        Produit addedProduct = productService.addProduit(product);
        Assertions.assertNotNull(addedProduct, "The added product should not be null.");
    }
    @Test
    void testRetrieveProduct () {
        Mockito.when(productRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(product));
        Produit product1 = productService.retrieveProduit(5L);
        Assertions.assertNotNull(product1, "The retrieved product should be null.");
    }
    @Test
    void testRemoveProduct() {
        Long productId = 1L;
        Mockito.doNothing().when(productRepository).deleteById(Mockito.anyLong());
        productService.deleteProduit(productId);
        Mockito.verify(productRepository, Mockito.times(1)).deleteById(productId);
    }
    @Test
    void testUpdateProduct() {
        Mockito.when(productRepository.save(Mockito.any(Produit.class))).thenReturn(product);
        Produit updatedProduct = productService.updateProduit(product1);
        Assertions.assertEquals(product.getIdProduit(), updatedProduct.getIdProduit(), "The updated product should have the same identifier as the old one");
        Assertions.assertNotNull(updatedProduct, "The updated product should not be null.");
    }

}
