package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.entidades.Alumno;
import org.example.entidades.Curso;
import org.example.entidades.Salon;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

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

            Curso curso = new Curso("Matematicas",1,"JorgeCampos");
            em.persist(curso);
            Alumno alumno1= new Alumno(
                    "Pedro Perez",
                    LocalDate.of(2005, Month.APRIL,15),
                    "pedro@gmail.com",
                    curso
            );
            em.persist(alumno1);
            Alumno alumno2 = new Alumno(
                    "Luis Vivas",
                    LocalDate.of(2005,Month.FEBRUARY,6),
                    "luis@gmail.com",
                    curso
            );
            em.persist(alumno2);

            em.getTransaction().commit();

//

            em.getTransaction().begin();
            em.clear();
            curso= em.find(Curso.class,1);
            System.out.println("Curso: "+curso.toString());
            em.getTransaction().commit();

            em.getTransaction().begin();

            alumno1 = em.find(Alumno.class,1);
            alumno2 = em.find(Alumno.class,2);

            Salon salon = new Salon(
                    "Sala-01",
                    List.of(alumno1,alumno2));
            em.persist(salon);

            em.getTransaction().commit();
        }
    }
}