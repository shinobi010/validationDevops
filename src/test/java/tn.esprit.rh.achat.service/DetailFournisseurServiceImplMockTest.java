package tn.esprit.rh.achat.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.rh.achat.entities.CategorieFournisseur;
import tn.esprit.rh.achat.entities.DetailFournisseur;
import tn.esprit.rh.achat.entities.Fournisseur;
import tn.esprit.rh.achat.repositories.DetailFournisseurRepository;
import tn.esprit.rh.achat.repositories.FournisseurRepository;
import tn.esprit.rh.achat.services.FournisseurServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DetailFournisseurServiceImplMockTest {

    @Mock
    private DetailFournisseurRepository detailFournisseurRepository;

    @Mock
    private FournisseurRepository fournisseurRepository;

    @InjectMocks
    private FournisseurServiceImpl fournisseurService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    DetailFournisseur detailFournisseur1 = new DetailFournisseur(1L,"four1@example.com",new Date(),"1 Rue de four1","MAT1",null);
    Fournisseur fournisseur1 = new Fournisseur(1L,"001","Fournisseur1", CategorieFournisseur.CONVENTIONNE,null,null,detailFournisseur1);

    DetailFournisseur detailFournisseur2 = new DetailFournisseur(2L,"four2@example.com",new Date(),"2 Rue de four2","MAT2",null);
    Fournisseur fournisseur2 = new Fournisseur(1L,"002","Fournisseur2", CategorieFournisseur.CONVENTIONNE,null,null,detailFournisseur2);

    DetailFournisseur detailFournisseur3 = new DetailFournisseur(3L,"four3@example.com",new Date(),"3 Rue de four3","MAT3",null);
    Fournisseur fournisseur3 = new Fournisseur(1L,"003","Fournisseur3", CategorieFournisseur.CONVENTIONNE,null,null,detailFournisseur3);


    List<Fournisseur> fournisseurs = new ArrayList<Fournisseur>() {
        {
            add(fournisseur1);
            add(fournisseur2);
            add(fournisseur3);
        }
    };
    List<DetailFournisseur> detailsfournisseurs = new ArrayList<DetailFournisseur>() {
        {
            add(detailFournisseur1);
            add(detailFournisseur2);
            add(detailFournisseur3);
        }
    };
    @Test
    void testAddFournisseur() {

        Mockito.when(fournisseurRepository.save(any(Fournisseur.class)))
                .thenReturn(fournisseur1)
                .thenReturn(fournisseur2)
                .thenReturn(fournisseur3);

        Fournisseur savedFournisseur1 = fournisseurService.addFournisseur(fournisseur1);
        Fournisseur savedFournisseur2 = fournisseurService.addFournisseur(fournisseur2);
        Fournisseur savedFournisseur3 = fournisseurService.addFournisseur(fournisseur3);

        assertNotNull(savedFournisseur1);
        assertEquals(fournisseur1.getDetailFournisseur(), savedFournisseur1.getDetailFournisseur());

        assertNotNull(savedFournisseur2);
        assertEquals(fournisseur2.getDetailFournisseur(), savedFournisseur2.getDetailFournisseur());

        assertNotNull(savedFournisseur3);
        assertEquals(fournisseur3.getDetailFournisseur(), savedFournisseur3.getDetailFournisseur());
    }
    @Test
    void testRetrieveAllFournisseurs() {
        Mockito.when(fournisseurRepository.findAll()).thenReturn(fournisseurs);
        List<Fournisseur> retrievedProducts = fournisseurService.retrieveAllFournisseurs();
        Assertions.assertEquals(3, retrievedProducts.size(), "The expected size is : " + fournisseurs.size());
    }
    @Test
    void testRetrieveFournisseur () {
        Mockito.when(fournisseurRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(fournisseur3));
        Fournisseur product1 = fournisseurService.retrieveFournisseur(3L);
        Assertions.assertNotNull(product1, "THE RETRIEVED FOURNISSEUR SHOULD NOT BE NULL§");
    }
    @Test
    void testRemoveProduct() {
        Long idFournissur = 1L;
        Mockito.doNothing().when(fournisseurRepository).deleteById(Mockito.anyLong());
        fournisseurService.deleteFournisseur(idFournissur);
        Mockito.verify(fournisseurRepository, Mockito.times(1)).deleteById(idFournissur);
    }
    @Test
    void testUpdateProduct() {
        Mockito.when(fournisseurRepository.save(Mockito.any(Fournisseur.class))).thenReturn(fournisseur3);
        Fournisseur updatedFournisseur = fournisseurService.updateFournisseur(fournisseur3);
        Assertions.assertEquals(fournisseur3.getIdFournisseur(), updatedFournisseur.getIdFournisseur(), "The updated fournisseur must have the same id as the old one");
        Assertions.assertNotNull(updatedFournisseur, "THE UPDATED FOURNISSEUR SHOULD NOT BE NULL!");
    }
}
