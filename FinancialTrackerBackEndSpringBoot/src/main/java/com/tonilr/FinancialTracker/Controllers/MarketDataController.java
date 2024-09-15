package com.tonilr.FinancialTracker.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tonilr.FinancialTracker.Entities.AssetType;
import com.tonilr.FinancialTracker.Entities.MarketData;
import com.tonilr.FinancialTracker.Services.APIServices;
import com.tonilr.FinancialTracker.Services.MarketDataServices;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/marketdata")
public class MarketDataController {

	@Autowired
    private final MarketDataServices marketDataService;
	
    @Autowired
    private APIServices apiServices;
	
	public MarketDataController(MarketDataServices marketDataService) {
		this.marketDataService = marketDataService;
	}

    // Endpoint para guardar un nuevo registro de MarketData
	@PostMapping("/add")
    public ResponseEntity<MarketData> addMarketData(@RequestBody MarketData marketData) {
		MarketData newMarketData = marketDataService.addMarketData(marketData);
        return new ResponseEntity<>(newMarketData, HttpStatus.CREATED);
    }

    // Endpoint para obtener MarketData por símbolo y tipo de activo
	@GetMapping("/getStock/{symbol}")
    public ResponseEntity<String> getStockDataBySymbolAPI(
    		@PathVariable("symbol") String symbol) {
		  try {
		        String data = apiServices.getStockData(symbol);
        return ResponseEntity.ok(data);
		    } catch (Exception e) {
		        // Manejo de errores
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener datos del mercado: " + e.getMessage());
		    }
        		//new ResponseEntity<>(marketData, HttpStatus.OK);
    }
	
    // Endpoint para obtener datos de Forex por símbolo
    @GetMapping("/getForex/{fromSymbol}/{toSymbol}")
    public ResponseEntity<String> getForexDataBySymbolsAPI(
            @PathVariable("fromSymbol") String fromSymbol,
            @PathVariable("toSymbol") String toSymbol) {
        try {
            String data = apiServices.getForexData(fromSymbol, toSymbol);
            return ResponseEntity.ok(data);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener datos de Forex: " + e.getMessage());
        }
    }
    
    // Endpoint para obtener datos de Forex por símbolo
    @GetMapping("/getCrypto/{symbol}/{market}")
    public ResponseEntity<String> getCryptoDataBySymbolsAPI(
            @PathVariable("symbol") String symbol,
            @PathVariable("market") String market) {
        try {
            String data = apiServices.getCryptoData(symbol, market);
            return ResponseEntity.ok(data);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener datos de Forex: " + e.getMessage());
        }
    }
	
	@GetMapping("/find/{symbol}/{assetType}")
    public ResponseEntity<List<MarketData>> findMarketDataBySymbol(
    		@PathVariable("symbol") String symbol, 
    		@PathVariable("assetType") AssetType assetType) {
		List<MarketData> marketData = marketDataService.findMarketDataBySymbol(symbol, assetType);
        return new ResponseEntity<>(marketData, HttpStatus.OK);
    }
    
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteMarketData(@PathVariable("id") Long id) {
		marketDataService.deleteMarketData(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
