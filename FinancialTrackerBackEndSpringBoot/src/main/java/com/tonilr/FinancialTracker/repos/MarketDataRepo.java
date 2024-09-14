package com.tonilr.FinancialTracker.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tonilr.FinancialTracker.Entities.AssetType;
import com.tonilr.FinancialTracker.Entities.MarketData;

import java.util.List;

@Repository
public interface MarketDataRepo extends JpaRepository<MarketData,Long>{

	// Consulta para encontrar datos por símbolo y tipo de activo
    List<MarketData> findBySymbolAndAssetType(String symbol, AssetType assetType);
}
