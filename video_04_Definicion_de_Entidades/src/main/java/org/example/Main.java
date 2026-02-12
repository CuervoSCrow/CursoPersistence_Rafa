package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.model.Empleado;

import java.math.BigDecimal;
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
                    )) {
            Map<String, Object> propiedadesPool = factory.getProperties();
            for (Map.Entry<String, Object> entry : propiedadesPool.entrySet()) {
                System.out.println(String.format("%s : %s",
                        entry.getKey(), entry.getValue()));
            }

            EntityManager manager = factory.createEntityManager();
            manager.getTransaction().begin();

            Empleado empleado = new Empleado(
                    1,
                    "Ulises Vidal",
                    "12345678",
                    "12345678",
                    LocalDate.of(1983, Month.NOVEMBER,18),
                    0,
                    1,
                    new BigDecimal(1250.45)
            );
            manager.persist(empleado);

            manager.getTransaction().commit();

            manager.getTransaction().begin();

            manager.detach(empleado);
            empleado = manager.find(Empleado.class,1);
//            System.out.println("Edad: "+empleado.calcularEdad());

            System.out.println("Edad2: "+empleado.getEdad2());
            manager.getTransaction().commit();
        }
    }
}