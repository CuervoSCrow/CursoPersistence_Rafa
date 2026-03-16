package org.example.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.example.dao.AlumnoDAO;
import org.example.entidades.Alumno;

import java.util.List;
import java.util.Optional;

public class AlumnoDAOImpl implements AlumnoDAO {

    private final EntityManager em;

    public AlumnoDAOImpl(EntityManager em) {
        this.em=em;
    }

    @Override
    public List<Alumno> findAll() {
        String str = "SELECT a FROM Alumno a";
        TypedQuery<Alumno> query = em.createQuery(str, Alumno.class);
        return query.getResultList();
    }

    @Override
    public Optional<Alumno> findById(Integer id) {

        return Optional.of(em.find(Alumno.class,id));
    }

    @Override
    public void create(Alumno alumno) {
        em.persist(alumno);
    }

    @Override
    public void update(Alumno alumno) {
        em.merge(alumno);
    }

    @Override
    public void delete(Alumno alumno) {
        em.remove(alumno);
    }
}
