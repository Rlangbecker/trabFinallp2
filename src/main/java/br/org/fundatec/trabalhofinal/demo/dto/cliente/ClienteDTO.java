package br.org.fundatec.trabalhofinal.demo.dto.cliente;

import br.org.fundatec.trabalhofinal.demo.dto.endereco.EnderecoDTO;
import lombok.Data;

@Data
public class ClienteDTO {

    private Integer id;

    private String nome;

    private String cpf;
    private boolean assinante;

    private EnderecoDTO endereco;

}
