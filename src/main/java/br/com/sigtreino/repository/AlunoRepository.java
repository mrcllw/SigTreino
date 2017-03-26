package br.com.sigtreino.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sigtreino.model.Academia;
import br.com.sigtreino.model.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long>{
	
	public List<Aluno> findByAcademia(Academia academia);

}
