package com.atacadista.orderProduct;

import com.atacadista.controller.AbstractRelationalController;
import com.atacadista.order.Order;
import com.atacadista.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("orders/describe")
public class OrderProductController extends AbstractRelationalController<OrderProductRequestDTO, OrderProductResponseDTO, Integer, Integer> {

    @Autowired
    private OrderProductRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    @Override
    public OrderProductResponseDTO insert(@RequestBody OrderProductRequestDTO data) {
        OrderProduct orderProductBean = new OrderProduct(data);
        OrderProduct savedOrderProduct = repository.save(orderProductBean);
        return new OrderProductResponseDTO(savedOrderProduct);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    @Override
    public List<OrderProductResponseDTO> selectAll() {
        return repository.findAll().stream()
                .map(OrderProductResponseDTO::new)
                .collect(Collectors.toList());
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{idOrder}/{idProduct}")
    @Override
    public OrderProductResponseDTO selectById(@PathVariable Integer idOrder, @PathVariable Integer idProduct) {
        OrderProductId id = new OrderProductId(idOrder, idProduct);
        OrderProduct orderProductBean = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Produto nao encontrado no pedido: " + id
                ));

        return new OrderProductResponseDTO(orderProductBean);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{idOrder}/{idProduct}")
    @Override
    public ResponseEntity<OrderProductResponseDTO> deleteById(@PathVariable Integer idOrder, @PathVariable Integer idProduct) {
        OrderProductId id = new OrderProductId(idOrder, idProduct);

        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        } return ResponseEntity.badRequest().build();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{idOrder}/{idProduct}")
    @Override
    public OrderProductResponseDTO update(@PathVariable Integer idOrder, @PathVariable Integer idProduct, @RequestBody OrderProductRequestDTO orderProductRequestDTO) {
        OrderProductId id = new OrderProductId(idOrder, idProduct);
        OrderProduct orderProductBean = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Produto nao encontrado no pedido: " + id
                ));

        orderProductBean.setOrder( new Order( orderProductRequestDTO.order() ));
        orderProductBean.setProduct( new Product( orderProductRequestDTO.product() ));
        orderProductBean.setQuantity( orderProductRequestDTO.quantity() );
        orderProductBean.setPrice( orderProductRequestDTO.price() );

        OrderProduct updatedOrderProduct = repository.save(orderProductBean);

        return new OrderProductResponseDTO(updatedOrderProduct);
    }
}
