package com.atacadista.sale;

import com.atacadista.controller.AbstractEntityController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("sales")
public class SaleController extends AbstractEntityController<SaleRequestDTO, SaleResponseDTO, Integer> {

    @Autowired
    private SaleRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    @Override
    public void insert(@RequestBody SaleRequestDTO data) {
        Sale saleBean = new Sale(data);
        repository.save(saleBean);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    @Override
    public List<SaleResponseDTO> selectAll() {
        return repository.findAll().stream()
                .map(SaleResponseDTO::new)
                .collect(Collectors.toList());
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{id}")
    @Override
    public SaleResponseDTO selectById(@PathVariable Integer id) {
        Sale saleBean = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Venda com o ID especificado nao encontrada: " + id
                ));
        return new SaleResponseDTO(saleBean);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    @Override
    public void deleteById(@PathVariable Integer id) {
        repository.deleteById(id);
    }

    @PutMapping("/{id}")
    @Override
    public SaleResponseDTO update(@PathVariable Integer id, @RequestBody SaleRequestDTO saleRequestDTO) {
        Sale saleBean = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Venda com o ID especificado nao encontrada: " + id
                ));

        saleBean.setSaleDate(saleRequestDTO.saleDate());
        saleBean.setUser(saleRequestDTO.user());

        Sale updatedSale = repository.save(saleBean);

        return new SaleResponseDTO(updatedSale);
    }
}
