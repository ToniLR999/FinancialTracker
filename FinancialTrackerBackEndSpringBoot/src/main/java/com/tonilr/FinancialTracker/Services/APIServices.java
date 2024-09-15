package com.tonilr.FinancialTracker.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class APIServices {
	 private final String API_KEY = "DMJY7MQ72LLNWTYS";  // Coloca tu API Key de Alpha Vantage aquí
	 private final String BASE_URL = "https://www.alphavantage.co/query";
	 

	    //@Autowired
	    //private RestTemplate restTemplate;

	 public String getStockData(String symbol) {
	        // Crear una instancia de RestTemplate
		    RestTemplate restTemplate = new RestTemplate();

	    	
	        // Construir la URL
	        String url = String.format("%s?function=TIME_SERIES_DAILY&symbol=%s&apikey=%s", BASE_URL, symbol, API_KEY);

	        System.out.println(url);
	        // Hacer la solicitud GET
	        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

	        // Devolver el cuerpo de la respuesta
	        return response.getBody();
	    }
}
