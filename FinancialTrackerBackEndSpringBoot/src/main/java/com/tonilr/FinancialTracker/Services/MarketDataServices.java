package com.tonilr.FinancialTracker.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tonilr.FinancialTracker.Entities.AssetType;
import com.tonilr.FinancialTracker.Entities.MarketData;
import com.tonilr.FinancialTracker.repos.MarketDataRepo;

@Service
public class MarketDataServices {
	
    @Autowired
    private final MarketDataRepo marketDataRepository;
    
	public MarketDataServices(MarketDataRepo marketDataRepository) {
		this.marketDataRepository = marketDataRepository;
	}

    public MarketData addMarketData(MarketData marketData) {
        return marketDataRepository.save(marketData);
    }

    public List<MarketData> findMarketDataBySymbol(String symbol, AssetType assetType) {
        return marketDataRepository.findBySymbolAndAssetType(symbol, assetType);
    }
    
    public void deleteMarketData(Long id) {
         marketDataRepository.deleteById(id);
    }

}
