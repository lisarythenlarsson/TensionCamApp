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
	 * Simply selects the home view to render by returning its name. Used to check that the server is up and running.
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
	/**
	 * Controller method which respondes to a POST requst. It receives a MultipartFile, which in this case is a picture.
	 * The controller writes the MultipartFile as an image to the disk. Afterwards it is executed with the analyse program.
	 * After the analysis is performed the result is sent back to the client.
	 * @throws InterruptedException 
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public String handleFormUpload(@RequestParam("file") MultipartFile file) throws IOException, InterruptedException {
		Read r = new Read();
		if (!file.isEmpty()) {
			byte[] bytes = file.getBytes();//Put the bytes into a byte array
			FileOutputStream fos = new FileOutputStream("C:\\Users\\Martin\\Desktop\\IMG1.bmp");//A FileOutputStream with the path where the picture should be
			try {
				fos.write(bytes);//Writes the bytes to a file, in this case "creating" the picture as a .bmp
			} finally {
				fos.close();
				new CommandExecution("C:\\Users\\Martin\\SoftwareEng\\Analyse.bat");
			}
			Thread.sleep(3000);
			String result = r.reader("C:\\Users\\Martin\\Desktop\\Result.txt");
			return result;
		} else {
			return "doesn't work";//Returns this in case of empty file
		}
	}
}
