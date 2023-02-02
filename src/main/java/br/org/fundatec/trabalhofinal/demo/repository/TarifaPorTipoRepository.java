package br.org.fundatec.trabalhofinal.demo.repository;

import br.org.fundatec.trabalhofinal.demo.entity.TarifaPorTipoEntity;
import br.org.fundatec.trabalhofinal.demo.entity.enums.TipoVeiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TarifaPorTipoRepository extends JpaRepository<TarifaPorTipoEntity, Integer> {

    Optional<TarifaPorTipoEntity> findTarifaPorTipoEntityByTipoVeiculo(TipoVeiculo tipoVeiculo);

}
