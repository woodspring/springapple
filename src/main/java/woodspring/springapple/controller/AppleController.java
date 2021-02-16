package woodspring.springapple.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import woodspring.springapple.entities.FXSpot;

import woodspring.springapple.Service.impl.AppleServiceImpl;

@RestController
@RequestMapping(value="/apple/")
public class AppleController {
	final static DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
	
	@Autowired
	AppleServiceImpl appleImpl;
	
	@GetMapping("listFX") 
	public List<FXSpot> getFXSpot() {
		 return appleImpl.getFxQoute();
		
	}
	
	@GetMapping("quote/{symbol}")
	public List<FXSpot> getQuote(@PathVariable String symbol) {
		return appleImpl.quote( symbol);
	}
	
	@GetMapping("quote")
	public List<FXSpot> getQuoteWithTenor( @RequestParam(name = "symbol") String symbol, @RequestParam(name="tenor") String tenor) {
		return appleImpl.quoteWithTenor(symbol, tenor);
	}

	//@GetMapping("short") 
	//public ResponseEntity<List<FXSpot>> shortList(RequestEntity reqEntity) {
		//HttpHeader
	//}
	
	@GetMapping("format") 
	public ResponseEntity listWithHeader( @RequestParam(name= "format") String reqFormat) {
		//ResponseEntity<List<FXSpot>> resp;
		//if ( reqFormat.equalsIgnoreCase("short")) {
		//	ResponseEntity<List<FXSpot>>.ok(appleImpl.getFxQoute(), )
		//}
		//ResponseEntity<List<FXSpot>> resp = new ResponseEntity
		if ( reqFormat.equalsIgnoreCase("long")) {
			String currTime = df.format( new Date( System.currentTimeMillis()));
			HttpHeaders theHeaders = new HttpHeaders();
			theHeaders.set("application",  "OK");
			theHeaders.set("currentTime",  currTime);
			return ResponseEntity.ok().headers( theHeaders).body(appleImpl.getFxQoute() );
			
		} else if ( reqFormat.equalsIgnoreCase("short")){
			return ResponseEntity.status(HttpStatus.OK).body("\"status\":\"OK\"\n"+appleImpl.getFxQoute().toString());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("code 400(\"Bad Request\")");
	}
}
