package br.org.fundatec.trabalhofinal.demo.dto.tarifaPorTipo;

import br.org.fundatec.trabalhofinal.demo.entity.enums.TipoVeiculo;
import lombok.Data;

@Data
public class TarifaPorTipoDTO extends TarifaPorTipoCreateDTO {

    private Integer idTarifa;
    private TipoVeiculo tipoVeiculo;

}
