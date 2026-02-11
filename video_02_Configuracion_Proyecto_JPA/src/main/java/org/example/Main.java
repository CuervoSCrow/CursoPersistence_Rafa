package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.config.PersistenceUnitInfoImpl;
import org.example.model.Empleado;
import org.hibernate.jpa.HibernatePersistenceConfiguration;
import org.hibernate.jpa.HibernatePersistenceProvider;

import javax.sound.midi.Soundbank;
import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {

        Map<String, String> properties = new HashMap<>();

//        Con esta configuración utilizamos el archivo PersistenceUnitInfoImpl

//        try(EntityManagerFactory  factory =
//                new HibernatePersistenceProvider()
//                .createContainerEntityManagerFactory(
//                        new PersistenceUnitInfoImpl(),
//                        properties)){
//            Map<String,Object> propiedadesPool= factory.getProperties();
//            for(Map.Entry<String,Object> entry: propiedadesPool.entrySet()){
//                System.out.println(String.format("%s = %s",entry.getKey(),entry.getValue()));
//            }
//            EntityManager manager = factory.createEntityManager();
//            manager.getTransaction().begin();
//            System.out.println("Medio de la transacción");
//            manager.getTransaction().commit();
//            System.out.println("Fin de la Transacción");
//        }

//        Con esta configuración utilizamos persistence.xml
          try(EntityManagerFactory factory =
                      Persistence.createEntityManagerFactory
                              ("lab-persistence-unit")){

              Map<String, Object> propiedadesPool=factory.getProperties();
              for(Map.Entry<String,Object> entry: propiedadesPool.entrySet()){
                  System.out.println(String.format("%s : %s",
                          entry.getKey(),entry.getValue()));
              }

              EntityManager manager = factory.createEntityManager();
              manager.getTransaction().begin();

//              Persist
//              Empleado empleado = new Empleado(
//                      1,
//                      "Juan Cardillo",
//                      LocalDate.of(1998, Month.JUNE,17),
//                      1458.3);
//              manager.persist(empleado);
//              System.out.println("Empleado 1: "+empleado.toString());

//              Find
              Empleado empleado2 = manager.find(Empleado.class,1);
              Empleado empleado3 = manager.find(Empleado.class,1);
              System.out.println("Empleado 2: "+empleado2.toString());
              System.out.println("Empleado 3: "+empleado3.toString());
              if(empleado2 == empleado3){
                  System.out.println("empleado2 y empelado 3 es el mismo objeto");
              }


              manager.getTransaction().commit();
              System.out.println("Fin de la transacción");

          }
    }
}