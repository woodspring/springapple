package woodspring.springapple.entities;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class FXSpot {
	
	static final DecimalFormat f = new DecimalFormat("##.00000000");
	private String symbol;
	private String tenor;
	private String pxStr;
	private long quoteTime;
	private BigDecimal price;
	
	
	public FXSpot( String... args) {
		this.symbol = args[0];
		this.tenor = args[1];
		this.pxStr = args[2];
		this.price = BigDecimal.valueOf(Long.valueOf( args[2].trim()).longValue());
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
	@Override
	public String toString() {
		return "FXSpot [symbol=" + symbol + ", price=" + price + ", tenor=" + tenor + ", quoteTime=" + quoteTime + "]";
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

}
