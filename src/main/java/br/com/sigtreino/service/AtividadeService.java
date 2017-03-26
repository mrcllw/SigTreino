package br.com.sigtreino.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sigtreino.model.Atividade;
import br.com.sigtreino.repository.AtividadeRepository;

@Service
public class AtividadeService {

	@Autowired
	private AtividadeRepository atividadeRep;
	
	public Atividade salvar(Atividade atividade){
		return atividadeRep.save(atividade);
	}
	
	public List<Atividade> buscarTodos(){
		return atividadeRep.findAll();
	}
	
	public void excluir (Atividade atividade){
		atividadeRep.delete(atividade);
	}
	
	public Atividade buscarPorId(Long id){
		return atividadeRep.findOne(id);
	}
}
