package br.org.fundatec.trabalhofinal.demo.controller;

import br.org.fundatec.trabalhofinal.demo.dto.endereco.EnderecoCreateDTO;
import br.org.fundatec.trabalhofinal.demo.dto.endereco.EnderecoDTO;
import br.org.fundatec.trabalhofinal.demo.service.EnderecoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/endereco")
public class EnderecoController {

    private final EnderecoService enderecoService;

    @PostMapping
    public ResponseEntity<EnderecoDTO> create(@RequestBody EnderecoCreateDTO enderecoCreateDTO) {
        return new ResponseEntity<>(enderecoService.create(enderecoCreateDTO), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<EnderecoDTO>> findAll() {
        return new ResponseEntity<>(enderecoService.listAll(), HttpStatus.OK);
    }

    @GetMapping("/find-by-id")
    public ResponseEntity<EnderecoDTO> findById(@RequestParam("id") Integer id) {
        EnderecoDTO enderecoDTO = enderecoService.findEnderecoById(id);
        return new ResponseEntity<>(enderecoDTO, HttpStatus.OK);
    }

    @PutMapping("/{idEndereco}")
    public ResponseEntity<EnderecoDTO> update(@PathVariable("idEndereco") Integer id, @RequestBody EnderecoCreateDTO enderecoCreateDTO) {
        EnderecoDTO enderecoDTO = enderecoService.update(id, enderecoCreateDTO);
        return new ResponseEntity<>(enderecoDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{idEndereco}")
    public ResponseEntity<Void> delete(@PathVariable("idEndereco") Integer idEndereco) {
        enderecoService.delete(idEndereco);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
