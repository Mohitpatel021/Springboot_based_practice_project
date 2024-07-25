package translate_message.message_translate.Service;

import java.io.IOException;

public interface MessageService {
	public String translate(String message,String sourceLanguage,String targetlanguage) throws IOException, InterruptedException;
	public String language() throws IOException, InterruptedException;
}