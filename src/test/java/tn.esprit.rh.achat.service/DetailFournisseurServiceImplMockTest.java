package tn.esprit.rh.achat.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.rh.achat.entities.DetailFournisseur;
import tn.esprit.rh.achat.entities.Fournisseur;
import tn.esprit.rh.achat.repositories.DetailFournisseurRepository;
import tn.esprit.rh.achat.repositories.FournisseurRepository;
import tn.esprit.rh.achat.services.FournisseurServiceImpl;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

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

    @Test
    void testAddDetailFournisseur() {
        // Création d'un détail fournisseur simulé
        DetailFournisseur detailFournisseur = new DetailFournisseur();
        detailFournisseur.setEmail("test@example.com");
        detailFournisseur.setDateDebutCollaboration(new Date());
        detailFournisseur.setAdresse("123 Rue de Test");
        detailFournisseur.setMatricule("MAT123");
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setDetailFournisseur(detailFournisseur);
        fournisseur.setLibelle("Fournisseur1");

        // Simuler le comportement du repository lorsqu'il sauvegarde le détail fournisseur
        when(detailFournisseurRepository.save(any(DetailFournisseur.class))).thenReturn(detailFournisseur);

        // Appeler la méthode pour ajouter un détail fournisseur
        Fournisseur savedFournisseur = fournisseurService.addFournisseur(fournisseur);

        // Vérifier si le détail fournisseur a été sauvegardé correctement
        assertNotNull(savedFournisseur);
        assertEquals(fournisseur.getDetailFournisseur(), savedFournisseur.getDetailFournisseur());
    }
}
