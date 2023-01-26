package br.org.fundatec.trabalhofinal.demo.repository;

import br.org.fundatec.trabalhofinal.demo.entity.EstacionamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstacionamentoRepository extends JpaRepository<EstacionamentoEntity, Integer> {
}
