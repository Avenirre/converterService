package telran.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import telran.converter.dto.CurrencyCodes;
import telran.converter.dto.CurrencyRates;

public class Converter {
	public Converter(String date) {
		this.date = date;
		renewRates(date);
	}

	private static final Double BASE_RATE = 1.0;
	private RestTemplate restTemplate = new RestTemplate();
	private String url = "http://api.fixer.io/";
	private HttpHeaders headers = new HttpHeaders();
	private HttpEntity<String> requestEntity = new HttpEntity<>(headers);
	private String date;
	protected static CurrencyRates currencyRates;

	public static void setCurrencyRates(CurrencyRates currencyRates) {
		Converter.currencyRates = currencyRates;
	}
	
	private CurrencyRates renewRates(String date) {
		currencyRates = restTemplate.exchange(url+date, HttpMethod.GET, 
				requestEntity, CurrencyRates.class).getBody();
		currencyRates.getRates().put(currencyRates.getBase(), BASE_RATE);
		return currencyRates;
	}
	
	public CurrencyCodes getCurrencies() {
		CurrencyCodes currencyCodes = new CurrencyCodes();
		String[] codes = currencyRates.getRates().keySet().stream().toArray(String[]::new);
		currencyCodes.setCodes(codes);
		return currencyCodes;
	}

	public String getDate() {
		return currencyRates.getDate();
	}

	public double getResult(String currencyFrom, String currencyTo, int amount, String currentDate) {
		Map<String, Double> rates = new HashMap<>();
		if (currentDate.contains("latest") && 
				LocalDate.now().isAfter(LocalDate.parse(currencyRates.getDate()))) {
			Converter.setCurrencyRates(renewRates(LocalDate.now().toString()));
		}
		if (!currencyRates.getDate().equals(currentDate)) {
			Converter.setCurrencyRates(renewRates(currentDate));
		}
		rates = currencyRates.getRates();
		double result = amount * rates.get(currencyTo) / rates.get(currencyFrom);
		return result;
	}

}
