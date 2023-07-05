package com.atacadista.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public abstract class AbstractEntityController<requestType, responseType, identificator> {

    // CREATE
    public abstract void insert(@RequestBody requestType data);

    // READ
    public abstract List<responseType> selectAll();
    public abstract responseType selectById(@PathVariable identificator id);

    // UPDATE
    public abstract responseType update(@PathVariable identificator id, @RequestBody requestType requestDTO);

    // DELETE
    public abstract void deleteById(@PathVariable identificator id);
}
