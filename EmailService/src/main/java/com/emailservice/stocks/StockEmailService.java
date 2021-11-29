package com.emailservice.stocks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.emailservice.bo.stocks.StockData;

@Service
public class StockEmailService{
	
	public static final String FROM = "emailsender1114@gmail.com";
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Scheduled(cron = "0 * * * * MON-FRI")
	public void smsDetailsOfNiftyStocksByDay() {
		final String url = "https://spring-example124.herokuapp.com/stocks/nifty50";
		StockData[] stockData = restTemplate.getForObject(url, StockData[].class);
		StringBuilder subject = new StringBuilder();
		for (StockData stockData2 : stockData) {
			subject.append(stockData2).append("\n");
		}
		sendSimpleMessage(subject.toString(), "Nifty 50 stock details", "belekar.varun96@gmail.com");
	}
	
	public void sendSimpleMessage(String data, String subject, String emailId) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom(FROM);
		simpleMailMessage.setTo(emailId);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(data);
		javaMailSender.send(simpleMailMessage);
	}
}
