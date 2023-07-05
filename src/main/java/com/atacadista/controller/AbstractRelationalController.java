package com.atacadista.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public abstract class AbstractRelationalController<requestType, responseType, firstIdType, secondIdType> {

    // CREATE
    public abstract responseType insert(@RequestBody requestType data);

    // READ
    public abstract List<responseType> selectAll();
    public abstract responseType selectById(@PathVariable firstIdType firstIdType, @PathVariable secondIdType secondIdType);

    // UPDATE
    public abstract responseType update(@PathVariable firstIdType firstIdType, @PathVariable secondIdType secondIdType, @RequestBody requestType requestDTO);

    // DELETE
    public abstract ResponseEntity<responseType> deleteById(@PathVariable firstIdType firstIdType, @PathVariable secondIdType secondIdType);
}
