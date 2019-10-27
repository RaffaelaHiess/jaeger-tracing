package org.ela.tracing.jaeger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class KobajagiController {
	
	@Autowired
	RestTemplate restTemplate;

	@RequestMapping("/kobajagi")
	public String getKobajagi() {
		return "This is kobajagi!";
	}

	@RequestMapping("/chaining")
	public String chaining() {
		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/kobajagi", String.class);
		return "Chaining + " + response.getBody();
	}
}
