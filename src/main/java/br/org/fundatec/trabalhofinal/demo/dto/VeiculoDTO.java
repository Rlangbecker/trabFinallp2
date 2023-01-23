package br.org.fundatec.trabalhofinal.demo.dto;

import br.org.fundatec.trabalhofinal.demo.dto.cliente.ClienteDTO;
import br.org.fundatec.trabalhofinal.demo.entity.enums.TipoVeiculo;
import lombok.Data;

@Data
public class VeiculoDTO {

    private Integer idVeiculo;

    private TipoVeiculo tipoVeiculo;

    private String placa;

    private ClienteDTO clienteDTO;

}
