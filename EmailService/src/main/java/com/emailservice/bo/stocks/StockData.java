package com.emailservice.bo.stocks;

import lombok.*;

@Data
public class StockData{
	private String companyName;

	private String industry;

	private String currentMarketPrice;

	private double averageCostPrice;

	private double peRatio;
}
