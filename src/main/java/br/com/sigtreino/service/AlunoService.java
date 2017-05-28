package br.com.sigtreino.service;

import java.util.List;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sigtreino.model.Aluno;
import br.com.sigtreino.repository.AlunoRepository;

@Service
public class AlunoService {
	
	@Autowired
	private AlunoRepository alunoRep;
	
	@Autowired
	private AcademiaService academiaSer;
	
	public Aluno salvar(Aluno aluno){
		try{
			aluno.setAcademia(academiaSer.academiaLogada());
		}catch(ServletException e){
			e.printStackTrace();
		}
		return alunoRep.save(aluno);
	}
	
	public List<Aluno> buscarTodos(){
		return alunoRep.findAll();
	}
	
	public void excluir(Aluno aluno){
		alunoRep.delete(aluno);
	}
	
	public Aluno buscarPorId(Long id){
		return alunoRep.findOne(id);
	}
	
	public List<Aluno> buscarPorAcademia() throws ServletException{
		return alunoRep.findByAcademia(academiaSer.academiaLogada());
	}

}
