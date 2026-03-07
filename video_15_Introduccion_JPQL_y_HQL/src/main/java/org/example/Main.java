package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import org.example.dto.AlumnoDto;
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

            em.getTransaction().begin();
/*
            Curso curso1 = new Curso("Matematicas",1,"Jorge Campos");
            Curso curso2 = new Curso("Castellano",2,"Angel Perez");
            Curso curso3 = new Curso("Ingles",1,"Oscar Landaeta");
            Curso curso4 = new Curso("Castellano",1,"Angela Perez");

            Alumno alumno1=new Alumno("Pedro Perez",
                                        LocalDate.of(2005,Month.APRIL,15),
                                        "pedro@mail.com",
                                        8,
                                        curso1);
            Alumno alumno2=new Alumno("Luis Vivas",
                                        LocalDate.of(2005,Month.FEBRUARY,6),
                                        "luis@mail.com",
                                        7,
                                        curso1);
            Alumno alumno3=new Alumno("AngelPuentes",
                                        LocalDate.of(2004,Month.JULY,9),
                                        "angel@mail.com",
                                        4,
                                        curso1);
            Alumno alumno4=new Alumno("Jorge Cadenas",
                                        LocalDate.of(2004,Month.AUGUST,19),
                                        "jorge@mail.com",
                                        6,
                                        curso2);
            Alumno alumno5=new Alumno("Maria Valero",
                                        LocalDate.of(2005,Month.SEPTEMBER,5),
                                        "maria@mail.com",
                                        3,
                                        curso2);
            Alumno alumno6=new Alumno("Natalia Moreno",
                                        LocalDate.of(2005,Month.FEBRUARY,9),
                                        "natalia@mail.com",
                                        9,
                                        curso2);
            Alumno alumno7 = new Alumno("Guillermo Parada",
                                        LocalDate.of(2004,Month.MAY,23),
                                        "guillermo@gmail.com",
                                        7,
                                        curso3);
            Alumno alumno8 = new Alumno("Olga Garcia",
                                        LocalDate.of(2004,Month.JANUARY,29),
                                        "olga@mail.com",
                                        3,
                                        curso3);
            Alumno alumno9 = new Alumno("Teresa Carmago",
                                        LocalDate.of(2005, Month.FEBRUARY,12),
                                        "teresa@mail.com",
                                        8,
                                        curso3);

            curso1.setAlumnos(List.of(alumno1,alumno2,alumno3));
            em.persist(curso1);
            curso2.setAlumnos(List.of(alumno4,alumno5,alumno6));
            em.persist(curso2);
            curso3.setAlumnos(List.of(alumno7,alumno8,alumno9));
            em.persist(curso3);

            em.persist(curso4);

 */
//            String str ="SELECT NEW org.example.dto.AlumnoDto(a.id,a.nombre,a.nota) " +
//                    "FROM Alumno a";
//            Query query =em.createQuery(str);
//            List<AlumnoDto> alumnos = query.getResultList();
//
//            for (AlumnoDto a : alumnos){
//                System.out.println("Aluumno "+a.toString());
//            }

            String str = "SELECT NEW org.example.dto.AlumnoDto(a.id,a.nombre,a.nota) " +
                    "FROM Alumno a " +
                    "WHERE a.nota>=5 " +
                    "ORDER BY a.nota DESC";
            Query query =em.createQuery(str);
            List<AlumnoDto> alumnos = query.getResultList();

            for (AlumnoDto a : alumnos){
                System.out.println("Aluumno "+a.toString());
            }

            String str2 = "SELECT c FROM Curso c " +
                    "WHERE c.alumnos IS NOT EMPTY";
            query = em.createQuery(str2);
            List<Curso> curso = query.getResultList();
            for(Curso c : curso){
                System.out.println("Curso Empty "+c.toString());
            }

            Alumno alumno = em.find(Alumno.class,1);
            String str3 = "SELECT c FROM Curso c " +
                    "WHERE :alumn NOT MEMBER OF c.alumnos";
            query = em.createQuery(str3);
            query.setParameter("alumn",alumno);
            List<Curso> cursos = query.getResultList();
            for(Curso c: cursos){
                System.out.println("Curso: "+c.toString());
            }

            em.getTransaction().commit();

        }catch (Exception e){
            System.out.println("Error: "+e.getMessage());
        }
    }

}