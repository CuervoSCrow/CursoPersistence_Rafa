package org.example.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.example.dao.CursoDAO;
import org.example.entidades.Curso;

import java.util.List;
import java.util.Optional;

public class CursoDAOImpl implements CursoDAO {
    private final EntityManager em;

    public CursoDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Curso> findAll() {
        String str="SELETC c FROM Curso c";
        TypedQuery<Curso> query = em.createQuery(str,Curso.class);
        return query.getResultList();
    }

    @Override
    public Optional<Curso> findById(Integer id) {
        return Optional.of(em.find(Curso.class,id));
    }

    @Override
    public void create(Curso curso) {
        em.persist(curso);
    }

    @Override
    public void update(Curso curso) {
        em.merge(curso);
    }

    @Override
    public void delete(Curso curso) {
        em.remove(curso);
    }
}
