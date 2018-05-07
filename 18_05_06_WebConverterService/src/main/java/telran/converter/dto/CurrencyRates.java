package telran.converter.dto;

import java.util.HashMap;

public class CurrencyRates {
	private String base;
	private String date;
	private HashMap<String, Double> rates;

	public CurrencyRates() {
	}

	public CurrencyRates(String base, String date, HashMap<String, Double> rates) {
		this.base = base;
		this.date = date;
		this.rates = rates;
	}

	public String getBase() {
		return base;
	}

	public String getDate() {
		return date;
	}

	public HashMap<String, Double> getRates() {
		return rates;
	}

	@Override
	public String toString() {
		return "CurrencyRates [date=" + date + ", rates=" + rates + "]";
	}
}
