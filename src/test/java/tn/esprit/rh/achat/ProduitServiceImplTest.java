package tn.esprit.rh.achat;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.rh.achat.entities.CategorieProduit;
import tn.esprit.rh.achat.entities.DetailFacture;
import tn.esprit.rh.achat.entities.Produit;
import tn.esprit.rh.achat.entities.Stock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Date;
import java.util.Optional;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import tn.esprit.rh.achat.repositories.ProduitRepository;
import tn.esprit.rh.achat.repositories.StockRepository;
import tn.esprit.rh.achat.services.ProduitServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProduitServiceImplTest {
	@Mock
	private ProduitRepository produitRepository;

	@Mock
	private StockRepository stockRepository;

	@InjectMocks
	private ProduitServiceImpl produitService;
	private Produit produit;




	@Test
	public void testRetrieveProduit() {
		Produit produit = new Produit();
		produit.setIdProduit(1L);
		produit.setLibelleProduit("Test Produit");
		when(produitRepository.findById(1L)).thenReturn(Optional.of(produit));
		Produit retrievedProduit = produitService.retrieveProduit(1L);
		assertNotNull(retrievedProduit);
		assertEquals("Test Produit", retrievedProduit.getLibelleProduit());
	}
	@Test
	public void testAssignProduitToStock() {
		Produit produit = new Produit();
		produit.setIdProduit(1L);

		Stock stock = new Stock();
		stock.setIdStock(1L);

		when(produitRepository.findById(1L)).thenReturn(Optional.of(produit));
		when(stockRepository.findById(1L)).thenReturn(Optional.of(stock));

		produitService.assignProduitToStock(1L, 1L);

		assertNotNull(produit.getStock());
		assertEquals(Long.valueOf(1L), produit.getStock().getIdStock());
	}
	@Test
	public void testAddProduit() {
		Produit produit = new Produit();
		produit.setLibelleProduit("Nouveau Produit");
		produit.setDateCreation(new Date());
		when(produitRepository.save(any(Produit.class))).thenReturn(produit);
		Produit savedProduit = produitService.addProduit(produit);
		assertNotNull(savedProduit);
		assertEquals("Nouveau Produit", savedProduit.getLibelleProduit());
		assertNotNull(savedProduit.getDateCreation());
	}

}
