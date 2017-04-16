package br.com.sigtreino.service;

import java.util.List;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sigtreino.model.Academia;
import br.com.sigtreino.model.Exercicio;
import br.com.sigtreino.repository.ExercicioRepository;

@Service
public class ExercicioService {

	@Autowired
	private ExercicioRepository exercicioRep;
	
	@Autowired
	private AcademiaService academiaSer;
	
	public Exercicio salvar(Exercicio exercicio){
		try{
			exercicio.setAcademia(academiaSer.academiaLogada());
		}catch(ServletException e){
			e.printStackTrace();
		}
		return exercicioRep.save(exercicio);
	}
	
	public List<Exercicio> buscarTodos(){
		return exercicioRep.findAll();
	}
	
	public void excluir(Exercicio exercicio) throws Exception{
		try{
			exercicioRep.delete(exercicio);
		}catch(Exception e){
			throw new Exception("Exercicio não pode ser excluido pois esta vinculado a um ou mais treinos.");
		}
	}
	
	public Exercicio buscarPorId(Long id){
		return exercicioRep.findOne(id);
	}
	
	public List<Exercicio> buscarPorAcademia(Academia academia){
		return exercicioRep.findByAcademia(academia);
	}
	
	public List<Exercicio> buscarPorAcademia() throws ServletException{
		return exercicioRep.findByAcademia(academiaSer.academiaLogada());
	}
}
