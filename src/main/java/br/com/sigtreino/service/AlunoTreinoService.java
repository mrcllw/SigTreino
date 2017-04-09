package br.com.sigtreino.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sigtreino.model.AlunoTreino;
import br.com.sigtreino.repository.AlunoTreinoRepository;

@Service
public class AlunoTreinoService {
	
	@Autowired
	private AlunoTreinoRepository alunoTreinoRep;
	
	public AlunoTreino salvar(AlunoTreino alunoTreino){
		return alunoTreinoRep.save(alunoTreino);
	}
	
	public List<AlunoTreino> buscarTodos(){
		return alunoTreinoRep.findAll();
	}
	
	public void excluir(AlunoTreino alunoTreino){
		alunoTreinoRep.delete(alunoTreino);
	}
	
	public AlunoTreino buscarPorId(Long id){
		return alunoTreinoRep.findOne(id);
	}
	
	public List<AlunoTreino> buscarPorAluno(Long id){
		return alunoTreinoRep.buscarAlunoTreinoPorAlunoId(id);
	}

}
