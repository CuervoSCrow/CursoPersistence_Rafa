package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.model.Empleado;

import javax.swing.text.html.parser.Entity;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Map<String,String> properties = new HashMap<>();
        try(EntityManagerFactory factory=
                    Persistence.createEntityManagerFactory(
                            "lab-persistence-unit"
                    )){
            Map<String,Object> propiedadesPool = factory.getProperties();
            for(Map.Entry<String,Object> entry: propiedadesPool.entrySet()){
                System.out.println(String.format("%s : %s",
                        entry.getKey(),entry.getValue()));
            }

            EntityManager manager = factory.createEntityManager();
            manager.getTransaction().begin();
            Empleado empleado = new Empleado(
                    "Pedro Perez",
                    "12345678",
                    "12345678",
                    LocalDate.of(1990, Month.MARCH,17),
                    0,
                    2,
                    new BigDecimal("1456.8")
            );

            manager.persist(empleado);

            manager.getTransaction().commit();
        }
    }
}