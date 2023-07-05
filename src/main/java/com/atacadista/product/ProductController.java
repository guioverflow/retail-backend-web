package com.atacadista.product;

import java.util.List;
import java.util.stream.Collectors;

import com.atacadista.controller.AbstractEntityController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/products")
public class ProductController extends AbstractEntityController<ProductRequestDTO, ProductResponseDTO, Integer> {

    @Autowired
    private ProductRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    @Override
    public void insert(@RequestBody ProductRequestDTO data) {
        Product productBean = new Product(data);
        repository.save(productBean);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    @Override
    public List<ProductResponseDTO> selectAll() {
        return repository.findAll().stream()
                .map(ProductResponseDTO::new)
                .collect(Collectors.toList());
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{id}")
    @Override
    public ProductResponseDTO selectById(@PathVariable Integer id) {
        Product productBean = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Produto com o ID especificado nao encontrado: " + id
                ));
        return new ProductResponseDTO(productBean);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    @Override
    public void deleteById(@PathVariable Integer id) {
        repository.deleteById(id);
    }

    @PutMapping("/{id}")
    @Override
    public ProductResponseDTO update(@PathVariable Integer id, @RequestBody ProductRequestDTO productRequestDTO) {
        Product productBean = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Produto não encontrado com ID: " + id
                ));

        productBean.setGTIN(productRequestDTO.GTIN());
        productBean.setName(productRequestDTO.name());

        Product updatedProduct = repository.save(productBean);

        return new ProductResponseDTO(updatedProduct);
    }
}
