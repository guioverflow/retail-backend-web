package com.atacadista.stock;

import com.atacadista.controller.AbstractRelationalController;
import com.atacadista.establishment.Establishment;
import com.atacadista.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("stock")
public class StockController extends AbstractRelationalController<StockRequestDTO, StockResponseDTO, Integer, Integer> {

    @Autowired
    private StockRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    @Override
    public StockResponseDTO insert(@RequestBody StockRequestDTO data) {
        Stock stockBean = new Stock(data);
        Stock savedStock = repository.save(stockBean);

        return new StockResponseDTO(savedStock);
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
    @GetMapping("/{idEstablishment}/{idProduct}")
    @Override
    public StockResponseDTO selectById(@PathVariable Integer idEstablishment, @PathVariable Integer idProduct) {
        StockId id = new StockId(idEstablishment, idProduct);
        Stock stockBean = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Registro de estoque nao encontrado: " + id
                ));
        return new StockResponseDTO(stockBean);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{idEstablishment}/{idProduct}")
    @Override
    public ResponseEntity<StockResponseDTO> deleteById(@PathVariable Integer idEstablishment, @PathVariable Integer idProduct) {
        StockId id = new StockId(idEstablishment, idProduct);

        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        } return ResponseEntity.badRequest().build();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{idEstablishment}/{idProduct}")
    @Override
    public StockResponseDTO update(@PathVariable Integer idEstablishment, @PathVariable Integer idProduct, @RequestBody StockRequestDTO stockRequestDTO) {
        StockId id = new StockId(idEstablishment, idProduct);
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
