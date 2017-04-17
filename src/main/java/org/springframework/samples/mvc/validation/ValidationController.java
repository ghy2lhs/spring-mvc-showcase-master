package org.springframework.samples.mvc.validation;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
public class ValidationController {

	// enforcement of constraints on the JavaBean arg require a JSR-303 provider on the classpath

	/**
	 * Request URL:http://localhost:8080/validate?number=3&date=2029-07-04
	 * Query String Parameters: number=3&date=2029-07-04
	 * or
	 * Request URL:http://localhost:8080/validate?number=3&date=2010-07-01
	 * 因为有Furture属性，所以会报错
	 * Query String Parameters: number=3&date=2010-07-01
	 * @param bean
	 * @param result
	 * @return
	 */
	@RequestMapping("/validate")
	public @ResponseBody String validate(@Valid JavaBean bean, BindingResult result) {
		if (result.hasErrors()) {
			return "Object has validation errors";
		} else {
			return "No errors";
		}
	}

}
