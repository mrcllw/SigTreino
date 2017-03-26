package br.com.sigtreino.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sigtreino.model.Academia;
import br.com.sigtreino.model.Exercicio;
import br.com.sigtreino.repository.ExercicioRepository;

@Service
public class ExercicioService {

	@Autowired
	private ExercicioRepository exercicioRep;
	
	public Exercicio salvar(Exercicio exercicio){
		return exercicioRep.save(exercicio);
	}
	
	public List<Exercicio> buscarTodos(){
		return exercicioRep.findAll();
	}
	
	public void excluir(Exercicio exercicio){
		exercicioRep.delete(exercicio);
	}
	
	public Exercicio buscarPorId(Long id){
		return exercicioRep.findOne(id);
	}
	
	public List<Exercicio> buscarPorAcademia(Academia academia){
		return exercicioRep.findByAcademia(academia);
	}
}
