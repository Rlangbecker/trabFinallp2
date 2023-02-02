package br.org.fundatec.trabalhofinal.demo.repository;

import br.org.fundatec.trabalhofinal.demo.entity.TarifaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarifaRepository extends JpaRepository<TarifaEntity,Integer> {

    TarifaEntity findTarifaEntityByVeiculoEntity_Placa(String placa);

    List<TarifaEntity> findTarifaEntitiesByVeiculoEntity_Placa(String placa);

}
