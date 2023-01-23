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

    public PlanoEntity findByAssinante(ClienteEntity cliente) {
        PlanoEntity planoRetorno = planoRepository.findPlanoEntityByCliente(cliente);
        if (planoRetorno != null) {
            return planoRetorno;
        } else {
            return null;
        }
    }

    public PlanoEntity findByAssinanteId(Integer idCliente) {
        PlanoEntity planoRetorno = planoRepository.findPlanoEntityByCliente_IdCliente(idCliente);
        if (planoRetorno != null) {
            return planoRetorno;
        } else {
            return null;
        }
    }

    public List<PlanoDTO> listAll() {
        return planoRepository.findAll().stream()
                .map(planoEntity -> {
                    PlanoDTO planoDTO = objectMapper.convertValue(planoEntity, PlanoDTO.class);
                    planoDTO.setCliente(clienteService.findClienteById(planoEntity.getCliente().getIdCliente()));
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

    public boolean verificarAssinatura(Integer idAssinante) {
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
