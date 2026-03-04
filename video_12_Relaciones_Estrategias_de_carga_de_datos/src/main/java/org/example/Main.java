package org.example;

import jakarta.persistence.*;
import org.example.entidades.Alumno;
import org.example.entidades.Curso;

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
            PersistenceUnitUtil unitUtil = emf.getPersistenceUnitUtil();
            em.getTransaction().begin();

//            Curso curso = new Curso(
//                    "Matematicas",
//                    1,
//                    "Jorge Campos"
//            ) ;
//
//            Alumno alumno1 = new Alumno(
//                    "Pedro Perez",
//                    LocalDate.of(2025, Month.APRIL,15),
//                    "pedro@gmail.com",
//                    curso);
//            Alumno alumno2 = new Alumno(
//                    "Luis Vivas",
//                    LocalDate.of(2005,Month.FEBRUARY,6),
//                    "luis@gmail.com",
//                    curso);
//            Alumno alumno3 = new Alumno(
//                    "Angel Puentes",
//                    LocalDate.of(2004,Month.JULY,9),
//                    "angel@gmail.com",
//                    curso);
//
//            curso.setAlumnos((List.of(alumno1,alumno2,alumno3)));
//            em.persist(curso);

            Alumno alumno = em.find(Alumno.class,1);
            System.out.println("Se recupero el curso: "+unitUtil.isLoaded(alumno,"curso"));
            System.out.println("Curso "+alumno.getCurso().toString());
            em.getTransaction().commit();

        }
    }
}