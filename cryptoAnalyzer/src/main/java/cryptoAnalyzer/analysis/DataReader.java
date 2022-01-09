package cryptoAnalyzer.analysis;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


/*
 * Singleton Design Pattern
 */
/**
 * This DataReader class reads data from Gecko's API
 * @author Yanwe
 * @since 2021-11-28
 */
public class DataReader {
	
	private DataReader instance;
	private double price;
	private double MarketCap;
	private double volume;	
	private JsonObject data;
		
	public DataReader(String id, String date) {
		/**
		 * this method is used to get data, price, marketcapitalizationa and volume from API
		 * @param id, date
		 * @return nothing
		 */
		date = date.replace("/", "-");
		this.data = this.getDataFromAPI(id, date);
		this.price = this.setPrice(id, date);
		this.MarketCap = this.setMarketCap(id, date);
		this.volume = this.setVolume(id, date);		
	}
	
	
	public DataReader getInstance(String id, String date) {
		/**
		 * this method is used to get data, price, marketcapitalizationa and volume from API
		 * @param id, date
		 * @return nothing
		 */
		date = date.replace("/", "-");
		if (instance == null) {
			instance = new DataReader(id, date);
		}		
		return instance;	
	}
	
	
	private JsonObject getDataFromAPI(String id, String date) {
		/**
		 * this method is used to get data from API
		 * @param id, date
		 * @return jsonObject data
		 */
		date = date.replace("/", "-");

		String urlString = String.format(
				"https://api.coingecko.com/api/v3/coins/%s/history?date=%s", id, date);
		
		try {
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			int responsecode = conn.getResponseCode();
			if (responsecode == 200) {
				String inline = "";
				Scanner sc = new Scanner(url.openStream());
				while (sc.hasNext()) {
					inline += sc.nextLine();
				}
				sc.close();
				JsonObject jsonObject = new JsonParser().parse(inline).getAsJsonObject();
				return jsonObject;
			}

		} catch (IOException e) {
			System.out.println("Something went wrong with the API call.");
		}
		return null;
	}
	
	
	public double getMarketCap() {
		/**
		 * this method is used to get marketcapitalizationa from API
		 * @param nothing
		 * @return marketcapitalizationa
		 */
		return MarketCap;
	}

	public double getPrice() {
		/**
		 * this method is used to get price from API
		 * @param nothing
		 * @return price
		 */
		return price;
	}

	public double getVolume() {
		/**
		 * this method is used to get volume from API
		 * @param nothing
		 * @return volume
		 */
		return volume;
	}

	public double setMarketCap(String id, String date) {
		/**
		 * this method is used to set marketcapitalizationa from API based on the id and date
		 * @param id, date
		 * @return marketcapitalizationa
		 */
		Double marketCaps = 0.0;
		date = date.replace("/", "-");
		JsonObject jsonObject = getDataFromAPI(id, date);
		if (jsonObject != null) {
			JsonObject marketData = jsonObject.get("market_data").getAsJsonObject();
			JsonObject currentPrice = marketData.get("market_cap").getAsJsonObject();
			marketCaps = currentPrice.get("cad").getAsDouble();
		}
	
		return marketCaps;
	}

	private double setPrice(String id, String date) {
		/**
		 * this method is used to set price from API based on the id and date
		 * @param id, date
		 * @return prices
		 */
		
		double prices = 0.0;
		date = date.replace("/", "-");
		JsonObject jsonObject = getDataFromAPI(id, date);
		if (jsonObject != null) {
			JsonObject marketData = jsonObject.get("market_data").getAsJsonObject();
			JsonObject currentPrice = marketData.get("current_price").getAsJsonObject();
			prices = currentPrice.get("cad").getAsDouble();
		}
		
		return prices;
	}

	public double setVolume(String id, String date) {
		/**
		 * this method is used to set volume from API based on the id and date
		 * @param id, date
		 * @return volumes
		 */
		double volumes = 0.0;
		date = date.replace("/", "-");
		JsonObject jsonObject = getDataFromAPI(id, date);
		if (jsonObject != null) {
			JsonObject marketData = jsonObject.get("market_data").getAsJsonObject();
			JsonObject currentPrice = marketData.get("total_volume").getAsJsonObject();
			volumes = currentPrice.get("cad").getAsDouble();
		}
		
		return volumes;
	}
	
	
}
