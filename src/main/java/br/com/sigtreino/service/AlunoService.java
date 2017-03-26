package br.com.sigtreino.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sigtreino.model.Academia;
import br.com.sigtreino.model.Aluno;
import br.com.sigtreino.repository.AlunoRepository;

@Service
public class AlunoService {
	
	@Autowired
	private AlunoRepository alunoRep;
	
	public Aluno salvar(Aluno aluno){
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
	
	public List<Aluno> buscarPorAcademia(Academia academia){
		return alunoRep.findByAcademia(academia);
	}

}
