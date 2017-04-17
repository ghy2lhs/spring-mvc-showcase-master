package org.springframework.samples.mvc.convert;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;
import java.util.Date;

@Controller
@RequestMapping("/convert")
public class ConvertController {
	/**
	 * Request URL:http://localhost:8080/convert/primitive?value=3
	 * Query String Parameters中含有：value=3
	 * @param value
	 * @return
	 */
	@RequestMapping("primitive")
	public @ResponseBody String primitive(@RequestParam Integer value) {
		return "Converted primitive " + value;
	}

	/** requires Joda-Time on the classpath
	 *  Request URL:http://localhost:8080/convert/date/2010-07-04
	 * @param value
	 * @return
	 */
	@RequestMapping("date/{value}")
	public @ResponseBody String date(@PathVariable @DateTimeFormat(iso=ISO.DATE) Date value) {
		return "Converted date " + value;
	}

	/**
	 * Request URL:http://localhost:8080/convert/collection?values=1&values=2&values=3&values=4&values=5
	 * Query String Parameters:  values=1&values=2&values=3&values=4&values=5
	 * Response: Converted collection [1, 2, 3, 4, 5]
	 * 或者：
	 * Request URL:http://localhost:8080/convert/collection?values=1,2,3,4,5
	 * values=1,2,3,4,5
	 * @param values
	 * @return
	 */
	@RequestMapping("collection")
	public @ResponseBody String collection(@RequestParam Collection<Integer> values) {
		return "Converted collection " + values;
	}

	/**
	 * Request URL:http://localhost:8080/convert/formattedCollection?values=2010-07-04,2011-07-04
	 * Query String Parameters:values=2010-07-04,2011-07-04
	 * Response:Converted formatted collection [Sun Jul 04 00:00:00 CST 2010, Mon Jul 04 00:00:00 CST 2011]
	 * @param values
	 * @return
	 */
	@RequestMapping("formattedCollection")
	public @ResponseBody String formattedCollection(@RequestParam @DateTimeFormat(iso=ISO.DATE) Collection<Date> values) {
		return "Converted formatted collection " + values;
	}

	/**
	 *
	 * @param bean
	 * @return
	 */
	@RequestMapping("bean")
	public @ResponseBody String bean(JavaBean bean) {
		return "Converted " + bean;
	}

	/**
	 * Request URL:http://localhost:8080/convert/value?value=123456789
	 * Query String Parameters: value=123456789
	 * @param value
	 * @return
	 */
	@RequestMapping("value")
	public @ResponseBody String valueObject(@RequestParam SocialSecurityNumber value) {
		return "Converted value object " + value;
	}

	/**
	 * Request URL:http://localhost:8080/convert/custom?value=123-45-6789
	 * Query String Parameters:value=123-45-6789
	 * @param value
	 * @return
	 */
	@RequestMapping("custom")
	public @ResponseBody String customConverter(@RequestParam @MaskFormat("###-##-####") String value) {
		return "Converted '" + value + "' with a custom converter";
	}

}
