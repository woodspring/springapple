package woodspring.springapple.Service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import woodspring.springapple.Service.AppleService;
import woodspring.springapple.entities.FXSpot;
import woodspring.springapple.repositoryies.SimFxQuoteRepository;

@Service
public class AppleServiceImpl implements AppleService {
	private static final Logger logger = LoggerFactory.getLogger(AppleServiceImpl.class );
	
	@Autowired
	SimFxQuoteRepository fxQuoteRepo;
	
	public List<FXSpot> getFxQoute() {
		return fxQuoteRepo.getQuote();
	}
	
	public List<FXSpot> getFxQouteBySymbol(String symbol) {
		return fxQuoteRepo.getQuoteBySymbol(symbol);	
	}
	
	public List<FXSpot> getFxQouteBySymbolTenor(String symbol, String tenor) {
		return fxQuoteRepo.getQuoteBySymbolTenor(symbol,  tenor);	
	}
	
	public List<FXSpot> listFxQouteBySymbolTenor(String symbol, String tenor) {
		return fxQuoteRepo.getQuoteBySymbolTenor(symbol,  tenor);	
	}
	
	public FXSpot postFXSpot(FXSpot fxSpot) {
		return fxSpot;
	}

	public FXSpot putFXSpot(FXSpot fxSpot) {
		return fxSpot;
	}

}
