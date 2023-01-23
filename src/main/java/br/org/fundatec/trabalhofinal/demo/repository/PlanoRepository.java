package br.org.fundatec.trabalhofinal.demo.repository;

import br.org.fundatec.trabalhofinal.demo.entity.ClienteEntity;
import br.org.fundatec.trabalhofinal.demo.entity.PlanoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanoRepository extends JpaRepository<PlanoEntity,Integer> {

    PlanoEntity findPlanoEntityByCliente(ClienteEntity cliente);

    PlanoEntity findPlanoEntityByCliente_IdCliente(Integer idCliente);
}
