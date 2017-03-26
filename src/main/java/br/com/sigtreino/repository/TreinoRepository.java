package br.com.sigtreino.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sigtreino.model.Academia;
import br.com.sigtreino.model.Treino;

@Repository
public interface TreinoRepository extends JpaRepository<Treino, Long>{
	
	public List<Treino> findByAcademia(Academia academia);

}
