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
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProduitServiceImplTest {

	private Produit produit;

	@Before
	public void setUp() {
		produit = new Produit();
	}

	@Test
	public void testGetIdProduit() {
		Long idProduit = 1L;
		produit.setIdProduit(idProduit);
		assertEquals(idProduit, produit.getIdProduit());
	}

	@Test
	public void testGetCodeProduit() {
		String codeProduit = "12345";
		produit.setCodeProduit(codeProduit);
		assertEquals(codeProduit, produit.getCodeProduit());
	}

	@Test
	public void testGetLibelleProduit() {
		String libelleProduit = "Libelle produit";
		produit.setLibelleProduit(libelleProduit);
		assertEquals(libelleProduit, produit.getLibelleProduit());
	}

	@Test
	public void testGetPrix() {
		float prix = 10.5f;
		produit.setPrix(prix);
		assertEquals(prix, produit.getPrix(), 0);
	}

	@Test
	public void testGetDateCreation() {
		Date dateCreation = new Date();
		produit.setDateCreation(dateCreation);
		assertEquals(dateCreation, produit.getDateCreation());
	}

	@Test
	public void testGetDateDerniereModification() {
		Date dateDerniereModification = new Date();
		produit.setDateDerniereModification(dateDerniereModification);
		assertEquals(dateDerniereModification, produit.getDateDerniereModification());
	}

	@Test
	public void testGetStock() {
		Stock stock = new Stock();
		produit.setStock(stock);
		assertEquals(stock, produit.getStock());
	}

	@Test
	public void testGetDetailFacture() {
		Set<DetailFacture> detailFacture = new HashSet<>();
		produit.setDetailFacture(detailFacture);
		assertEquals(detailFacture, produit.getDetailFacture());
	}

	@Test
	public void testGetCategorieProduit() {
		CategorieProduit categorieProduit = new CategorieProduit();
		produit.setCategorieProduit(categorieProduit);
		assertEquals(categorieProduit, produit.getCategorieProduit());
	}
}
