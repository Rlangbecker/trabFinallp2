package br.org.fundatec.trabalhofinal.demo.repository;

import br.org.fundatec.trabalhofinal.demo.entity.VeiculoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeiculoRepository extends JpaRepository<VeiculoEntity, Integer> {
}
