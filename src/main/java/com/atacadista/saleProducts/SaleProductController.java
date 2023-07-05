package com.atacadista.saleProducts;

import com.atacadista.controller.AbstractRelationalController;
import com.atacadista.sale.Sale;
import com.atacadista.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("sales/describe")
public class SaleProductController extends AbstractRelationalController<SaleProductRequestDTO, SaleProductResponseDTO, Integer, Integer> {

    @Autowired
    private SaleProductRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    @Override
    public SaleProductResponseDTO insert(@RequestBody SaleProductRequestDTO data) {
        SaleProduct saleProductBean = new SaleProduct(data);
        SaleProduct savedSaleProduct = repository.save(saleProductBean);
        return new SaleProductResponseDTO(savedSaleProduct);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    @Override
    public List<SaleProductResponseDTO> selectAll() {
        return repository.findAll().stream()
                .map(SaleProductResponseDTO::new)
                .collect(Collectors.toList());
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{idSale}/{idProduct}")
    @Override
    public SaleProductResponseDTO selectById(@PathVariable Integer idSale, @PathVariable Integer idProduct) {
        SaleProductId id = new SaleProductId(idSale, idProduct);
        SaleProduct saleProductBean = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Produto nao encontrado na venda: " + id
                ));

        return new SaleProductResponseDTO(saleProductBean);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{idSale}/{idProduct}")
    @Override
    public ResponseEntity<SaleProductResponseDTO> deleteById(@PathVariable Integer idSale, @PathVariable Integer idProduct) {
        SaleProductId id = new SaleProductId(idSale, idProduct);

        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        } return ResponseEntity.badRequest().build();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{idSale}/{idProduct}")
    @Override
    public SaleProductResponseDTO update(@PathVariable Integer idSale, @PathVariable Integer idProduct, @RequestBody SaleProductRequestDTO saleProductRequestDTO) {
        SaleProductId id = new SaleProductId(idSale, idProduct);
        SaleProduct saleProductBean = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Produto nao encontrado na venda: " + id
                ));

        saleProductBean.setSale( new Sale( saleProductRequestDTO.sale() ));
        saleProductBean.setProduct( new Product( saleProductRequestDTO.product() ));
        saleProductBean.setQuantity( saleProductRequestDTO.quantity() );
        saleProductBean.setPrice( saleProductRequestDTO.price() );

        SaleProduct updatedSaleProduct = repository.save(saleProductBean);

        return new SaleProductResponseDTO(updatedSaleProduct);
    }
}
