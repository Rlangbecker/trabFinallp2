package br.org.fundatec.trabalhofinal.demo.dto.endereco;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class EnderecoCreateDTO {

    @NotBlank(message = "Rua não pode ser vazio")
    @NotNull(message = "Rua não pode ser nula")
    private String rua;

    private Integer numero;

    private String bairro;

    private String cep;

    private String cidade;

    private String estado;
}
