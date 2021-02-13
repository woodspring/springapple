package woodspring.springapple.repositoryies;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import woodspring.springapple.entities.FXSpot;
import woodspring.springapple.utils.FxSpotRateMaker;

@Component
public class SimFxQuoteRepository {
	
	private final static Logger logger = LoggerFactory.getLogger(SimFxQuoteRepository.class);
	
	List<FXSpot> fxSpotList = new ArrayList<FXSpot>();
	Comparator<FXSpot> orderByKey = Comparator.comparing(FXSpot::getSymbol)
			.thenComparing(FXSpot::getTenor).thenComparing(FXSpot::getPrice);
	
	@Autowired 
	FxSpotRateMaker fxSpotMaker;
	
	public List<FXSpot> getQuote() {
		int qNum  = (int)(Math.random() *2000);
		fxSpotList =  fxSpotMaker.getFXQuoteList( qNum, null);
		return fxSpotList;
	}
	
	public List<FXSpot> getQuoteBySymbol(String symbol) {
		int qNum  = (int)(Math.random() *1000);
		return fxSpotMaker.getFXQuoteList( qNum, symbol);		
	}

	public List<FXSpot> getQuoteBySymbolTenor(String symbol, String tenor) {
		int qNum  = (int)(Math.random() *500);
		return fxSpotMaker.getFXQuoteList( qNum, symbol, tenor);		
	}
	

	public List<FXSpot> listQuoteSorted() {
		fxSpotList.addAll( getQuote().stream().sorted(orderByKey).collect(Collectors.toList()));
		return fxSpotList;
	}

	public List<FXSpot> listQuoteSymbol(String symbol) {
		fxSpotList.addAll( getQuoteBySymbol(symbol));
		return fxSpotList.stream().sorted(orderByKey).collect(Collectors.toList());
		
	}
	public List<FXSpot> listQuoteSymbolTenor(String symbol, String tenor) {
		fxSpotList.addAll( getQuoteBySymbolTenor(symbol, tenor));
		return fxSpotList.stream().sorted(orderByKey).collect(Collectors.toList());
	}
	
	


}
