package br.com.sigtreino.service;

import java.util.List;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sigtreino.model.Treino;
import br.com.sigtreino.repository.TreinoRepository;

@Service
public class TreinoService {

	@Autowired
	private TreinoRepository treinoRep;
	
	@Autowired
	private AcademiaService academiaSer;
	
	public Treino salvar(Treino treino){
		try{
			treino.setAcademia(academiaSer.academiaLogada());
		}catch(ServletException e){
			e.printStackTrace();
		}
		return treinoRep.save(treino);
	}
	
	public List<Treino> buscarTodos(){
		return treinoRep.findAll();
	}
	
	public void excluir(Treino treino) throws Exception{
		try{
			treinoRep.delete(treino);
		}catch(Exception e){
			throw new Exception("Treino não pode ser excluido pois esta vinculado a um ou mais alunos.");
		}
	}
	
	public Treino buscarPorId(Long id){
		return treinoRep.findOne(id);
	}
	
	public List<Treino> buscarPorAcademia() throws ServletException{
		return treinoRep.findByAcademia(academiaSer.academiaLogada());
	}
}
