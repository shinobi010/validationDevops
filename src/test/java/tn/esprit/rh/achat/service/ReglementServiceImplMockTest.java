package tn.esprit.rh.achat.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.rh.achat.entities.Reglement;
import tn.esprit.rh.achat.repositories.FactureRepository;
import tn.esprit.rh.achat.repositories.ReglementRepository;
import tn.esprit.rh.achat.services.ReglementServiceImpl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@extendWith(MockitoExtension.class)
public class ReglementServiceImplMock {

    @InjectMocks
    private ReglementServiceImpl reglementService;

    @Mock
    private ReglementRepository reglementRepository;

    @Mock
    private FactureRepository factureRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRetrieveAllReglements() {
        // Arrange
        Reglement reglement1 = new Reglement(1L, 100.0f, 50.0f, true, new Date(), null);
        Reglement reglement2 = new Reglement(2L, 200.0f, 0.0f, false, new Date(), null);
        List<Reglement> reglements = Arrays.asList(reglement1, reglement2);
        when(reglementRepository.findAll()).thenReturn(reglements);

        // Act
        List<Reglement> result = reglementService.retrieveAllReglements();

        // Assert
        assertEquals(2, result.size());
        verify(reglementRepository, times(1)).findAll();
    }

    @Test
    void testAddReglement() {
        // Arrange
        Reglement reglement = new Reglement(1L, 100.0f, 50.0f, true, new Date(), null);
        when(reglementRepository.save(reglement)).thenReturn(reglement);

        // Act
        Reglement result = reglementService.addReglement(reglement);

        // Assert
        assertNotNull(result);
        assertEquals(100.0f, result.getMontantPaye());
        verify(reglementRepository, times(1)).save(reglement);
    }

    @Test
    void testRetrieveReglement() {
        // Arrange
        Reglement reglement = new Reglement(1L, 100.0f, 50.0f, true, new Date(), null);
        when(reglementRepository.findById(1L)).thenReturn(Optional.of(reglement));

        // Act
        Reglement result = reglementService.retrieveReglement(1L);

        // Assert
        assertNotNull(result);
        assertEquals(100.0f, result.getMontantPaye());
        verify(reglementRepository, times(1)).findById(1L);
    }

    @Test
    void testRetrieveReglementByFacture() {
        // Arrange
        Long idFacture = 1L;
        Reglement reglement1 = new Reglement(1L, 100.0f, 50.0f, true, new Date(), null);
        Reglement reglement2 = new Reglement(2L, 200.0f, 0.0f, false, new Date(), null);
        List<Reglement> reglements = Arrays.asList(reglement1, reglement2);
        when(reglementRepository.retrieveReglementByFacture(idFacture)).thenReturn(reglements);

        // Act
        List<Reglement> result = reglementService.retrieveReglementByFacture(idFacture);

        // Assert
        assertEquals(2, result.size());
        verify(reglementRepository, times(1)).retrieveReglementByFacture(idFacture);
    }

    @Test
    void testGetChiffreAffaireEntreDeuxDate() {
        // Arrange
        Date startDate = new Date();
        Date endDate = new Date();
        when(reglementRepository.getChiffreAffaireEntreDeuxDate(startDate, endDate)).thenReturn(1000.0f);

        // Act
        float result = reglementService.getChiffreAffaireEntreDeuxDate(startDate, endDate);

        // Assert
        assertEquals(1000.0f, result);
        verify(reglementRepository, times(1)).getChiffreAffaireEntreDeuxDate(startDate, endDate);
    }
}
