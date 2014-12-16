package eu.kielczewski.example.service;

import java.util.List;

public interface BusinessService<T> {

    T save(T t);

    List<T> getList();

}
