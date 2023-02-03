package br.org.fundatec.trabalhofinal.demo.service;

import br.org.fundatec.trabalhofinal.demo.dto.cliente.ClienteDTO;
import br.org.fundatec.trabalhofinal.demo.dto.veiculo.VeiculoCreateDTO;
import br.org.fundatec.trabalhofinal.demo.dto.veiculo.VeiculoDTO;
import br.org.fundatec.trabalhofinal.demo.entity.ClienteEntity;
import br.org.fundatec.trabalhofinal.demo.entity.VeiculoEntity;
import br.org.fundatec.trabalhofinal.demo.entity.enums.TipoVeiculo;
import br.org.fundatec.trabalhofinal.demo.exception.RegraDeNegocioException;
import br.org.fundatec.trabalhofinal.demo.repository.VeiculoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;

    private final ClienteService clienteService;
    private final ObjectMapper objectMapper;

    public VeiculoDTO create(VeiculoCreateDTO veiculoCreateDTO, TipoVeiculo tipoVeiculo) throws RegraDeNegocioException {

        VeiculoEntity veiculoEntity = objectMapper.convertValue(veiculoCreateDTO, VeiculoEntity.class);
        ClienteDTO clienteDTO = clienteService.findClienteById(veiculoCreateDTO.getIdCliente());

        ClienteEntity cliente = new ClienteEntity();
        cliente.setIdCliente(clienteDTO.getId());
        cliente.setNome(clienteDTO.getNome());
        veiculoEntity.setDono(cliente);
        veiculoEntity.setTipoVeiculo(tipoVeiculo);
        VeiculoEntity veiculoRetornoRepository = veiculoRepository.save(veiculoEntity);

        VeiculoDTO veiculoDTO = objectMapper.convertValue(veiculoRetornoRepository, VeiculoDTO.class);
        veiculoDTO.setIdVeiculo(veiculoEntity.getIdVeiculo());
        veiculoDTO.setTipoVeiculo(tipoVeiculo);
        return veiculoDTO;
    }

    public List<VeiculoDTO> listAll() {
        return veiculoRepository.findAll().stream()
                .map(veiculoEntity -> {
                    VeiculoDTO veiculoDTO = new VeiculoDTO();
                    veiculoDTO.setPlaca(veiculoEntity.getPlaca());
                    veiculoDTO.setTipoVeiculo(veiculoEntity.getTipoVeiculo());
                    veiculoDTO.setIdVeiculo(veiculoEntity.getIdVeiculo());
                    ClienteDTO clienteDTO = null;
                    try {
                        clienteDTO = clienteService.findClienteById(veiculoEntity.getDono().getIdCliente());
                    } catch (RegraDeNegocioException e) {
                        throw new RuntimeException(e);
                    }
                    veiculoDTO.setClienteDTO(clienteDTO);
                    return veiculoDTO;
                })
                .toList();
    }

    private VeiculoEntity findById(Integer id) throws RegraDeNegocioException {
        Optional<VeiculoEntity> veiculo = veiculoRepository.findById(id);
        if (veiculo.isEmpty()) {
            throw new RegraDeNegocioException("Veículo não encontrado pelo ID - " + id);
        }

        VeiculoEntity veiculoEntity = new VeiculoEntity();
        veiculoEntity.setIdVeiculo(veiculo.get().getIdVeiculo());
        veiculoEntity.setTipoVeiculo(veiculo.get().getTipoVeiculo());
        veiculoEntity.setPlaca(veiculo.get().getPlaca());
        veiculoEntity.setDono(veiculo.get().getDono());

        return veiculoEntity;
    }

    public VeiculoDTO findVeiculoDTOById(Integer id) throws RegraDeNegocioException {
        VeiculoDTO veiculoDTO = objectMapper.convertValue(findById(id), VeiculoDTO.class);
        return veiculoDTO;
    }

    public VeiculoDTO findByPlaca(String placa) throws RegraDeNegocioException {
        Optional<VeiculoEntity> veiculoOptional = veiculoRepository.findVeiculoEntityByPlaca(placa);
        if (veiculoOptional.isEmpty()) {
            throw new RegraDeNegocioException("Veículo não encontrado");
        }
        VeiculoDTO veiculoDTO = new VeiculoDTO();
        veiculoDTO.setTipoVeiculo(veiculoOptional.get().getTipoVeiculo());
        veiculoDTO.setPlaca(veiculoOptional.get().getPlaca());
        veiculoDTO.setIdVeiculo(veiculoOptional.get().getIdVeiculo());
        ClienteDTO cliente = clienteService.findClienteById(veiculoOptional.get().getDono().getIdCliente());
        veiculoDTO.setClienteDTO(cliente);

        return veiculoDTO;
    }

    public VeiculoEntity findVeiculoEntityByPlaca(String placa) throws RegraDeNegocioException {
        Optional<VeiculoEntity> veiculoOptional = veiculoRepository.findVeiculoEntityByPlaca(placa);
        if (veiculoOptional.isEmpty()) {
            throw new RegraDeNegocioException("Veículo não encontrado");
        }
        VeiculoEntity veiculoEntity = new VeiculoEntity();
        veiculoEntity.setTipoVeiculo(veiculoOptional.get().getTipoVeiculo());
        veiculoEntity.setPlaca(veiculoOptional.get().getPlaca());
        veiculoEntity.setIdVeiculo(veiculoOptional.get().getIdVeiculo());
        ClienteDTO cliente = clienteService.findClienteById(veiculoOptional.get().getDono().getIdCliente());
        veiculoEntity.setDono(objectMapper.convertValue(cliente,ClienteEntity.class));
        veiculoEntity.getDono().setIdCliente(cliente.getId());

        return veiculoEntity;
    }
}
