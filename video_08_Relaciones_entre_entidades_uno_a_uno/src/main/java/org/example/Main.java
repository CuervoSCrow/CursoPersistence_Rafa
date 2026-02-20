package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.entidades.Socio;
import org.example.entidades.TargetaSocio;

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
//=====================================================================================
//                              ONE TO ONE
//=====================================================================================
/*
//            Unidireccional (siendo Socio la Entidad Padre)
            TargetaSocio targeta = new TargetaSocio(
                    "ABCD-111-222-333"
            );
            Socio socio = new Socio(
                    "PedroPerez",
                    "12345678",
                    LocalDate.of(2000, Month.APRIL,15),
                    targeta
            );
            em.persist(targeta);
            em.persist(socio);
            Socio socio2 = new Socio(
                    "Luis Garcia",
                    "12345679",
                    LocalDate.of(2001,Month.JUNE,21),
                    targeta //Aqui no puede ser la misma targeta o marca error
            );
*/

//            Unidireccional (siendo Socio la Entidad Padre)
///*
            Socio socio1=new Socio("Pedro Perez",
                    "12345678",
                    LocalDate.of(2000,Month.APRIL,15));
            TargetaSocio targeta1 = new TargetaSocio("ABCD-111-222-333",socio1);

            em.persist(socio1);
            em.persist(targeta1);

            Socio socio2 = new Socio("Luis Garcia",
                    "12345679",
                    LocalDate.of(2001,Month.JUNE,21));
            em.persist(socio2);

// */


            em.getTransaction().commit();
        }
    }
}