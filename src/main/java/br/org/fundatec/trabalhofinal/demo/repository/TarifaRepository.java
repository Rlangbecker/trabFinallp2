package br.org.fundatec.trabalhofinal.demo.repository;

import br.org.fundatec.trabalhofinal.demo.entity.TarifaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarifaRepository extends JpaRepository<TarifaEntity,Integer> {
}
