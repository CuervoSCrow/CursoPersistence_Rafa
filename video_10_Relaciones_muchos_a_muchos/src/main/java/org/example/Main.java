package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.entidades.Rol;
import org.example.entidades.Usuario;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Set;


public class Main {
    public static void main(String[] args) {
        try(EntityManagerFactory emf =
                    Persistence.createEntityManagerFactory(
                            "lab-persistence-unit")){
            EntityManager em = emf.createEntityManager();

//            Uso con List<>
/*
            em.getTransaction().begin();

                Rol rol1 = new Rol("Administrador");
                Rol rol2 = new Rol("Usuario");
                Rol rol3 = new Rol("Invitado");

                em.persist(rol1);
                em.persist(rol2);
                em.persist(rol3);

                Usuario usuario1 = new Usuario(
                        "Laboratorio",
                        "1234",
                        "Laboratorio de Rafa",
                        "lab@mail.com",
                        List.of(rol1,rol2,rol1));

                Usuario usuario2 = new Usuario(
                        "pperz",
                        "1234",
                        "Pedro Pérez",
                        "pedro@mail.com",
                        List.of(rol2));

                Usuario usuario3 = new Usuario(
                        "lgarcia",
                        "1234",
                        "Luis Garcia",
                        "luis@mail.com",
                        List.of(rol3));

                em.persist(usuario1);
                em.persist(usuario2);
                em.persist(usuario3);

            em.getTransaction().commit();

 */
///*
//        Uso Con Set
            em.getTransaction().begin();

            Rol rol1 = new Rol("Administrador");
            Rol rol2 = new Rol("Usuario");
            Rol rol3 = new Rol("Invitado");

            em.persist(rol1);
            em.persist(rol2);
            em.persist(rol3);

            Usuario usuario1 = new Usuario(
                    "Laboratorio",
                    "1234",
                    "Laboratorio de Rafa",
                    "lab@mail.com",
                    Set.of(rol1,rol2));

            Usuario usuario2 = new Usuario(
                    "pperz",
                    "1234",
                    "Pedro Pérez",
                    "pedro@mail.com",
                    Set.of(rol2));

            Usuario usuario3 = new Usuario(
                    "lgarcia",
                    "1234",
                    "Luis Garcia",
                    "luis@mail.com",
                    Set.of(rol3));

            em.persist(usuario1);
            em.persist(usuario2);
            em.persist(usuario3);

            em.getTransaction().commit();
// */

///*            Forma Bidireccional

            em.clear();
            em.getTransaction().begin();

            rol1=em.find(Rol.class,1);
            System.out.println("***** Usuarios del rol: "+rol1.toString());
            for(Object u : rol1.getUsuarios()){
                System.out.println("***** Usuario: "+u.toString());
            }
            rol2 = em.find(Rol.class,2);
            System.out.println("***** Usuarios del rol: "+rol2.toString());
            for(Object u : rol2.getUsuarios()){
                System.out.println("***** Usuario: "+u.toString());
            }
            rol3 = em.find(Rol.class,3);
            System.out.println("***** Usuarios del rol: "+rol3.toString());
            for(Object u : rol3.getUsuarios()){
                System.out.println("***** Usuario: "+u.toString());
            }
            em.getTransaction().commit();
// */

        }
    }
}