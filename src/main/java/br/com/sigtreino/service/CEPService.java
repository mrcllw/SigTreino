package br.com.sigtreino.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CEPService {
	
	private static final String API_URL = "http://viacep.com.br/ws/{cep}/json/";
	
	public String consultarCEP(String cep){
		RestTemplate rt = new RestTemplate();
		return rt.getForObject(API_URL.replace("{cep}", cep), String.class);
	}
}
