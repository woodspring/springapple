package woodspring.springapple.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import woodspring.springapple.entities.FXSpot;

import woodspring.springapple.Service.impl.AppleServiceImpl;

@Controller
@RequestMapping(value="/apple/")
public class AppleController {
	
	@Autowired
	AppleServiceImpl appleImpl;
	
	@GetMapping("listFX") 
	@ResponseBody
	public List<FXSpot> getFXSpot() {
		 return appleImpl.getFxQoute();
		
	}

}
