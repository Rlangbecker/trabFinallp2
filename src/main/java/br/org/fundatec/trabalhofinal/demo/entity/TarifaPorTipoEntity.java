package br.org.fundatec.trabalhofinal.demo.entity;

import br.org.fundatec.trabalhofinal.demo.entity.enums.TipoVeiculo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="Tarifa_Por_Tipo")
public class TarifaPorTipoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTarifa;

    private double taxaAteMeiaHora;

    private double taxaAteUmaHora;

    private double taxaAdicional;

    private double taxaDiaria;

    private TipoVeiculo tipoVeiculo;
}
