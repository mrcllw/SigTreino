package br.com.sigtreino.service;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import br.com.sigtreino.model.Academia;
import br.com.sigtreino.repository.AcademiaRepository;
import io.jsonwebtoken.Jwts;

@Service
public class AcademiaService {
	
	@Autowired
	private AcademiaRepository academiaRep;
	
	public Academia salvar(Academia academia) throws Exception{
		Academia academiaBuscadaCpfCnpj = academiaRep.findByCpfCnpj(academia.getCpfCnpj());
		Academia academiaBuscadaLogin = academiaRep.findByLogin(academia.getLogin());
		if(academiaBuscadaCpfCnpj != null){
			throw new Exception("Academia já cadastrada.");
		}  else if (academiaBuscadaLogin != null){
			throw new Exception("Login já utilizado.");
		}
		return academiaRep.save(academia);
	}
	
	public List<Academia> buscarTodos(){
		return academiaRep.findAll();
	}
	
	public void excluir(Academia academia){
		academiaRep.delete(academia);
	}
	
	public Academia buscarPorId(Long id){
		return academiaRep.findOne(id);
	}
	
	public Academia buscarPorLogin(String login){
		return academiaRep.findByLogin(login);
	}
	
	@Autowired
	private HttpServletRequest req;
	
	public Academia academiaLogada() throws ServletException {
		String header = req.getHeader("Authorization");
		if(header==null || !header.startsWith("Bearer ")){
			throw new ServletException("Token inexistente ou invalido.");
		};
		String token = header.substring(7);
		String user = Jwts.parser().setSigningKey("chave").parseClaimsJws(token).getBody().getSubject();
		Gson gson = new Gson();
		Academia academia = gson.fromJson(user, Academia.class);
		return academia;
	}

}
