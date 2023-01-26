package br.org.fundatec.trabalhofinal.demo.dto.tarifaPorTipo;

import lombok.Data;

@Data
public class TarifaPorTipoCreateDTO {
    private double taxaAteMeiaHora;

    private double taxaAteUmaHora;

    private double taxaAdicional;

    private double taxaDiaria;
}
