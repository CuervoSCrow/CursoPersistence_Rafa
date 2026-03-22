package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.dto.ResumenAlumnoDTO;
import org.example.entidades.Alumno;
import org.example.persistencia.AlumnoDB;

import java.util.List;
import java.util.Optional;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static EntityManager em;
    public static void main(String[] args) {
        try(EntityManagerFactory emf =
                    Persistence.createEntityManagerFactory(
                            "lab-persistence-unit")){
            em = emf.createEntityManager();
            em.getTransaction().begin();

            AlumnoDB alumnoDB = new AlumnoDB(em);
            List<Alumno> alumnos = alumnoDB.findAll_01_AC();
            for(Alumno a : alumnos){
                System.out.println("Alumno: "+a.toString());
            }

            System.out.println("Pagina 2 de Alumnos");
            alumnos = alumnoDB.findPage_AC(2,12);
            for(Alumno alumno : alumnos){
                System.out.println("Alumno: "+ alumno.toString());
            }

            System.out.println("****** findById_AC ******");
            Optional<Alumno> alumno = alumnoDB.findById_AC(2);
            if(alumno.isPresent()){
                System.out.println("Alumno: "+alumno.toString());
            }

            System.out.println(" ****** FindByNombreAC *******");
            alumnos = alumnoDB.findByNombre_AC("gomez");
            System.out.println(alumnos.size());
            for(Alumno a: alumnos){
                System.out.println("Alumno: "+a.toString());
            }

            System.out.println(" ****** Numero de alumnos: "+alumnoDB.count_AC());

            System.out.println(" ****** Promedio del curso 1: "+alumnoDB.getNoteAVG_02_AC(1));

            System.out.println(" ****** Alumnos aprobados del curso 2");
            alumnos = alumnoDB.getAlumnosAprobadosAC(2);
            for(Alumno a : alumnos){
                System.out.println("Alumno: "+a.toString());
            }

            System.out.println(" ****** Matricula de alumnos");
            List<ResumenAlumnoDTO> resumenAlumnos = alumnoDB.getMatriculaAlumnos_AC();
            for(ResumenAlumnoDTO r : resumenAlumnos){
                System.out.println("Alumno: "+r.toString());
            }

            em.getTransaction().commit();

            System.out.println("***** Promoción del curso 2 al 3");
            if(alumnoDB.promocionAlumnos_AC(2,3)){
                System.out.println("La promocion de curso se ha realizado correctamente");
            }else{
                System.out.println("Ha ocurrido un error realizando la promocion del curso");
            }


        }
    }
}