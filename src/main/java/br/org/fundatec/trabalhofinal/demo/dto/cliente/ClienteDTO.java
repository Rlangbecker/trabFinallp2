package br.org.fundatec.trabalhofinal.demo.dto.cliente;

import br.org.fundatec.trabalhofinal.demo.dto.VeiculoDTO;
import br.org.fundatec.trabalhofinal.demo.dto.endereco.EnderecoDTO;
import br.org.fundatec.trabalhofinal.demo.entity.EnderecoEntity;
import br.org.fundatec.trabalhofinal.demo.entity.PlanoEntity;
import lombok.Data;

import java.util.Set;

@Data
public class ClienteDTO {

    private Integer id;

    private String nome;

    private String cpf;
    private boolean assinante;

    private EnderecoDTO endereco;

}
