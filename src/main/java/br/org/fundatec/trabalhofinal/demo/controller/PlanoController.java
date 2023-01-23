package br.org.fundatec.trabalhofinal.demo.controller;

import br.org.fundatec.trabalhofinal.demo.dto.plano.PlanoCreateDTO;
import br.org.fundatec.trabalhofinal.demo.dto.plano.PlanoDTO;
import br.org.fundatec.trabalhofinal.demo.exception.RegraDeNegocioException;
import br.org.fundatec.trabalhofinal.demo.service.PlanoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/plano")
public class PlanoController {

    private final PlanoService planoService;


    @PostMapping
    public ResponseEntity<PlanoDTO> create(@RequestParam("idCliente")Integer idCliente, @RequestBody PlanoCreateDTO planoCreateDTO) throws RegraDeNegocioException {
        PlanoDTO planoDTO = planoService.create(idCliente,planoCreateDTO);
        return new ResponseEntity<>(planoDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PlanoDTO>> listAll(){
        return new ResponseEntity<>(planoService.listAll(),HttpStatus.OK);
    }

    @PutMapping("/fazer-recarga/{idCliente}")
    public ResponseEntity<PlanoDTO> fazerRecarga(@PathVariable("idCliente") Integer idCliente,@RequestBody PlanoCreateDTO planoCreateDTO) throws RegraDeNegocioException {
        PlanoDTO planoDTO = planoService.fazerRecarga(idCliente,planoCreateDTO);
        return new ResponseEntity<>(planoDTO,HttpStatus.OK);
    }
}
