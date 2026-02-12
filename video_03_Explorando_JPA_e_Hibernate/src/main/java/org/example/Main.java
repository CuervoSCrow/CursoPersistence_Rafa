package org.example;

import javax.swing.text.html.parser.Entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.model.Empleado;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Map<String, String> properties = new HashMap<>();
        try(EntityManagerFactory factory =
                Persistence.createEntityManagerFactory(
                        "lab-persistence-unit"
                )){
            Map<String,Object> propiedadesPool=factory.getProperties();
            for(Map.Entry<String,Object> entry : propiedadesPool.entrySet()){
                System.out.println(String.format("%s : %s",
                        entry.getKey(),entry.getValue()));
            }

            EntityManager manager = factory.createEntityManager();
            manager.getTransaction().begin();

//             Persist
            Empleado empleado= new Empleado(1,
                    "Juan Cardillo",
                    LocalDate.of(1998, Month.JUNE,17),
                    1458.3);
//            persist
            manager.persist(empleado);
            System.out.println("Empleado 1: "+empleado.toString());
            manager.getTransaction().commit();
            System.out.println("Fin de la transacción");

        }

    }
}