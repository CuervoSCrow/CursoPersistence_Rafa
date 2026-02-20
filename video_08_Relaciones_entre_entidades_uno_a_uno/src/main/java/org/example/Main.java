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
//          1.-  Unidireccional (siendo Socio la Entidad Padre)

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

//          2.-  Unidireccional (siendo Socio la Entidad Padre)
/*
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

 */

//          3.-   Bidireccional _ A nivel base de datos no hay bidireccion
//
/*

//            Caso 1 , Cuando trato de ver el socio con el To String me da null
            Socio socio = new Socio(
                    "Pedro Perez",
                    "12345678",
                    LocalDate.of(2000,Month.APRIL,15)
            );
            TargetaSocio targetaSocio = new TargetaSocio("ABCD-111-222-333",socio);

            em.persist(socio);
            em.persist(targetaSocio);


            em.getTransaction().commit();
            System.out.println("Socio: "+socio.toString());

 */
//            Caso 2 .- Asignandole targetaSocio para que la muestra con el toString
/*
            Socio socio = new Socio(
                    "Pedro Perez",
                    "12345678",
                    LocalDate.of(2000,Month.APRIL,15)
            );
            TargetaSocio targetaSocio = new TargetaSocio("ABCD-111-222-333",socio);
            socio.setTargetaSocio(targetaSocio);

            em.persist(socio);
            em.persist(targetaSocio);


            em.getTransaction().commit();
            System.out.println("Socio: "+socio.toString());
 */
//
//            Caso 3.-  Aquí sin la necesidad de agregarlo con el set como anteriormente
//            cuando se hace la busqueda con el find y asigna la relación desde la BD.
//            aunque la busqueda que realiza es mucho más grande háce un join para buscar
//            las dos tablas, se cree que la bidirección da mayor costo en recursos.

/*
            Socio socio = new Socio(
                    "Pedro Perez",
                    "12345678",
                    LocalDate.of(2000,Month.APRIL,15)
            );
            TargetaSocio targetaSocio = new TargetaSocio("ABCD-111-222-333",socio);


            em.persist(socio);
            em.persist(targetaSocio);


            em.getTransaction().commit();
            System.out.println("Socio: "+socio.toString());
            em.getTransaction().begin();
            em.clear();
            socio =null;
            Socio socio2 = em.find(Socio.class,1);
            System.out.println("Socio 2: "+socio2.toString());
            em.getTransaction().commit();

 */

//            4.- Mismo Id para ambas Entidades
///*
            Socio socio = new Socio(
                    "Pedro Perez",
                    "12345678",
                    LocalDate.of(2000,Month.APRIL,15)
            );
            TargetaSocio targetaSocio = new TargetaSocio("ABCD-111-222-333",socio);


            em.persist(socio);
            em.persist(targetaSocio);
            em.getTransaction().commit();
// */

//

        }
    }
}