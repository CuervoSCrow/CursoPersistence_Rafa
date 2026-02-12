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
//            Empleado empleado= new Empleado(1,
//                    "Juan Cardillo",
//                    LocalDate.of(1998, Month.JUNE,17),
//                    1458.3);
//
//            manager.persist(empleado);
//            System.out.println("Empleado 1: "+empleado.toString());


//            Operacion find
//            Empleado empleado2 = manager.find(Empleado.class,1);
//            Empleado empleado3 = manager.find(Empleado.class,1);
//            System.out.println("Empleado 2: "+empleado2.toString());
//            System.out.println("Empleado 3: "+empleado3.toString());
//
//            if(empleado2 == empleado3){
//                System.out.println("Empleado 2 y Empleado 3 es el mismo objeto");
//            }

//            Refresh
//            Empleado empleado2 = manager.find(Empleado.class,1);
//            System.out.println("Empleado 2: "+empleado2.toString());
//            empleado2.setNombre("Luis Garcia");
//            System.out.println("Empleado 2: "+empleado2.toString());
//            manager.refresh(empleado2);
//            System.out.println("Empleado 2: "+empleado2.toString());
//

//      ======================================================================
                //            getReference
//            Es un busqueda que no se realiza solo que sea necesario
//      ======================================================================
//            Empleado empleado4 = manager.getReference(Empleado.class,1);
//            System.out.println("Empleado 4: "+empleado4.toString());

//      ======================================================================
//                          Modificación
//      ======================================================================
//            Empleado empleado4 = manager.find(Empleado.class,1);
//            System.out.println("Empleado 4: "+empleado4.toString());
//
//            empleado4.setNombre("Luis Garcia");
//            System.out.println("Empleado 4: "+empleado4.toString());

//      ======================================================================
//                          Remove
//      ======================================================================
//            Empleado empleado4 = manager.find(Empleado.class,1);
//            manager.remove(empleado4);
//            System.out.println("Empleado 4: "+empleado4.toString());

//      ======================================================================
//                          Detach
//            Sacar a un objeto del contexto de persistencia
//      ======================================================================
//        Asegurate que tienes un dato en la base de datos
//            Empleado empleado5 = manager.find(Empleado.class,1);
//            manager.detach(empleado5);
//            COn el detach sacamos al empleado 5 cel modelo de persistencia es por eso que me genera
//            un error al tratar de eliminarlo.
//            manager.remove(empleado5);

//      ======================================================================
//                          Merge
//          Nuestro empleado muestra que tiene el mismo id  de uno que esta en la BD,
//            lo que va a pasar es que va a sobrescribir el campo que se encuentra en la BD,
//            y si no esta en la BD solo lo crea como algo nuevo.
//      ======================================================================
//            Empleado empleado6 = new Empleado(
//                    1,
//                    "Alexis Peña",
//                    LocalDate.of(1995,Month.JANUARY,7),
//                    1198.9);
//            manager.merge(empleado6);
//            Empleado empleado7 = new Empleado(
//                    2,
//                    "Oscar Alvarez",
//                    LocalDate.of(1992,Month.FEBRUARY,22),
//                    1798.9);
//            manager.merge(empleado7);

//      ======================================================================
//                          Flush
//          Sincronizar el contexto de persistencia con la BD aunque no finaliza la transacción
//      ======================================================================
            Empleado empleado8 = new Empleado(
                    3,
                    "alberto Gonzales",
                    LocalDate.of(1990,Month.MAY,25),
                    1233.7
            );
            manager.persist(empleado8);
            manager.flush();
//            En la linea se marca una parada para debuguearla y se nota que ya hizo la inserccion
//            solo que en la BD no se han insertado el flush hace la orden antes del commit,
//            solo que no lo inserta hasta cuando llega al commit.
            System.out.println("Empleado 8: "+empleado8.toString());

            manager.getTransaction().commit();
            System.out.println("Fin de la transacción");
        }

    }
}