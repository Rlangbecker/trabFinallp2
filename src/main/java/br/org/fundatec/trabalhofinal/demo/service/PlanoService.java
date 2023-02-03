package br.org.fundatec.trabalhofinal.demo.service;

import br.org.fundatec.trabalhofinal.demo.dto.cliente.ClienteDTO;
import br.org.fundatec.trabalhofinal.demo.dto.plano.PlanoCreateDTO;
import br.org.fundatec.trabalhofinal.demo.dto.plano.PlanoDTO;
import br.org.fundatec.trabalhofinal.demo.entity.ClienteEntity;
import br.org.fundatec.trabalhofinal.demo.entity.PlanoEntity;
import br.org.fundatec.trabalhofinal.demo.exception.RegraDeNegocioException;
import br.org.fundatec.trabalhofinal.demo.repository.PlanoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PlanoService {


    private final PlanoRepository planoRepository;

    private final ClienteService clienteService;
    private final ObjectMapper objectMapper;


    public PlanoDTO create(Integer idCliente, PlanoCreateDTO planoCreateDTO) throws RegraDeNegocioException {

        PlanoEntity planoRetorno = findByAssinanteId(idCliente);
        if (planoRetorno != null) {
            throw new RegraDeNegocioException("Já consta plano para este cliente");
        }
        clienteService.tornarAssinante(idCliente);
        ClienteDTO clienteDTO = clienteService.findClienteById(idCliente);
        ClienteEntity cliente = objectMapper.convertValue(clienteDTO, ClienteEntity.class);
        cliente.setIdCliente(idCliente);
        PlanoEntity planoEntity = objectMapper.convertValue(planoCreateDTO, PlanoEntity.class);
        planoEntity.setCliente(cliente);
        planoEntity.setValor(planoCreateDTO.getValor());

        PlanoEntity planoRetornoRepository = planoRepository.save(planoEntity);

        PlanoDTO planoDTO = objectMapper.convertValue(planoRetornoRepository, PlanoDTO.class);
        planoDTO.setCliente(clienteDTO);

        return planoDTO;
    }

    public PlanoEntity findByAssinante(ClienteEntity cliente) throws RegraDeNegocioException {
        PlanoEntity planoRetorno = planoRepository.findPlanoEntityByCliente(cliente);
        if (planoRetorno != null) {
            return planoRetorno;
        } else {
            throw new RegraDeNegocioException("Cliente sem plano");
        }
    }

    public PlanoEntity findByAssinanteId(Integer idCliente) throws RegraDeNegocioException {
        PlanoEntity planoRetorno = planoRepository.findPlanoEntityByCliente_IdCliente(idCliente);
        if (planoRetorno != null) {
            return planoRetorno;
        } else {
            throw new RegraDeNegocioException("Cliente sem plano");
        }
    }

    public List<PlanoDTO> listAll() {
        return planoRepository.findAll().stream()
                .map(planoEntity -> {
                    PlanoDTO planoDTO = objectMapper.convertValue(planoEntity, PlanoDTO.class);
                    try {
                        planoDTO.setCliente(clienteService.findClienteById(planoEntity.getCliente().getIdCliente()));
                    } catch (RegraDeNegocioException e) {
                        throw new RuntimeException(e);
                    }
                    return planoDTO;
                })
                .toList();
    }

    public PlanoDTO fazerRecarga(Integer idAssinante, PlanoCreateDTO planoCreateDTO) throws RegraDeNegocioException {

        if (verificarAssinatura(idAssinante)) {

            PlanoEntity planoEntity = findByAssinanteId(idAssinante);
            planoEntity.setValor(planoEntity.getValor() + planoCreateDTO.getValor());
            PlanoEntity planoRetorno = planoRepository.save(planoEntity);

            PlanoDTO planoDTO = objectMapper.convertValue(planoRetorno, PlanoDTO.class);
            planoDTO.setCliente(clienteService.findClienteById(idAssinante));

            return planoDTO;
        } else {
            throw new RegraDeNegocioException("Cliente sem assinatura, para fazer recarga, crie um plano!");
        }
    }

    public void descontarTarifa(Integer idAssinante,double valor) throws RegraDeNegocioException {
       ClienteDTO cliente= clienteService.findClienteById(idAssinante);
       ClienteEntity clienteEntity = new ClienteEntity();
       clienteEntity.setIdCliente(cliente.getId());
       clienteEntity.setNome(cliente.getNome());
       clienteEntity.setCpf(clienteEntity.getCpf());
       PlanoEntity planoEntity = findByAssinante(clienteEntity);
       planoEntity.setValor(planoEntity.getValor()-valor);
       planoRepository.save(planoEntity);

    }

    public boolean verificarAssinatura(Integer idAssinante) throws RegraDeNegocioException {
        boolean assinante = true;
        boolean naoAssinante = false;
        ClienteEntity cliente = objectMapper.convertValue(clienteService.findClienteById(idAssinante), ClienteEntity.class);
        if (cliente.isAssinante()) {
            return assinante;
        } else {
            return naoAssinante;
        }

    }

}
