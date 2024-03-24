package com.jaguarpetroleum.JgasRegistration.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jaguarpetroleum.JgasRegistration.Model.StaffDiscount;
import com.jaguarpetroleum.JgasRegistration.Service.StaffDiscountService;

import net.minidev.json.JSONObject;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
public class StaffDiscountController {
	private static final Logger logger = LoggerFactory.getLogger(StaffDiscountController.class);
	@Autowired
	private StaffDiscountService staffDiscountService;
	
	@PostMapping("/createStaffOffer")
	public JSONObject add(@RequestBody JSONObject offerDetails) {
		logger.info("Received staff offer details "+offerDetails);
		JSONObject response = new JSONObject();
		
		StaffDiscount staffDiscount = new StaffDiscount();
		
		try {
			staffDiscount.setDiscountAmount(Double.parseDouble(offerDetails.get("discountAmount").toString()));
			staffDiscountService.updateActive();
			staffDiscountService.save(staffDiscount);
			logger.info("Staff offer created ");
			
			response.put("resultCode", 0);
			response.put("resultMessage", "Staff offer has been successfully applied");
		} catch (Exception e) {
			response.put("resultCode", 10010);
			response.put("resultMessage", "An error occured while creating staff offer. "+e.getMessage());
		}
		
		logger.info("Staff offer creation response "+response);		
		return response;
	}
}