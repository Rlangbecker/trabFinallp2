package br.org.fundatec.trabalhofinal.demo.dto.cliente;

import br.org.fundatec.trabalhofinal.demo.dto.VeiculoCreateDTO;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ClienteCreateDTO {

    @NotNull(message = "Nome não pode ser nulo")
    @NotBlank(message = "Nome não pode estar vazio")
    private String nome;

    @NotNull(message = "CPF não pode ser nulo")
    @NotBlank(message = "CPF não pode estar vazio")
    private String cpf;

    private Integer idEndereco;

}
