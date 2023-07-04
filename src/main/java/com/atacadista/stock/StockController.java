package com.atacadista.stock;

import com.atacadista.controller.AbstractController;
import com.atacadista.establishment.Establishment;
import com.atacadista.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("stock")
public class StockController extends AbstractController<StockRequestDTO, StockResponseDTO, StockId> {

    @Autowired
    private StockRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    @Override
    public void insert(@RequestBody StockRequestDTO data) {
        Stock stockBean = new Stock(data);
        repository.save(stockBean);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    @Override
    public List<StockResponseDTO> selectAll() {
        return repository.findAll().stream()
                .map(StockResponseDTO::new)
                .collect(Collectors.toList());
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{id}")
    @Override
    public StockResponseDTO selectById(@PathVariable StockId id) {
        Stock stockBean = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Registro de estoque nao encontrado: " + id
                ));
        return new StockResponseDTO(stockBean);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    @Override
    public void deleteById(@PathVariable StockId id) {
        repository.deleteById(id);
    }

    @PutMapping("/{id}")
    @Override
    public StockResponseDTO update(@PathVariable StockId id, @RequestBody StockRequestDTO stockRequestDTO) {
        Stock stockBean = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Estoque não encontrado com ID: " + id
                ));

        stockBean.setEstablishment( new Establishment( stockRequestDTO.establishment() ));
        stockBean.setProduct( new Product( stockRequestDTO.product() ));
        stockBean.setQuantity( stockRequestDTO.quantity() );

        Stock updatedStock = repository.save(stockBean);

        return new StockResponseDTO(updatedStock);
    }
}
