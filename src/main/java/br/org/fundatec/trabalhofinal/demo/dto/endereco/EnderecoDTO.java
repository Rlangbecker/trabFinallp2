package br.org.fundatec.trabalhofinal.demo.dto.endereco;

import lombok.Data;

@Data
public class EnderecoDTO {

    private Integer id;
    private String rua;

    private Integer numero;

    private String bairro;

    private String cep;

    private String cidade;

    private String estado;
}
