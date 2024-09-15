package com.tonilr.FinancialTracker.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tonilr.FinancialTracker.Entities.AssetType;
import com.tonilr.FinancialTracker.Entities.MarketData;
import com.tonilr.FinancialTracker.repos.MarketDataRepo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Iterator;

@Service
public class APIServices {
	 private final String API_KEY = "DMJY7MQ72LLNWTYS";  // Coloca tu API Key de Alpha Vantage aquí
	 private final String BASE_URL = "https://www.alphavantage.co/query";
	 

	    @Autowired
	    private MarketDataRepo marketDataRepo;  // Inyectar el repositorio
	    
	    //@Autowired
	    //private RestTemplate restTemplate;

	 public String getStockData(String symbol){
	        // Crear una instancia de RestTemplate
		    RestTemplate restTemplate = new RestTemplate();

	    	
	        // Construir la URL
	        String url = String.format("%s?function=TIME_SERIES_DAILY&symbol=%s&apikey=%s", BASE_URL, symbol, API_KEY);

	        System.out.println("URL generada: "+url);
	        
	        // Hacer la solicitud GET
	        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

	        
	        try {
	            ObjectMapper mapper = new ObjectMapper();
	            JsonNode root = mapper.readTree(response.getBody());
	            JsonNode timeSeries = root.path("Time Series (Daily)");

	            Iterator<String> dates = timeSeries.fieldNames();
	            while (dates.hasNext()) {
	                String dateString = dates.next();
	                LocalDate date = LocalDate.parse(dateString);
	                JsonNode dailyData = timeSeries.path(dateString);

	                MarketData marketData = new MarketData();
	                marketData.setSymbol(symbol);
	                marketData.setAssetType(AssetType.STOCK);
	                marketData.setDate(date);
	                marketData.setOpen(new BigDecimal(dailyData.path("1. open").asText()));
	                marketData.setHigh(new BigDecimal(dailyData.path("2. high").asText()));
	                marketData.setLow(new BigDecimal(dailyData.path("3. low").asText()));
	                marketData.setClose(new BigDecimal(dailyData.path("4. close").asText()));
	                marketData.setVolume(new BigDecimal(dailyData.path("5. volume").asText()));


	                marketDataRepo.save(marketData);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            // Manejo de errores
	        }
	        // Devolver el cuerpo de la respuesta
	        return response.getBody();
	    }
	 

	    public String getForexData(String fromSymbol, String toSymbol) {
	        // Crear una instancia de RestTemplate
	        RestTemplate restTemplate = new RestTemplate();

	        // Construir la URL
	        String url = String.format("%s?function=FX_DAILY&from_symbol=%s&to_symbol=%s&apikey=%s", BASE_URL, fromSymbol, toSymbol, API_KEY);

	        System.out.println("URL generada: " + url);

	        // Hacer la solicitud GET
	        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
	        try {
	            ObjectMapper mapper = new ObjectMapper();
	            JsonNode root = mapper.readTree(response.getBody());
	            JsonNode timeSeries = root.path("Time Series FX (Daily)");

	            Iterator<String> dates = timeSeries.fieldNames();
	            while (dates.hasNext()) {
	                String dateString = dates.next();
	                LocalDate date = LocalDate.parse(dateString);
	                JsonNode dailyData = timeSeries.path(dateString);

	                MarketData marketData = new MarketData();
	                marketData.setSymbol(fromSymbol + "/" + toSymbol); // El símbolo combinado
	                marketData.setAssetType(AssetType.FOREX);
	                marketData.setDate(date);

	                // Manejo de valores vacíos y nulos
	                String openValue = dailyData.path("1. open").asText(null);
	                String highValue = dailyData.path("2. high").asText(null);
	                String lowValue = dailyData.path("3. low").asText(null);
	                String closeValue = dailyData.path("4. close").asText(null);
	                String volumeValue = dailyData.path("5. volume").asText(null);

	                // Validar si el valor no es nulo ni vacío antes de convertirlo a BigDecimal
	                marketData.setOpen(openValue != null && !openValue.isEmpty() ? new BigDecimal(openValue) : null);
	                marketData.setHigh(highValue != null && !highValue.isEmpty() ? new BigDecimal(highValue) : null);
	                marketData.setLow(lowValue != null && !lowValue.isEmpty() ? new BigDecimal(lowValue) : null);
	                marketData.setClose(closeValue != null && !closeValue.isEmpty() ? new BigDecimal(closeValue) : null);
	                marketData.setVolume(volumeValue != null && !volumeValue.isEmpty() ? new BigDecimal(volumeValue) : null);

	                marketDataRepo.save(marketData);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            return "Error en la conversión de datos: " + e.getMessage();
	        }
	        
	        // Devolver el cuerpo de la respuesta
	        return response.getBody();
	    }
	    
	    public String getCryptoData(String symbol, String market) {
	    	 // Crear una instancia de RestTemplate
	        RestTemplate restTemplate = new RestTemplate();

	        // Construir la URL
	        String url = String.format("%s?function=DIGITAL_CURRENCY_DAILY&symbol=%s&market=%s&apikey=%s", BASE_URL, symbol, market, API_KEY);

	        System.out.println("URL generada: " + url);

	        // Hacer la solicitud GET
	        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
	        
	        try {
	            ObjectMapper mapper = new ObjectMapper();
	            JsonNode root = mapper.readTree(response.getBody());
	            JsonNode timeSeries = root.path("Time Series (Digital Currency Daily)");

	            // Verificar si la respuesta contiene datos
	            if (timeSeries.isMissingNode() || timeSeries.isEmpty()) {
	                System.err.println("No se encontraron datos en la respuesta para " + symbol);
	                return "No se encontraron datos en la respuesta para " + symbol;
	            }

	            Iterator<String> dates = timeSeries.fieldNames();
	            while (dates.hasNext()) {
	                String dateString = dates.next();
	                LocalDate date = LocalDate.parse(dateString);
	                JsonNode dailyData = timeSeries.path(dateString);

	                MarketData marketData = new MarketData();
	                marketData.setSymbol(symbol);
	                marketData.setAssetType(AssetType.CRYPTO);
	                marketData.setDate(date);
	                marketData.setMarket(market);

	                // Manejo de valores vacíos y nulos
	                String openValue = dailyData.path("1. open").asText(null);
	                String highValue = dailyData.path("2. high").asText(null);
	                String lowValue = dailyData.path("3. low").asText(null);
	                String closeValue = dailyData.path("4. close").asText(null);
	                String volumeValue = dailyData.path("5. volume").asText(null);
		        	                
	                try {
	                	
		                // Validar si el valor no es nulo ni vacío antes de convertirlo a BigDecimal
		                marketData.setOpen(openValue != null && !openValue.isEmpty() ? new BigDecimal(openValue) : null);
		                marketData.setHigh(highValue != null && !highValue.isEmpty() ? new BigDecimal(highValue) : null);
		                marketData.setLow(lowValue != null && !lowValue.isEmpty() ? new BigDecimal(lowValue) : null);
		                marketData.setClose(closeValue != null && !closeValue.isEmpty() ? new BigDecimal(closeValue) : null);
		                marketData.setVolume(volumeValue != null && !volumeValue.isEmpty() ? new BigDecimal(volumeValue) : null);
	                } catch (NumberFormatException e) {
	                    System.err.println("Error al convertir los datos numéricos para la fecha " + dateString + ": " + e.getMessage());
	                    continue; // Saltar a la siguiente fecha si hay un error en la conversión
	                }

	                marketDataRepo.save(marketData);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            // Manejo de errores
	            return "Error en la conversión de datos: " + e.getMessage();
	        }

	        // Devolver el cuerpo de la respuesta
	        return response.getBody();
	    }
}