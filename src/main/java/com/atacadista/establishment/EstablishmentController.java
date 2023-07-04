package com.atacadista.establishment;

import com.atacadista.controller.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("establishments")
public class EstablishmentController extends AbstractController<EstablishmentRequestDTO, EstablishmentResponseDTO, Integer> {

    @Autowired
    private EstablishmentRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    @Override
    public void insert(@RequestBody EstablishmentRequestDTO data) {
        Establishment establishmentBean = new Establishment(data);
        repository.save(establishmentBean);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    @Override
    public List<EstablishmentResponseDTO> selectAll() {
        return repository.findAll().stream()
                .map(EstablishmentResponseDTO::new)
                .collect(Collectors.toList());
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{id}")
    @Override
    public EstablishmentResponseDTO selectById(@PathVariable Integer id) {
        Establishment establishmentBean = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Estabelecimento com o ID especificado nao encontrado: " + id
                ));
        return new EstablishmentResponseDTO(establishmentBean);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    @Override
    public void deleteById(@PathVariable Integer id) {
        repository.deleteById(id);
    }

    @PutMapping("/{id}")
    @Override
    public EstablishmentResponseDTO update(@PathVariable Integer id, @RequestBody EstablishmentRequestDTO establishmentRequestDTO) {
        Establishment establishmentBean = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Estabelecimento não encontrado com ID: " + id
                ));

        establishmentBean.setPhone(establishmentRequestDTO.phone());
        establishmentBean.setCEP(establishmentRequestDTO.CEP());

        Establishment updatedEstablishment = repository.save(establishmentBean);

        return new EstablishmentResponseDTO(updatedEstablishment);
    }
}
