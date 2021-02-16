package woodspring.springapple.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class FXSpot implements Serializable{
	private static final Logger logger = LoggerFactory.getLogger(FXSpot.class);
	static final DecimalFormat f = new DecimalFormat("##.00000000");
	private String symbol;
	private String tenor;
	private String pxStr;
	private long quoteTime;
	private BigDecimal price;

	public FXSpot() {
		this.symbol = "";
		this.tenor = "ON";
		this.pxStr = "";
	}
	
	public FXSpot( String... args) {
		if ( args ==  null) {
			this.symbol = "USDCAD";
			this.tenor = "ON";
			this.pxStr = "1.345";
		} else {
		this.symbol = args[0];
		this.tenor = (args.length > 1) ? args[1] : "TOM";
		this.pxStr = (args.length > 2) ? args[2] : "0";
		}
		//logger.info("FXSpot: symbol:{}, tenor:{}, price:{}", this.symbol, this.tenor, this.pxStr);
		this.price = new BigDecimal(this.pxStr.trim()); // r.valueOf(Long.valueOf( this.pxStr.trim()).longValue());
	}
		

	//private int notation;
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
		this.pxStr = f.format(price);
	}
	public String getPriceString() {
		return pxStr;
	}
	
	public String getTenor() {
		return tenor;
	}
	public void setTenor(String tenor) {
		this.tenor = tenor;
	}
	public long getQuoteTime() {
		return quoteTime;
	}
	public void setQuoteTime(long quoteTime) {
		this.quoteTime = quoteTime;
	}
	@Override
	public String toString() {
		return "FXSpot [symbol=" + symbol + ", price=" + price + ", tenor=" + tenor + ", quoteTime=" + quoteTime + "]";
	}
}
