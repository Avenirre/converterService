package telran.controller;

import java.time.LocalDate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import telran.converter.dto.ConvertData;
import telran.converter.dto.ConverterRestApi;
import telran.converter.dto.CurrencyCodes;

@SpringBootApplication
@RestController
public class ServiceStarter {
	
Converter converter = new Converter(LocalDate.now().toString());
	
	@RequestMapping(value = ConverterRestApi.CURRENCIES)
	CurrencyCodes getCurrencies(){
	return converter.getCurrencies();
}
	
	@RequestMapping(value = ConverterRestApi.DATE)
	String getDate(){
	return converter.getDate();
}
	
	@PostMapping(value = ConverterRestApi.CONVERT)
	double getResult(@RequestBody ConvertData data){
		return converter.getResult(data.currencyFrom, data.currencyTo, data.amount, data.date);
	}
	public static void main(String[] args) {
		SpringApplication.run(ServiceStarter.class, args);
	}

}
