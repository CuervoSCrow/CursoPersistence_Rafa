package org.example.persistencia;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.example.entidades.Alumno;

import java.util.List;
import java.util.Optional;

public class AlumnoDB {
    private EntityManager em;

    public AlumnoDB(EntityManager em){
        this.em = em;
    }

    public List<Alumno> findAll_01(){
        System.out.println("****** findAll_01 ******");
        String str = "SELECT a FROM Alumno a "+
                "ORDER BY a.nombre";
        TypedQuery<Alumno> query = em.createQuery(str, Alumno.class);
        return query.getResultList();
    }

    public List<Alumno> findAll_02(){
        TypedQuery<Alumno> query =em.createNamedQuery("Alumno.findAll", Alumno.class);
        return query.getResultList();
    }

    public List<Alumno> findPage(int pageNumber,int pageSize){
        TypedQuery<Alumno> query = em.createNamedQuery("Alumno.findAll", Alumno.class);
        query.setFirstResult((pageNumber-1)*pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    public Optional<Alumno> findById(Integer id){
        String str = "SELECT a FROM Alumno a WHERE a.id=:id";
        TypedQuery<Alumno> query = em.createQuery(str, Alumno.class);
        query.setParameter("id",id);
        try{
            return Optional.of(query.getSingleResult());
        }catch(NoResultException e){
            System.out.println("No hay alumno con ese identificador");
            return Optional.empty();
        }catch (Exception e){
            System.out.println("Error: "+e.getMessage());
            if(e.getCause()!=null){
                System.out.println("Causa: "+e.getMessage());
            }
            return Optional.empty();
        }
    }

    public Optional<Alumno> findById2(Integer id){
        String str = "SELECT a FROM Alumno a WHERE a.id =?1";
        TypedQuery<Alumno> query = em.createQuery(str,Alumno.class);
        query.setParameter(1,id);
        try{
            return Optional.of(query.getSingleResult());
        }catch(NoResultException e){
            System.out.println("No hay alumno con ese identificador");
            return Optional.empty();
        }catch (Exception e){
            System.out.println("Error: "+e.getMessage());
            if(e.getCause()!=null){
                System.out.println("Causa: "+e.getMessage());
            }
            return Optional.empty();
        }
    }
    public List<Alumno> findByNombre(String nombre){
        String str =
            "SELECT a FROM Alumno a " +
            "WHERE UPPER(a.nombre) LIKE '%'||UPPER(:nombre)||'%'";
        TypedQuery<Alumno> query = em.createQuery(str,Alumno.class);
        query.setParameter("nombre",nombre);
        return query.getResultList();
    }

    public Long count(){
        String str=
            "SELECT COUNT(a) FROM Alumno a";
        TypedQuery<Long> query = em.createQuery(str,Long.class);
        return query.getSingleResult();
    }

    public double getNoteAvg(int idCurso){
        String str =
            "SELECT AVG(a.nota) FROM Alumno a " +
            "WHERE a.curso.id = :id";
        TypedQuery<Double> query = em.createQuery(str, Double.class);
        query.setParameter("id",idCurso);
        return query.getSingleResult();
    }

    public double getNoteAvg2(int idCurso){
        String str=
            "SELECT a FROM Alumno a " +
            "WHERE a.curso.id = :id";
        TypedQuery<Alumno> query = em.createQuery(str,Alumno.class);
        query.setParameter("id",idCurso);

        return query.getResultStream()
                .mapToInt(Alumno::getNota)
                .average().getAsDouble();
    }
    public boolean promocionalAlumnos(int idOrigen,int idDestino){
        String str1 ="UPDATE Alumno a SET a.curso.id = :idDestino, a.nota = 0 " +
                "WHERE a.curso.id = :idOrigen AND a.nota >= 5";
        String str2="DELETE FROM Alumno a WHERE a.curso.id =:idOrigen " +
                " AND a.nota < 5";

        try{
            em.getTransaction().begin();
            Query query1 = em.createQuery(str1);
            query1.setParameter("idOrigen",idOrigen);
            query1.setParameter("idDestino",idDestino);
            int result1= query1.executeUpdate();

            Query query2 = em.createQuery(str2);
            query2.setParameter("idOrigen",idOrigen);
            int result2 = query2.executeUpdate();

            em.getTransaction().commit();
            System.out.println(String.format(
                    "%d alumnos han pasado al siguiente curso \n " +
                            "%d alumnos han reprobado.",result1,result2));
            return true;
        }catch(Exception e){
            em.getTransaction().rollback();
            System.out.println("Error: "+e.getMessage());
            if(e.getCause()!=null){
                System.out.println("Causa: "+e.getMessage());
            }
            return false;
        }
    }


}
