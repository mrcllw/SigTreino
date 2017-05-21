package br.com.sigtreino.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import br.com.sigtreino.model.Academia;
import br.com.sigtreino.repository.AcademiaRepository;

@Service
public class CPFCNPJService {
	
	private static final String API_URL_CNPJ = "http://receitaws.com.br/v1/cnpj/{cnpj}";
	
	@Autowired
	private AcademiaRepository academiaRep;
	
	public String consultaCNPJ(String cnpj) throws Exception{
		Academia academia = academiaRep.findByCpfCnpj(cnpj);
		if(academia != null){
			throw new Exception("Academia já cadastrada.");
		}
		
		String cnpjBuscado;
		
		try {
			cnpjBuscado = new RestTemplate().getForObject(API_URL_CNPJ.replace("{cnpj}", cnpj), String.class);
		} catch (HttpServerErrorException e){
			throw new Exception("CNPJ não encontrado.");
		}
		return cnpjBuscado;
	}
	
	public Academia consultaCPF(String cpf) throws Exception{
		Academia academia = academiaRep.findByCpfCnpj(cpf);
		return academia;
	}
}
