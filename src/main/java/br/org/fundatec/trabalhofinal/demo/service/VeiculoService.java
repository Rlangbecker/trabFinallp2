package br.org.fundatec.trabalhofinal.demo.service;

import br.org.fundatec.trabalhofinal.demo.dto.VeiculoCreateDTO;
import br.org.fundatec.trabalhofinal.demo.dto.VeiculoDTO;
import br.org.fundatec.trabalhofinal.demo.dto.cliente.ClienteDTO;
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

    public VeiculoDTO create(VeiculoCreateDTO veiculoCreateDTO, TipoVeiculo tipoVeiculo) {

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
                    VeiculoDTO veiculoDTO = objectMapper.convertValue(veiculoEntity, VeiculoDTO.class);
                    veiculoDTO.setIdVeiculo(veiculoEntity.getIdVeiculo());
                    ClienteDTO clienteDTO = clienteService.findClienteById(veiculoEntity.getDono().getIdCliente());
                    veiculoDTO.setClienteDTO(clienteDTO);
                    return veiculoDTO;
                })
                .toList();
    }

    private VeiculoEntity findById (Integer id) throws RegraDeNegocioException {
       Optional<VeiculoEntity> veiculo = veiculoRepository.findById(id);
       if(veiculo.isEmpty()){
           throw new RegraDeNegocioException("Veículo não encontrado pelo ID - "+ id);
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
}
