package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.entidades.Estudiante;
import org.example.entidades.Persona;
import org.example.entidades.Profesor;

import java.time.LocalDate;
import java.time.Month;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        try(EntityManagerFactory emf =
                    Persistence.createEntityManagerFactory(
                            "lab-persistence-unit"
                    )){
            EntityManager em = emf.createEntityManager();

            em.getTransaction().begin();
            Estudiante estudiante = new Estudiante(
                    "Pedro Perez",
                    "2do B",
                    LocalDate.of(2010, Month.MARCH,13));

            em.persist(estudiante);

            Profesor profesor = new Profesor(
                    "Román Alonso",
                    "Matematicas",
                    "7353709847"
            );
            em.persist(profesor);


            em.getTransaction().commit();
            em.clear();

        }

    }
}