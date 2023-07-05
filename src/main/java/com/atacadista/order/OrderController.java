package com.atacadista.order;

import com.atacadista.controller.AbstractEntityController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("orders")
public class OrderController extends AbstractEntityController<OrderRequestDTO, OrderResponseDTO, Integer> {

    @Autowired
    private OrderRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    @Override
    public void insert(@RequestBody OrderRequestDTO data) {
        Order orderBean = new Order(data);
        repository.save(orderBean);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    @Override
    public List<OrderResponseDTO> selectAll() {
        return repository.findAll().stream()
                .map(OrderResponseDTO::new)
                .collect(Collectors.toList());
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{id}")
    @Override
    public OrderResponseDTO selectById(@PathVariable Integer id) {
        Order orderBean = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Pedido com o ID especificado nao encontrado: " + id
                ));
        return new OrderResponseDTO(orderBean);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    @Override
    public void deleteById(@PathVariable Integer id) {
        repository.deleteById(id);
    }

    @PutMapping("/{id}")
    @Override
    public OrderResponseDTO update(@PathVariable Integer id, @RequestBody OrderRequestDTO orderRequestDTO) {
        Order orderBean = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Pedido com o ID especificado nao encontrado: " + id
                ));

        orderBean.setOrderDate(orderRequestDTO.orderDate());
        orderBean.setUser(orderRequestDTO.user());
        orderBean.setApproved(orderRequestDTO.approved());
        orderBean.setIdManager(orderRequestDTO.idManager());

        Order updatedOrder = repository.save(orderBean);

        return new OrderResponseDTO(updatedOrder);
    }
}
