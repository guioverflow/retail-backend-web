package com.atacadista.supplier;

import com.atacadista.controller.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("suppliers")
public class SupplierController extends AbstractController<SupplierRequestDTO, SupplierResponseDTO, Integer> {

    @Autowired
    private SupplierRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    @Override
    public void insert(@RequestBody SupplierRequestDTO data) {
        Supplier supplierBean = new Supplier(data);
        repository.save(supplierBean);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    @Override
    public List<SupplierResponseDTO> selectAll() {
        return repository.findAll().stream()
                .map(SupplierResponseDTO::new)
                .collect(Collectors.toList());
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{id}")
    @Override
    public SupplierResponseDTO selectById(@PathVariable Integer id) {
        Supplier supplierBean = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Fornecedor com o ID especificado nao encontrado: " + id
                ));
        return new SupplierResponseDTO(supplierBean);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    @Override
    public void deleteById(@PathVariable Integer id) {
        repository.deleteById(id);
    }

    @PutMapping("/{id}")
    @Override
    public SupplierResponseDTO update(@PathVariable Integer id, @RequestBody SupplierRequestDTO supplierRequestDTO) {
        Supplier supplierBean = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Fornecedor com o ID especificado nao encontrado: " + id
                ));

        supplierBean.setTradingName(supplierRequestDTO.tradingName());
        supplierBean.setPhone(supplierRequestDTO.phone());
        supplierBean.setEmail(supplierRequestDTO.email());

        Supplier updatedSupplier = repository.save(supplierBean);

        return new SupplierResponseDTO(updatedSupplier);
    }
}
