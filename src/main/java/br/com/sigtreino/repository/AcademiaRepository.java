package br.com.sigtreino.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sigtreino.model.Academia;

@Repository
public interface AcademiaRepository extends JpaRepository<Academia, Long>{
	
	public Academia findByLogin(String login);

}
