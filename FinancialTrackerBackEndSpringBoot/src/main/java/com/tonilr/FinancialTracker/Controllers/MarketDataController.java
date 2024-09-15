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
	@GetMapping("/get/{symbol}")
    public String getMarketDataBySymbolAPI(
    		@PathVariable("symbol") String symbol) {
		System.out.println(symbol);
		String marketDataJSON = apiServices.getStockData(symbol);
        return marketDataJSON;
        		//new ResponseEntity<>(marketData, HttpStatus.OK);
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
