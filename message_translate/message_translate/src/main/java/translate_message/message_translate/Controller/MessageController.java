package translate_message.message_translate.Controller;



import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import translate_message.message_translate.ServiceImpl.MessageServiceImpl;

@RestController
public class MessageController {

	@Autowired
	private MessageServiceImpl messageServiceImpl;
	
	@PostMapping("/translate")	
	public String translateText(@RequestParam String message, @RequestParam String source , @RequestParam String target) throws IOException, InterruptedException{
		return messageServiceImpl.translate(message, source,target);
	
	}
	
	@GetMapping("language")
	public String lang() throws IOException, InterruptedException {
		return messageServiceImpl.language();
	}
}
