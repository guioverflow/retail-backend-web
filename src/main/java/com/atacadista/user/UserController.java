package com.atacadista.user;

import com.atacadista.controller.AbstractEntityController;
import com.atacadista.establishment.Establishment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("users")
public class UserController extends AbstractEntityController<UserRequestDTO, UserResponseDTO, Integer> {

    @Autowired
    private UserRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    @Override
    public void insert(@RequestBody UserRequestDTO data) {
        User userBean = new User(data);
        repository.save(userBean);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    @Override
    public List<UserResponseDTO> selectAll() {
        return repository.findAll().stream()
                .map(UserResponseDTO::new)
                .collect(Collectors.toList());
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{id}")
    @Override
    public UserResponseDTO selectById(@PathVariable Integer id) {
        User userBean = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Funcionario não encontrado com ID: " + id
                ));
        return new UserResponseDTO(userBean);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    @Override
    public void deleteById(@PathVariable Integer id) {
        repository.deleteById(id);
    }

    @PutMapping("/{id}")
    @Override
    public UserResponseDTO update(@PathVariable Integer id, @RequestBody UserRequestDTO userRequestDTO) {
        User userBean = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "User com o ID especificado nao encontrado: " + id
                ));

        userBean.setEstablishment(new Establishment( userRequestDTO.establishment() ));
        userBean.setUserRole(userRequestDTO.userRole());
        userBean.setDisplayName(userRequestDTO.displayName());
        userBean.setUsername(userRequestDTO.username());
        userBean.setPassword(userRequestDTO.password());

        User updatedUser = repository.save(userBean);

        return new UserResponseDTO(updatedUser);
    }
}
