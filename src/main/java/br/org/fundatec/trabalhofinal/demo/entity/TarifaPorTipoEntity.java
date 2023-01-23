package br.org.fundatec.trabalhofinal.demo.entity;

import br.org.fundatec.trabalhofinal.demo.entity.enums.TipoVeiculo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TarifaPorTipoEntity {
    private double taxaAteMeiaHora;

    private double taxaAteUmaHora;

    private double taxaAdicional;

    private double taxaDiaria;

    private TipoVeiculo tipoVeiculo;
}
