package net.codejava.analyse;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	//A handler that takes in one parameter, the file
	public String handleFormUpload(@RequestParam("file") MultipartFile file) throws IOException {
		ReadWrite w = new ReadWrite();
		w.writer("Nu kör vi!");
		//If there is a file
		if (!file.isEmpty()) {
			byte[] bytes = file.getBytes();//Put the bytes into a byte array
			FileOutputStream fos = new FileOutputStream("C:\\Users\\Martin\\Desktop\\image.bmp");//A FileOutputStream with the path where the picture should be
			try {
				fos.write(bytes);//Writes the bytes to a file, in this case "creating" the picture as a .bmp
			} finally {
				fos.close();//Closes the FileOutPutStream
			}
			return "Hej hej test test";//Returns the answer
		} else {
			return "doesn't work";//Returns this in case of empty file
		}
	}
}
