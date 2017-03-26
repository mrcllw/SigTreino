package br.com.sigtreino.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sigtreino.model.Academia;
import br.com.sigtreino.model.Exercicio;

@Repository
public interface ExercicioRepository extends JpaRepository<Exercicio, Long> {
	
	public List<Exercicio> findByAcademia(Academia academia);

}
