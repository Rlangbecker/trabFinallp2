package br.org.fundatec.trabalhofinal.demo.service;

import br.org.fundatec.trabalhofinal.demo.dto.endereco.EnderecoCreateDTO;
import br.org.fundatec.trabalhofinal.demo.dto.endereco.EnderecoDTO;
import br.org.fundatec.trabalhofinal.demo.entity.EnderecoEntity;
import br.org.fundatec.trabalhofinal.demo.repository.EnderecoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    private final ObjectMapper objectMapper;

    public EnderecoDTO create(EnderecoCreateDTO enderecoCreateDTO) {

        EnderecoEntity endereco = objectMapper.convertValue(enderecoCreateDTO, EnderecoEntity.class);

        EnderecoEntity enderecoRetorno = enderecoRepository.save(endereco);

        EnderecoDTO enderecoDTO = objectMapper.convertValue(enderecoRetorno, EnderecoDTO.class);
        enderecoDTO.setId(enderecoRetorno.getIdEndereco());

        return enderecoDTO;
    }

    public List<EnderecoDTO> listAll() {
        return enderecoRepository.findAll().stream()
                .map(enderecoEntity -> {
                    EnderecoDTO enderecoDTO = objectMapper.convertValue(enderecoEntity, EnderecoDTO.class);
                    enderecoDTO.setId(enderecoEntity.getIdEndereco());
                    return enderecoDTO;
                })
                .toList();
    }

    private EnderecoEntity findById(Integer idEndereco) {
        EnderecoEntity enderecoRetorno = enderecoRepository.findById(idEndereco).orElse(null);
        if (enderecoRetorno!=null) {
            EnderecoEntity enderecoEntity = objectMapper.convertValue(enderecoRetorno, EnderecoEntity.class);
            enderecoEntity.setIdEndereco(enderecoRetorno.getIdEndereco());
            return enderecoEntity;
        } else {
            return null;
        }
    }

    public EnderecoDTO update(Integer idEndereco, EnderecoCreateDTO enderecoCreateDTO) {
        EnderecoEntity endereco = findById(idEndereco);

        endereco.setBairro(enderecoCreateDTO.getBairro());
        endereco.setCep(enderecoCreateDTO.getCep());
        endereco.setCidade(enderecoCreateDTO.getCidade());
        endereco.setBairro(enderecoCreateDTO.getBairro());
        endereco.setRua(enderecoCreateDTO.getRua());
        endereco.setNumero(enderecoCreateDTO.getNumero());

        EnderecoEntity enderecoEntityRepositorio = enderecoRepository.save(endereco);

        EnderecoDTO enderecoRetorno = objectMapper.convertValue(enderecoEntityRepositorio, EnderecoDTO.class);
        enderecoRetorno.setId(enderecoEntityRepositorio.getIdEndereco());
        return enderecoRetorno;
    }

    public EnderecoDTO findEnderecoById(Integer idEndereco) {
        EnderecoEntity endereco = findById(idEndereco);
        EnderecoDTO enderecoDTO = objectMapper.convertValue(endereco, EnderecoDTO.class);
        if (enderecoDTO != null){
            enderecoDTO.setId(idEndereco);
            return enderecoDTO;
        }
        return null;
    }

    public void delete(Integer idEndereco) {
        findById(idEndereco);
        enderecoRepository.deleteById(idEndereco);
    }


}
