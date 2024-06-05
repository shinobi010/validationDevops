package tn.esprit.rh.achat.stock.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.repositories.StockRepository;
import tn.esprit.rh.achat.services.StockServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StockServiceTest {

    @InjectMocks
    private StockServiceImpl stockService;

    @Mock
    private StockRepository stockRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateStock() {
        // Given
        Stock stock = new Stock("Test Libelle", 100, 10);
        when(stockRepository.save(stock)).thenReturn(stock);

        // When
        Stock createdStock = stockService.addStock(stock);

        // Then
        assertEquals(stock, createdStock);
        verify(stockRepository, times(1)).save(stock);
    }

    @Test
    void testGetStockById() {
        // Given
        Long id = 1L;
        Stock stock = new Stock("Test Libelle", 100, 10);
        when(stockRepository.findById(id)).thenReturn(Optional.of(stock));

        // When
        Optional<Stock> retrievedStock = Optional.ofNullable(stockService.retrieveStock(id));

        // Then
        assertTrue(retrievedStock.isPresent());
        assertEquals(stock, retrievedStock.get());
        verify(stockRepository, times(1)).findById(id);
    }

    @Test
    void testGetAllStocks() {
        // Given
        Stock stock1 = new Stock("Libelle 1", 100, 10);
        Stock stock2 = new Stock("Libelle 2", 200, 20);
        List<Stock> stocks = Arrays.asList(stock1, stock2);
        when(stockRepository.findAll()).thenReturn(stocks);

        // When
        List<Stock> retrievedStocks = stockService.retrieveAllStocks();

        // Then
        assertEquals(2, retrievedStocks.size());
        assertEquals(stock1, retrievedStocks.get(0));
        assertEquals(stock2, retrievedStocks.get(1));
        verify(stockRepository, times(1)).findAll();
    }

    @Test
    void testUpdateStock() {
        // Given
        Stock stock = new Stock("Test Libelle", 100, 10);
        when(stockRepository.save(stock)).thenReturn(stock);

        // When
        Stock updatedStock = stockService.updateStock(stock);

        // Then
        assertEquals(stock, updatedStock);
        verify(stockRepository, times(1)).save(stock);
    }

    @Test
    void testDeleteStock() {
        // Given
        Long id = 1L;
        doNothing().when(stockRepository).deleteById(id);

        // When
        stockService.deleteStock(id);

        // Then
        verify(stockRepository, times(1)).deleteById(id);
    }
}
