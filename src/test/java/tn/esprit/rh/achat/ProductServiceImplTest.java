package tn.esprit.rh.achat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
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

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

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

    @Test
    public void testRetrieveAll() {
        Mockito.when(productRepository.findAll()).thenReturn(products);
        List<Produit> retrievedProducts = productService.retrieveAllProduits();
        Assertions.assertEquals(3, retrievedProducts.size(), "The expected size is : " + products.size());
    }

}
