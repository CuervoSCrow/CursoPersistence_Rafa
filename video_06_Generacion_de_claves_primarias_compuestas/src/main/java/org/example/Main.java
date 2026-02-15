package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.model.Direccion;
import org.example.model.Empleado;
import org.example.model.LineaFactura1;
import org.example.model.LineaFactura2;
import org.example.model.pkey.LineaFacturaPK;

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
        try(EntityManagerFactory emf =
                    Persistence.createEntityManagerFactory(
                            "lab-persistence-unit"
                    )){
            Map<String,Object> propiedadesPool = emf.getProperties();
            for(Map.Entry<String,Object> entry : propiedadesPool.entrySet()){
                System.out.println(String.format("%s : %s",
                        entry.getKey(), entry.getValue()));
            }
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();

            Empleado empleado1 = new Empleado(
                    "Pedro Perez",
                    "12345678",
                    "12345678",
                    LocalDate.of(1990, Month.MARCH,17),
                    0,
                    2,
                    new BigDecimal("1456.8")
            );
            em.persist(empleado1);

            Empleado empleado2 = new Empleado(
                    "Juan Luis Vidal",
                    "87654321",
                    "87654321",
                    LocalDate.of(1974,Month.JULY,5),
                    0,
                    2,
                    new BigDecimal("1546.9")
            );
            em.persist(empleado2);

            Direccion direccion1 = new Direccion("Calle 10 #12",
                                                "Madrid",
                                                "España");
            em.persist(direccion1);

            LineaFacturaPK lfPK = new LineaFacturaPK("F00452534",45);
            LineaFactura1 lf1 = new LineaFactura1(
                    lfPK,
                    5,
                    new BigDecimal(13.45),
                    21,
                    0);

            em.persist(lf1);

            LineaFactura2 lf2 = new LineaFactura2(
                    "F00452534",
                    45,
                    4,
                    new BigDecimal(16.14),
                    21,
                    0);
            em.persist(lf2);

            em.getTransaction().commit();
        }
    }
}