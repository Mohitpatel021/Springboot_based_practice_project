package translate_message.message_translate.ServiceImpl;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Service;


import translate_message.message_translate.Service.MessageService;

@Service
public class MessageServiceImpl implements MessageService{

	
public String translate(String message,String sourceLanguage,String targetLanguage) throws IOException, InterruptedException {
		
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://google-translate1.p.rapidapi.com/language/translate/v2"))
				.header("content-type", "application/x-www-form-urlencoded")
				.header("Accept-Encoding", "application/gzip")
				.header("X-RapidAPI-Key", "fcf07f0949msh709b7b4a5842ca6p14babajsn40c30934ca3a")
				.header("X-RapidAPI-Host", "google-translate1.p.rapidapi.com")
				.method("POST", HttpRequest.BodyPublishers.ofString("q="+message+"&target="+targetLanguage+"&source="+sourceLanguage))
				.build();
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		return response.body();
	}
public String language() throws IOException, InterruptedException {
	HttpRequest request = HttpRequest.newBuilder()
			.uri(URI.create("https://google-translate1.p.rapidapi.com/language/translate/v2/languages"))
			.header("Accept-Encoding", "application/gzip")
			.header("X-RapidAPI-Key", "fcf07f0949msh709b7b4a5842ca6p14babajsn40c30934ca3a")
			.header("X-RapidAPI-Host", "google-translate1.p.rapidapi.com")
			.method("GET", HttpRequest.BodyPublishers.noBody())
			.build();
	HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
	return response.body();
}

}