package br.org.fundatec.trabalhofinal.demo.dto.veiculo;

import br.org.fundatec.trabalhofinal.demo.entity.enums.TipoVeiculo;
import lombok.Data;

@Data
public class VeiculoCreateDTO {
    private String placa;

    private Integer idCliente;

}
