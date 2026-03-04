package org.example;

import jakarta.persistence.*;
import org.example.entidades.Alumno;
import org.example.entidades.Curso;

import java.awt.*;
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

//              Parte que se utiliza con la demostración de @ManyToOne estrategias de carga
//             Cuando es de @OneToMany por defecto es EAGER

//            Alumno alumno = em.find(Alumno.class,1);
//            System.out.println("Se recupero el curso: "+unitUtil.isLoaded(alumno,"curso"));
//            System.out.println("Curso "+alumno.getCurso().toString());

//            En este ejemplo cuando es de @ManyToOne por defecto es lazy
/*
            Curso curso = em.find(Curso.class,1);
            System.out.println("Se recuperaron los alumnos: "+
                    unitUtil.isLoaded(curso, "alumnos"));

            for (Alumno a: curso.getAlumnos()){
                System.out.println("Alumnos: "+a.toString());
            }
*/
///*
//            Aqí muestra un error al utilizar lazy
            Curso curso = em.find(Curso.class,1);
            System.out.println("se recuperaron los alumnos: "+
                    unitUtil.isLoaded(curso,"alumnos"));
//           Aquí nos va a dar un error ya que cuando usas detach sacas de persistencia curso
            em.detach(curso);

//            Y como LAZY lo carga hasta que los necesita en la siguinete linea no lo va a encontrar
//            si fuese EAGER no saldria el error
            for(Alumno a : curso.getAlumnos()){
                System.out.println("Alumnos: "+a.toString());
            }
            em.getTransaction().commit();

        }
    }
}