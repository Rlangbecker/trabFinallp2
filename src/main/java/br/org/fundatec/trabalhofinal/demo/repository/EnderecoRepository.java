package br.org.fundatec.trabalhofinal.demo.repository;

import br.org.fundatec.trabalhofinal.demo.entity.EnderecoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoEntity,Integer> {
}
