package br.com.sigtreino.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sigtreino.model.Treino;

@Repository
public interface TreinoRepository extends JpaRepository<Treino, Long>{

}
