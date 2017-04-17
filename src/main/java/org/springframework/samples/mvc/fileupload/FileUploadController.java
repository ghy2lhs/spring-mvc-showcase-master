package org.springframework.samples.mvc.fileupload;

import org.springframework.mvc.extensions.ajax.AjaxUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/fileupload")
public class FileUploadController {

	@ModelAttribute
	public void ajaxAttribute(WebRequest request, Model model) {
		model.addAttribute("ajaxRequest", AjaxUtils.isAjaxRequest(request));
	}

	@RequestMapping(method=RequestMethod.GET)
	public void fileUploadForm() {
	}

	/**
	 * Request URL:http://localhost:8080/fileupload?_csrf=609659e0-e2c9-4fdc-b004-b20c8b813bc6
	 * Request Method:POST
	 * Request Payload:
	 * ------WebKitFormBoundaryJYAmd1cQci4uANW7
	 Content-Disposition: form-data; name="file"; filename="P020170306359087344128.xls"
	 Content-Type: application/octet-streams
	 * @param file
	 * @param model
	 * @throws IOException
	 */
	@RequestMapping(method=RequestMethod.POST)
	public void processUpload(@RequestParam MultipartFile file, Model model) throws IOException {
		model.addAttribute("message", "File '" + file.getOriginalFilename() + "' uploaded successfully");
	}
	
}
