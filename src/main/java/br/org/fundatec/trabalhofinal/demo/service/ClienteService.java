package br.org.fundatec.trabalhofinal.demo.service;


import br.org.fundatec.trabalhofinal.demo.dto.VeiculoDTO;
import br.org.fundatec.trabalhofinal.demo.dto.cliente.ClienteCreateDTO;
import br.org.fundatec.trabalhofinal.demo.dto.cliente.ClienteDTO;
import br.org.fundatec.trabalhofinal.demo.dto.endereco.EnderecoDTO;
import br.org.fundatec.trabalhofinal.demo.entity.ClienteEntity;
import br.org.fundatec.trabalhofinal.demo.entity.EnderecoEntity;
import br.org.fundatec.trabalhofinal.demo.entity.PlanoEntity;
import br.org.fundatec.trabalhofinal.demo.entity.VeiculoEntity;
import br.org.fundatec.trabalhofinal.demo.repository.ClienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    private final EnderecoService enderecoService;
    private final ObjectMapper objectMapper;

    public ClienteDTO create(ClienteCreateDTO clienteCreateDTO) {

        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setAssinante(false);
        clienteEntity.setNome(clienteCreateDTO.getNome());
        clienteEntity.setCpf(clienteCreateDTO.getCpf());
        EnderecoDTO enderecoDTO = enderecoService.findEnderecoById(clienteCreateDTO.getIdEndereco());
        EnderecoEntity endereco = objectMapper.convertValue(enderecoDTO, EnderecoEntity.class);
        endereco.setIdEndereco(clienteCreateDTO.getIdEndereco());
        clienteEntity.setEndereco(endereco);

        ClienteEntity clienteRetorno = clienteRepository.save(clienteEntity);

        ClienteDTO clienteDTO = objectMapper.convertValue(clienteRetorno, ClienteDTO.class);
        clienteDTO.setId(clienteRetorno.getIdCliente());
        clienteDTO.setEndereco(enderecoDTO);

        return clienteDTO;
    }

    public List<ClienteDTO> listAll() {
        return clienteRepository.findAll().stream()
                .map(clienteEntity -> {
                    ClienteDTO clienteDTO = objectMapper.convertValue(clienteEntity, ClienteDTO.class);
                    clienteDTO.setId(clienteEntity.getIdCliente());
                    if (clienteEntity.getEndereco() != null) {
                        clienteDTO.setEndereco(enderecoService.findEnderecoById(clienteEntity.getEndereco().getIdEndereco()));
                    }
                    return clienteDTO;
                })
                .toList();
    }

    public ClienteEntity findById(Integer idCliente) {
//        Optional<ClienteEntity> clienteOptional = clienteRepository.findById(idCliente);

        return clienteRepository.findById(idCliente).orElse(null);


    }

    public ClienteDTO findClienteById(Integer idCliente) {

        ClienteEntity cliente = findById(idCliente);
        ClienteDTO clienteDTO = objectMapper.convertValue(cliente, ClienteDTO.class);
        clienteDTO.setId(idCliente);
        EnderecoDTO endereco = objectMapper.convertValue(cliente.getEndereco(), EnderecoDTO.class);
        clienteDTO.setEndereco(endereco);

        return clienteDTO;
    }

    public ClienteDTO update(Integer idCliente, ClienteCreateDTO clienteCreateDTO) {
        ClienteEntity cliente = findById(idCliente);

        cliente.setCpf(clienteCreateDTO.getCpf());
        cliente.setNome(clienteCreateDTO.getNome());
        EnderecoDTO enderecoDTO = enderecoService.findEnderecoById(clienteCreateDTO.getIdEndereco());
        EnderecoEntity endereco = objectMapper.convertValue(enderecoDTO, EnderecoEntity.class);
        endereco.setIdEndereco(clienteCreateDTO.getIdEndereco());
        cliente.setEndereco(endereco);
//        PlanoEntity plano = objectMapper.convertValue(planoService.findByAssinanteId(cliente.getIdCliente()),PlanoEntity.class);
//        cliente.setPlanoEntity(plano);

        ClienteEntity clienteEntity = clienteRepository.save(cliente);
        ClienteDTO clienteDTO = objectMapper.convertValue(clienteEntity, ClienteDTO.class);
        clienteDTO.setId(clienteEntity.getIdCliente());
        enderecoDTO.setId(clienteCreateDTO.getIdEndereco());
        clienteDTO.setEndereco(enderecoDTO);

        return clienteDTO;
    }

    public void delete(Integer idCliente) {
        findById(idCliente);
        clienteRepository.deleteById(idCliente);
    }

    public void tornarAssinante(Integer idCliente) {
        ClienteEntity cliente = findById(idCliente);
        cliente.setAssinante(true);
        clienteRepository.save(cliente);
    }

//    public void cancelarAssinante(Integer idCliente){
//        ClienteEntity cliente = findById(idCliente);
//        cliente.setAssinante(false);
//        clienteRepository.save(cliente);
//    }


}
