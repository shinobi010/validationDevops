package tn.esprit.rh.achat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.rh.achat.entities.Operateur;
import tn.esprit.rh.achat.repositories.OperateurRepository;
import tn.esprit.rh.achat.services.OperateurServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class OperateurServiceImplMockTest {
    @Mock
    OperateurRepository operateurRepository;
    @InjectMocks
    OperateurServiceImpl operateurService;

    Operateur op = new Operateur(1L, "sn", "marwen", "marwen", null);
    List<Operateur> operateurList = new ArrayList<Operateur>(){
        {
            add(new Operateur(2L, "sn", "sn", "sn", null));
            add(new Operateur(3L, "shinobi", "shinobi", "shinobi", null));
        }
    };
    @Test
    public void testRetrieveOperateur() {
        Mockito.when(operateurRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(op));
        Operateur op1 = operateurService.retrieveOperateur(2L);
        Assertions.assertNotNull(op1);
    }
    @Test
    public void testAddOperateur() {
        Mockito.when(operateurRepository.save(Mockito.any(Operateur.class))).thenReturn(op);
        Operateur savedOperateur = operateurService.addOperateur(op);
        Assertions.assertNotNull(savedOperateur);
        Assertions.assertEquals(op.getIdOperateur(), savedOperateur.getIdOperateur());
    }
    @Test
    public void testDeleteOperateur() {
        Mockito.doNothing().when(operateurRepository).deleteById(Mockito.anyLong());
        operateurService.deleteOperateur(1L);
        Mockito.verify(operateurRepository, Mockito.times(1)).deleteById(Mockito.anyLong());
    }
    @Test
    public void testUpdateOperateur() {
        Mockito.when(operateurRepository.save(Mockito.any(Operateur.class))).thenReturn(op);
        Operateur updatedOperateur = operateurService.updateOperateur(op);
        Assertions.assertNotNull(updatedOperateur);
        Assertions.assertEquals(op.getIdOperateur(), updatedOperateur.getIdOperateur());
    }
    @Test
    public void testRetrieveAllOperateurs() {
        Mockito.when(operateurRepository.findAll()).thenReturn(operateurList);
        List<Operateur> operateurs = operateurService.retrieveAllOperateurs();
        Assertions.assertNotNull(operateurs);
        Assertions.assertEquals(2, operateurs.size());
    }
}
