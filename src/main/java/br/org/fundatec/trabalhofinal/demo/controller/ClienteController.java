package br.org.fundatec.trabalhofinal.demo.controller;

import br.org.fundatec.trabalhofinal.demo.dto.cliente.ClienteCreateDTO;
import br.org.fundatec.trabalhofinal.demo.dto.cliente.ClienteDTO;
import br.org.fundatec.trabalhofinal.demo.dto.endereco.EnderecoDTO;
import br.org.fundatec.trabalhofinal.demo.exception.RegraDeNegocioException;
import br.org.fundatec.trabalhofinal.demo.service.ClienteService;
import br.org.fundatec.trabalhofinal.demo.service.EnderecoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    private final EnderecoService enderecoService;
    @PostMapping
    public ResponseEntity create(ClienteCreateDTO clienteCreateDTO){
        EnderecoDTO enderecoDTO = enderecoService.findEnderecoById(clienteCreateDTO.getIdEndereco());
        if(enderecoDTO==null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("ID_ENDEREÇO ["+ clienteCreateDTO.getIdEndereco() +"] NÃO ENCONTRADO");
        }
        ClienteDTO clienteDTO = clienteService.create(clienteCreateDTO);
        return new ResponseEntity<>(clienteDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listAll(){
        List<ClienteDTO> clienteDTOList = clienteService.listAll();
        return new ResponseEntity<>(clienteDTOList,HttpStatus.OK);
    }

    @GetMapping("/find-by-id")
    public ResponseEntity<ClienteDTO> findById(@RequestParam("idCliente")Integer idCliente) throws RegraDeNegocioException {
        ClienteDTO clienteDTO = clienteService.findClienteById(idCliente);
        return new ResponseEntity<>(clienteDTO,HttpStatus.OK);
    }

    @PutMapping("/{idCliente}")
    public ResponseEntity<ClienteDTO> update(@PathVariable("idCliente")Integer idCliente,ClienteCreateDTO clienteCreateDTO) throws RegraDeNegocioException {
        ClienteDTO clienteDTO= clienteService.update(idCliente,clienteCreateDTO);
        return new ResponseEntity<>(clienteDTO,HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam("idCliente")Integer idCliente) throws RegraDeNegocioException {
        clienteService.delete(idCliente);
        return new ResponseEntity<>(null,HttpStatus.OK);
    }

}
