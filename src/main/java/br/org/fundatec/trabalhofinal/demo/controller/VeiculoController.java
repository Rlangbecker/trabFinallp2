package br.org.fundatec.trabalhofinal.demo.controller;

import br.org.fundatec.trabalhofinal.demo.dto.VeiculoCreateDTO;
import br.org.fundatec.trabalhofinal.demo.dto.VeiculoDTO;
import br.org.fundatec.trabalhofinal.demo.entity.enums.TipoVeiculo;
import br.org.fundatec.trabalhofinal.demo.service.VeiculoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/veiculo")
public class VeiculoController {

    private final VeiculoService veiculoService;

    @PostMapping
    public ResponseEntity<VeiculoDTO> create(@RequestBody VeiculoCreateDTO veiculoCreateDTO, @RequestParam TipoVeiculo tipoVeiculo){
        VeiculoDTO veiculoDTO = veiculoService.create(veiculoCreateDTO,tipoVeiculo);
        return new ResponseEntity<>(veiculoDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<VeiculoDTO>> listAll(){
        return new ResponseEntity<>(veiculoService.listAll(),HttpStatus.OK);
    }
}
