package br.com.sigtreino.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sigtreino.model.Academia;
import br.com.sigtreino.repository.AcademiaRepository;

@Service
public class AcademiaService {
	
	@Autowired
	private AcademiaRepository academiaRep;
	
	public Academia salvar(Academia academia){
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

}
