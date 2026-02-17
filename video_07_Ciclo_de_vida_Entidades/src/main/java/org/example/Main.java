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
import java.util.Objects;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static void estadoEntidad(Empleado empleado,EntityManager em){
        if(em.contains(empleado)){
            System.out.println("El empleado esta en el contexto de persistencia");
        }else{
            System.out.println("El empleado no esta en el contexto de persistencia");
        }
    }

    public static void main(String[] args) {
        Map<String,String> properties = new HashMap<>();
        try(EntityManagerFactory emf =
                    Persistence.createEntityManagerFactory(
                            "lab-persistence-unit"
                    )){
            Map<String,Object> propiedadesPool = emf.getProperties();
            for(Map.Entry<String,Object> entry : propiedadesPool.entrySet()){
                System.out.println(String.format("%s : %s",
                        entry.getKey(),entry.getValue()));
            }
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
//
//            ============================ Estado New ==================================
            
            Empleado empleado = new Empleado("Pedro Perez",
                                            "12345678",
                                            "12345678",
                                            LocalDate.of(1990, Month.MARCH,17),
                                            0,
                                            (short) 2,
                                            new BigDecimal("1456.8")
            );
            estadoEntidad(empleado,em);
            em.persist(empleado);
            estadoEntidad(empleado,em);

            em.getTransaction().commit();
        }
    }
}