package br.com.sigtreino.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sigtreino.model.Academia;
import br.com.sigtreino.model.Treino;
import br.com.sigtreino.repository.TreinoRepository;

@Service
public class TreinoService {

	@Autowired
	private TreinoRepository treinoRep;
	
	public Treino salvar(Treino treino){
		return treinoRep.save(treino);
	}
	
	public List<Treino> buscarTodos(){
		return treinoRep.findAll();
	}
	
	public void excluir(Treino treino){
		treinoRep.delete(treino);
	}
	
	public Treino buscarPorId(Long id){
		return treinoRep.findOne(id);
	}
	
	public List<Treino> buscarPorAcademia(Academia academia){
		return treinoRep.findByAcademia(academia);
	}
}
