package telran.converter.dto;

public class ConvertData {
	public String currencyFrom;
	public String currencyTo;
	public int amount;
	public String date;

	public String getCurrencyFrom() {
		return currencyFrom;
	}

	public String getCurrencyTo() {
		return currencyTo;
	}

	public int getAmount() {
		return amount;
	}

	public String getDate() {
		return date;
	}

	public ConvertData() {
	}
}
