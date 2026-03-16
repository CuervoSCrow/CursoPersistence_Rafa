package org.example.dao;

import org.example.entidades.Alumno;

import java.util.List;
import java.util.Optional;

public interface AlumnoDAO {
    List<Alumno> findAll();
    Optional<Alumno> findById(Integer Id);
    void create(Alumno alumno);
    void update(Alumno alumno);
    void delete(Alumno alumno);
}
