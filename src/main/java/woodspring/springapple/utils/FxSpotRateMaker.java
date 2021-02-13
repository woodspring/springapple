package woodspring.springapple.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import woodspring.springapple.entities.FXSpot;

@Component
public class FxSpotRateMaker {
	
	private final static  Logger logger = LoggerFactory.getLogger(FxSpotRateMaker.class);
	
	private  ArrayList<String> symbolList = new ArrayList<>();
	private ArrayList<BigDecimal> priceList = new  ArrayList<>();
	private  ArrayList<String> priceStrList = new ArrayList<>();
	private  ArrayList<String> tenorStrList = new ArrayList<>();
	private int size =0;
	private int tenorSize =0;
	
	//private ConcurrentSkipListMap<FxSpotKey, FXSpot> quoteMap = new ConcurrentSkipListMap<>();
	//private ConcurrentSkipListMap<SymbolTenor, ConcurrentSkipListMap<String, ArrayList<FXSpot>>> quoteSTMap = new ConcurrentSkipListMap<>();
	
	private List<FXSpot> fxQuoteList = new ArrayList<FXSpot>();
	
	public FxSpotRateMaker() {
		symbolList.add("EURUSD");priceList.add(new BigDecimal("1.1805"));
		symbolList.add("USDCAD");priceList.add(new BigDecimal("1.3225"));
		symbolList.add("USDJPY");priceList.add(new BigDecimal("105.8860"));
		symbolList.add("CADJPY");priceList.add(new BigDecimal("80.0670"));
		symbolList.add("EURUSD");priceList.add(new BigDecimal("1.1805"));
		symbolList.add("USDCAD");priceList.add(new BigDecimal("1.3225"));
		symbolList.add("EURUSD");priceList.add(new BigDecimal("1.1805"));
		symbolList.add("USDCAD");priceList.add(new BigDecimal("1.3525"));
		symbolList.add("EURUSD");priceList.add(new BigDecimal("1.2005"));
		tenorStrList.add("ON");tenorStrList.add("TN"); tenorStrList.add("SN");tenorStrList.add("TOM");
		tenorStrList.add("1W");tenorStrList.add("1M");	tenorStrList.add("2W"); tenorStrList.add("2M");
		tenorStrList.add("3M");tenorStrList.add("6M");tenorStrList.add("9M");tenorStrList.add("1Y");
		tenorStrList.add("ON");tenorStrList.add("ON");tenorStrList.add("ON");tenorStrList.add("TN");
		tenorStrList.add("ON");tenorStrList.add("SN");tenorStrList.add("TOM");tenorStrList.add("1W");
		tenorStrList.add("ON");tenorStrList.add("ON");tenorStrList.add("ON");tenorStrList.add("ON");
		tenorStrList.add("ON");tenorStrList.add("ON");tenorStrList.add("ON");tenorStrList.add("ON");
		size = symbolList.size();
		tenorSize = tenorStrList.size();	
	}
	
	public FXSpot getSymbol( String... qouteParams) {
		//quoteParams : 0: symbol
		//              1: tenor
		int	ind = (qouteParams.length > 0) ? symbolList.indexOf( qouteParams[0]) :			
									(int)(Math.random() *  size);
		
		String tenor =  ( qouteParams.length > 1) ? qouteParams[1] : 
									tenorStrList.get( ((int) (Math.random() * tenorSize)));
		
		FXSpot retFx = new FXSpot();	
		//logger.info(" symbol:{}, ind:{} symbolList:{}, tenor:{}  ->{} length:{}", qouteParams[0], ind, symbolList.size(), tenor, qouteParams[1], qouteParams.length);
		retFx.setSymbol( symbolList.get(ind));
		int pluse = (int)(Math.random() *2);
		int rang = (int)(Math.random()*350);
		BigDecimal price = priceList.get(ind);
		BigDecimal value = price.multiply( new BigDecimal(rang* 0.000001));
		retFx.setPrice(((pluse == 0) ? price.subtract(value) : price.add( value)).setScale(6, BigDecimal.ROUND_DOWN));
		retFx.setQuoteTime( System.nanoTime());
		retFx.setTenor(tenor);
		priceList.remove(ind);
		priceList.add(ind, retFx.getPrice());
		priceList.set( ind,  retFx.getPrice());
		return retFx;
	}

	
	public List<FXSpot> getFXQuoteList(int qNum, String... params) {
		List<FXSpot> QuoteList = new ArrayList<>();
		FXSpot aFX = null;
		int pLen = (params == null) ? 0 : params.length;
		for ( int ind=0; ind< qNum; ind++) {
			switch ( pLen) {
			case 0: aFX = getSymbol(); break;
			case 1: aFX = getSymbol(params[0]); break;
			case 2: aFX = getSymbol(params[0], params[1]); break;
			default: aFX = getSymbol();
			}
			FXSpot aFXSpot = new FXSpot( aFX.getSymbol(), aFX.getTenor(), aFX.getPriceString());
			QuoteList.add( aFXSpot );
		}
		//igniteDao.putListToCache(listQuote);
		return QuoteList;
		
	}

}
