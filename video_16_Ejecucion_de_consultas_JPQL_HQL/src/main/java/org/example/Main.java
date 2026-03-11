package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.entidades.Alumno;
import org.example.entidades.Curso;
import org.example.persistencia.AlumnoDB;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static EntityManager em;
    public static void main(String[] args) {
        try(EntityManagerFactory emf =
                    Persistence.createEntityManagerFactory(
                            "lab-persistence-unit"
                    )){
            em = emf.createEntityManager();

//            cargaInicial();
            AlumnoDB alumnoDB = new AlumnoDB(em);

            System.out.println("******* Buscar Todos los registros Forma 1 *******");
            List<Alumno> alumnos1= alumnoDB.findAll1();
            for(Alumno a : alumnos1){
                System.out.println("Alumno: "+a.toString());
            }

            System.out.println("************ Busca Todos los Registros Forma 2********");

            List<Alumno> alumnos = alumnoDB.findAll();
            for (Alumno a : alumnos){
                System.out.println("Alumno: "+a.toString());
            }

            System.out.println("");
            System.out.println("******* findById *******");

            System.out.println("Busca el Id 2");
            Optional<Alumno> alumno = alumnoDB.findById(2);
            if(alumno.isPresent()){
                System.out.println("Alumno: "+alumno.toString());
            }

            System.out.println("Busca el Id 50");
            alumno = alumnoDB.findById(50);
            if(alumno.isPresent()){
                System.out.println("Alumno: "+alumno.toString());
            }

            System.out.println("***** findById2 *****");
            System.out.println("Busca le Id 3");
            alumno = alumnoDB.findById2(3);
            if(alumno.isPresent()){
                System.out.println("Alumno: "+alumno.toString());
            }

            System.out.println("******* findByNombre *****");
            System.out.println("Busca un nombre que contenga 'sanchez'");
            alumnos = alumnoDB.findByNombre("sanchez");
            for(Alumno a :alumnos){
                System.out.println("Alumno: "+a.toString());
            }

            System.out.println("******* Total de alumnos *****");
            System.out.println("Numero de alumnos "+alumnoDB.count());

            System.out.println("******** getNoteAVG ************");
            System.out.println("Promedio del curso 1: "+alumnoDB.getNoteAvg(1));

            System.out.println("******** getNotAVG2 *************");
            System.out.println("Promedio de curso 1: "+alumnoDB.getNoteAvg2(1));

            System.out.println("******** Promoción de curso ***********");

            System.out.println("Promocionando los alumos del curso 2 al 3");
            if(alumnoDB.promocionalAlumnos(2,3)){
                System.out.println("La promoción se ha realizado correctamente");
            }else{
                System.out.println("Ha ocurrido un error realizando la promocion del curso");
            }
            System.out.println("Promocionando los alumos del curso 1 al 2");
            if(alumnoDB.promocionalAlumnos(1,2)){
                System.out.println("La promoción se ha realizado correctamente");
            }else{
                System.out.println("Ha ocurrido un error realizando la promocion del curso");
            }
        }
    }

    public static void cargaInicial(){
        em.getTransaction().begin();

        try{
            Curso c1 = new Curso("Matematicas",1,"Jorge Campos");
            Curso c2 = new Curso("Matematicas",2,"Angela Perez");
            Curso c3 = new Curso("Matematicas",3,"Oscar Landaeta");

            Alumno a01 = new Alumno("Pedro Perez", LocalDate.of(2005, Month.APRIL,15),"pedro@mail.com",8,c1);
            Alumno a02 = new Alumno("Luis Vivas", LocalDate.of(2005, Month.FEBRUARY,6),"luis@mail.com",7,c1);
            Alumno a03 = new Alumno("Angel Puentes", LocalDate.of(2004, Month.JULY,9),"angel@mail.com",4,c1);
            Alumno a04 = new Alumno("Jorge Cadenas", LocalDate.of(2004, Month.AUGUST,19),"jorge@mail.com",6,c1);
            Alumno a05 = new Alumno("Maria Valero", LocalDate.of(2005, Month.SEPTEMBER,5),"maria@mail.com",3,c1);
            Alumno a06 = new Alumno("Natalia Moreno", LocalDate.of(2005, Month.FEBRUARY,9),"natalia@mail.com",9,c1);
            Alumno a07 = new Alumno("Guillermo Parada", LocalDate.of(2004, Month.MAY,23),"guillermo@mail.com",7,c1);
            Alumno a08 = new Alumno("Olga Garcia", LocalDate.of(2004, Month.JANUARY,29),"olga@mail.com",3,c1);
            Alumno a09 = new Alumno("Teresa Camargo", LocalDate.of(2005, Month.FEBRUARY,12),"teresa@mail.com",8,c1);
            Alumno a10 = new Alumno("Roberto Sainz", LocalDate.of(2005, Month.APRIL,14),"roberto@mail.com",6,c1);
            Alumno a11 = new Alumno("Alexis Peña", LocalDate.of(2005, Month.APRIL,25),"alexis@mail.com",4,c1);
            Alumno a12 = new Alumno("Isabel Ortega", LocalDate.of(2004, Month.MAY,12),"isabel@mail.com",8,c1);
            Alumno a13 = new Alumno("Daniel Sanchez", LocalDate.of(2005, Month.APRIL,15),"daniel@mail.com",6,c1);
            Alumno a14 = new Alumno("Javier Gomez", LocalDate.of(2005, Month.SEPTEMBER,25),"javier@mail.com",5,c1);
            Alumno a15 = new Alumno("Arturo Sanchez", LocalDate.of(2005, Month.APRIL,15),"arturo@mail.com",7,c1);
            c1.setAlumnos(List.of(a01,a02,a03,a04,a05,a06,a07,a08,a09,a10,a11,a12,a13,a14,a15));
            em.persist(c1);


            Alumno a16 = new Alumno("Angela Perez", LocalDate.of(2004, Month.APRIL,18),"angela@mail.com",4,c2);
            Alumno a17 = new Alumno("Luisa Gonzales", LocalDate.of(2003, Month.OCTOBER,16),"luisa@mail.com",6,c2);
            Alumno a18 = new Alumno("Jose Alfaro", LocalDate.of(2004, Month.NOVEMBER,19),"jose@mail.com",7,c2);
            Alumno a19 = new Alumno("Carlos Cadenas", LocalDate.of(2004, Month.DECEMBER,29),"carlos@mail.com",8,c2);
            Alumno a20 = new Alumno("Angeles Valero", LocalDate.of(2003, Month.SEPTEMBER,11),"angeles@mail.com",7,c2);
            Alumno a21 = new Alumno("Josefina Moreno", LocalDate.of(2004, Month.FEBRUARY,13),"josefina@mail.com",9,c2);
            Alumno a22 = new Alumno("Antonio Paredes", LocalDate.of(2004, Month.JANUARY,23),"antonio@mail.com",7,c2);
            Alumno a23 = new Alumno("Hortensia Garcia", LocalDate.of(2004, Month.JANUARY,24),"hortensia@mail.com",3,c2);
            Alumno a24 = new Alumno("Marta Araujo", LocalDate.of(2003, Month.MAY,12),"marta@mail.com",3,c2);
            Alumno a25 = new Alumno("Julio Rojas", LocalDate.of(2004, Month.APRIL,17),"julio@mail.com",6,c2);
            Alumno a26 = new Alumno("Alvaro Rios",LocalDate.of(2003,Month.APRIL,22),"alvaro@gmail.com",4,c2);
            Alumno a27 = new Alumno("Indira Ortega",LocalDate.of(2004,Month.MAY,17),"indira@mail.com",8,c2);
            Alumno a28 = new Alumno("Dolores Diaz",LocalDate.of(2003,Month.JULY,15),"dolores@mail.com",6,c2);
            Alumno a29 = new Alumno("Jacinto Gomez",LocalDate.of(2004,Month.SEPTEMBER,21),"jacinto@mail.com",5,c2);
            Alumno a30 = new Alumno("Ramon Fernandez",LocalDate.of(2003,Month.OCTOBER,15),"ramon@mail.com",7,c2);
            c2.setAlumnos(List.of(a16,a17,a18,a19,a20,a21,a22,a23,a24,a25,a26,a27,a28,a29,a30));
            em.persist(c2);

            em.persist(c3);
        }catch (Exception e){
            System.out.println("Error: "+e.getMessage());
            if(e.getCause() != null){
                System.out.println("Causa: "+e.getMessage());
            }
            em.getTransaction().rollback();
            return;
        }
        em.getTransaction().commit();



    }
}