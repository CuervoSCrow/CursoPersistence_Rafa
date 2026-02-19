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
    private static void estadoEntidad(Empleado empleado,EntityManager em,String lugar){
        if(em.contains(empleado)){
            System.out.println("El empleado esta en el contexto de persistencia desde "+lugar);
        }else{
            System.out.println("El empleado no esta en el contexto de persistencia desde "+lugar);
        }
    }

    public static void main(String[] args) {
        Map<String,String> properties = new HashMap<>();
        Integer numEmpleado = 252;
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
/*
            Empleado empleado = new Empleado("Pedro Perez",
                                            "87654321",
                                            "87654321",
                                            LocalDate.of(1990, Month.MARCH,17),
                                            0,
                                            (short) 2,
                                            new BigDecimal("1456.8")
            );
            em.persist(empleado);
//            em.getTransaction().commit();
*/

//            ============================ Estado Managed ==================================

//            ========================= Ejemplo 1 junto con el new ============
/*
            estadoEntidad(empleado,em,"Managed");
            em.persist(empleado);
            estadoEntidad(empleado,em,"Managed");
*/

//            ================= Ejemplo 2 =====================
/*
            Empleado empleado = em.find(Empleado.class,252);

            estadoEntidad(empleado,em,"Manged");

*/

//            ============================== Estado Detached ==================================

//            ======= Ejemplo 1 ========
/*
            Empleado  empleado = em.find(Empleado.class,numEmpleado);
            em.detach(empleado);
            estadoEntidad(empleado,em,"Detached");
//*/

//          ========== Ejemplo 2================
/*
            Empleado empleado = em.find(Empleado.class,numEmpleado);
            em.getTransaction().commit();
            em.close();
            em = emf.createEntityManager();

            estadoEntidad(empleado,em,"Detached");
*/
//  ====================== Merge =============================================

//            em.getTransaction().begin();
            Empleado empleado = em.find(Empleado.class,numEmpleado);
            empleado.setNombre("Angel Peña");
            em.merge(empleado);
            estadoEntidad(empleado,em,"Merge");
            em.remove(empleado); // Aquí da el error por que esta fuera del estado de persistencia
//            siempre y cuando este habilitada la liea 67,69


            em.getTransaction().commit();
        }
    }
}