package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.embeddable.FormaContacto;
import org.example.embeddable.PreferenciasUsuarios;
import org.example.entidades.Persona;
import org.example.entidades.Usuario;

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
//
//            Usuario usuario = new Usuario(
//                    "Pedro Perez",
//                    "pedro@main.com",
//                    "1234",
//                    "Calle 123",
//                    "123456789",
//                    "amarillo",
//                    "abstracto");

            Usuario usuario = new Usuario(
                    "Pedro Perez",
                    "pedro@mail.com",
                    "1234",
                    "Av 16 de septiembre No 38",
                    "123456",
                    new PreferenciasUsuarios("amarillo", "abstracto")
            );
            em.persist(usuario);

            Persona persona = new Persona(
                    "Oscar Guiterrez",
                    List.of(
                            new FormaContacto("Telefono", "123456"),
                            new FormaContacto("Whatsapp", "123456"),
                            new FormaContacto("Telegram", "123456")
                    )
            );
            em.persist(persona);

            em.getTransaction().commit();
        }
    }
}