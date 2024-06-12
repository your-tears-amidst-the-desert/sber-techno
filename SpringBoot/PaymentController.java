package com.example.demo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
@SpringBootApplication
public class PaymentController {

	public static void main(String[] args) {
		SpringApplication.run(PaymentController.class, args);
	}

	private final String sharedKey = "SHARED_KEY";

	private static final String SUCCESS_STATUS = "success";
	private static final String ERROR_STATUS = "error";
	private static final int CODE_SUCCESS = 100;
	private static final int AUTH_FAILURE = 102;

	@GetMapping
	public String showStatus(BaseResponse response) {
		return response.getStatus();
	}

	@PostMapping("/pay")
	public BaseResponse pay(@RequestParam(value = "key") String key,  PaymentRequest request) {

		final BaseResponse response;

		if (sharedKey.equalsIgnoreCase(key)) {
			int userId = request.getUserId();
			String itemId = request.getItemId();
			double discount = request.getDiscount();
			response = new BaseResponse(SUCCESS_STATUS, CODE_SUCCESS);
		} else {
			response = new BaseResponse(ERROR_STATUS, AUTH_FAILURE);
		}
		showStatus(response);
		return response;
	}
}