package br.com.sigtreino.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sigtreino.model.Atividade;

@Repository
public interface AtividadeRepository extends JpaRepository<Atividade, Long>{

}
