package br.org.fundatec.trabalhofinal.demo.dto.plano;

import br.org.fundatec.trabalhofinal.demo.dto.cliente.ClienteDTO;
import lombok.Data;

@Data
public class PlanoDTO {

    private Integer idPlano;
    private double valor;
    private ClienteDTO cliente;

}
