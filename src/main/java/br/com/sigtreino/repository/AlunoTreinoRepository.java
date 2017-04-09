package br.com.sigtreino.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.sigtreino.model.Aluno;
import br.com.sigtreino.model.AlunoTreino;

@Repository
public interface AlunoTreinoRepository extends JpaRepository<AlunoTreino, Long>{
	
	public List<AlunoTreino> findByAluno(Aluno aluno);
	
	@Query("select at from AlunoTreino at where at.aluno.id = :id")
	public List<AlunoTreino> buscarAlunoTreinoPorAlunoId(@Param("id")Long id);

}
