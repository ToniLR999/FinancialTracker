package com.tonilr.FinancialTracker.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
@Table(name = "Market_Data")
public class MarketData {
	  	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(nullable = false)
	    private String symbol; // Símbolo del activo (AAPL, BTC, EURUSD)

	    @Enumerated(EnumType.STRING)
	    @Column(nullable = false)
	    private AssetType assetType; // Tipo de activo (STOCK, CRYPTO, FOREX)

	    @Column(nullable = false)
	    private LocalDate date; // Fecha del registro de los precios

	    @Column(precision = 19, scale = 4) // Ej. 12345.6789
	    private BigDecimal open; // Precio de apertura

	    @Column(precision = 19, scale = 4)
	    private BigDecimal high; // Precio máximo

	    @Column(precision = 19, scale = 4)
	    private BigDecimal low; // Precio mínimo

	    @Column(precision = 19, scale = 4)
	    private BigDecimal close; // Precio de cierre

	    @Column
	    private BigDecimal volume; // Volumen de transacciones (opcional, puede ser nulo para divisas)

	    @Column
	    private String market; // Para cryptos, mercado en que se comercializa (ej. USD, BTC)

	    @Column(name = "base_currency")
	    private String baseCurrency; // Para Forex, la divisa base (ej. USD)

	    // Getters y Setters
	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getSymbol() {
	        return symbol;
	    }

	    public void setSymbol(String symbol) {
	        this.symbol = symbol;
	    }

	    public AssetType getAssetType() {
	        return assetType;
	    }

	    public void setAssetType(AssetType assetType) {
	        this.assetType = assetType;
	    }

	    public LocalDate getDate() {
	        return date;
	    }

	    public void setDate(LocalDate date) {
	        this.date = date;
	    }

	    public BigDecimal getOpen() {
	        return open;
	    }

	    public void setOpen(BigDecimal open) {
	        this.open = open;
	    }

	    public BigDecimal getHigh() {
	        return high;
	    }

	    public void setHigh(BigDecimal high) {
	        this.high = high;
	    }

	    public BigDecimal getLow() {
	        return low;
	    }

	    public void setLow(BigDecimal low) {
	        this.low = low;
	    }

	    public BigDecimal getClose() {
	        return close;
	    }

	    public void setClose(BigDecimal close) {
	        this.close = close;
	    }

	    public BigDecimal getVolume() {
	        return volume;
	    }

	    public void setVolume(BigDecimal volume) {
	        this.volume = volume;
	    }

	    public String getMarket() {
	        return market;
	    }

	    public void setMarket(String market) {
	        this.market = market;
	    }

	    public String getBaseCurrency() {
	        return baseCurrency;
	    }

	    public void setBaseCurrency(String baseCurrency) {
	        this.baseCurrency = baseCurrency;
	    }
	
}
