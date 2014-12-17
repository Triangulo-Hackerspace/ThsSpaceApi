package br.net.triangulohackerspace.spaceapi.service;

import java.io.Serializable;
import java.util.List;

public interface BusinessService<E, ID extends Serializable> {

    E save(E e);

    List<E> getList();

}
