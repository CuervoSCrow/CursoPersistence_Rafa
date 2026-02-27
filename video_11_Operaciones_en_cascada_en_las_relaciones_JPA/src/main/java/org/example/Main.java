package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
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
            EntityManager em=emf.createEntityManager();

            em.getTransaction().begin();

            Curso curso=new Curso("Matematicas",1,"Jorge Campos");


            Alumno alumno1 = new Alumno(
                    "Pedro Perez",
                    LocalDate.of(2025, Month.APRIL,15),
                    "pedro@gmail.com",
                    curso);
            Alumno alumno2= new Alumno(
                    "Luis Vivas",
                    LocalDate.of(2005,Month.FEBRUARY,6),
                    "luis@gmail.com",
                    curso);
            Alumno alumno3 = new Alumno(
                    "Angel Puentes",
                    LocalDate.of(2004, Month.JULY,9),
                    "angel@gmail.com",
                    curso);
//            Caso 1: Sin las anotaciones cascade = CascadeType.PERSIST
//            Marca error al no persistir el curos, pero con la anotacion sealiza
//            la persistencia en cascada
//            em.persist(curso);
//            /*
//          Se comenta para ejecutar  caso 2
            em.persist(alumno1);
            em.persist(alumno2);
            em.persist(alumno3);

//             */

//            Caso 2: La anotacion cascade=CascadeType.PERSIST
//              La anotación PERSIST en este caso se utiliza en la entidad Curso

            curso.setAlumnos(List.of(alumno1,alumno2,alumno3));
            em.persist(curso);


            em.getTransaction().commit();

            em.clear();

//          CascadeType.MERGE
//            Se utiliza en la clase Curso

            em.getTransaction().begin();
            alumno2.setNombre("Luis Manuel Vivas");
            alumno1.setNombre("Pedro Luis Perez");
            em.merge(curso);
            em.getTransaction().commit();

            em.getTransaction().begin();

//            CascadeType.DELETE
//            Caso 1:
//            Si se utiliza el remove sin tener en cascadeType crea error
//            pero el tener cascadeType.REMOVE
//            Cuando hay muchos datos o relaciones entre tablas pueden tarer problemas

//            Caso 2:
//            Se elimina CasacadeType.REMOVE y se agrega @OnDelete(action= OnDeleteAction.CASCADE)
//            da el mismo resultado pero en el codigo sql hace menos instrucciones

            curso = em.find(Curso.class,1);
            em.remove(curso);

            em.getTransaction().commit();

        }
    }
}