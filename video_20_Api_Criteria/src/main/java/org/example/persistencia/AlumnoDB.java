package org.example.persistencia;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.example.dto.ResumenAlumnoDTO;
import org.example.entidades.Alumno;
import org.example.entidades.Curso;

import javax.sound.midi.Soundbank;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

public class AlumnoDB {
    private EntityManager em;

    public AlumnoDB(EntityManager em) {
        this.em = em;
    }

    public List<Alumno> findAll_01() {
        System.out.println("****** findAll_01 ******");
        String str = "SELECT a FROM Alumno a " +
                "ORDER BY a.nombre";
        TypedQuery<Alumno> query = em.createQuery(str, Alumno.class);
        return query.getResultList();
    }


    public List<Alumno> findAll_01_AC(){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Alumno> query = cb.createQuery(Alumno.class);
        Root<Alumno> fuente = query.from(Alumno.class);

        query.select(fuente).orderBy(cb.asc(fuente.get("nombre")));

        return em.createQuery(query).getResultList();
    }

    public List<Alumno> findAll_02() {
        System.out.println("****** findAll_02 ******");
        TypedQuery<Alumno> query = em.createQuery("Alumno.findAll", Alumno.class);
        return query.getResultList();
    }

    public List<Alumno> findPage(int pageNumber, int pageSize) {
        TypedQuery<Alumno> query = em.createNamedQuery("Alumno.findAll", Alumno.class);
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    public List<Alumno> findPage_AC(int pageNumber, int pageSize){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Alumno> query = cb.createQuery(Alumno.class);
        Root<Alumno> fuente = query.from(Alumno.class);

        query.select(fuente)
                .orderBy(cb.asc(fuente.get("nombre")));

        return em.createQuery(query)
                .setFirstResult((pageNumber-1)*pageSize)
                .setMaxResults(pageSize)
                .getResultList();
    }

    public Optional<Alumno> findById(Integer id) {
        String str = "SELECT a FROM Alumno a WHERE id=:id ";
        TypedQuery<Alumno> query = em.createQuery(str, Alumno.class);
        query.setParameter("id", id);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            System.out.println("No hay alumno con esa identificacion");
            return Optional.empty();
        } catch (Exception e) {
            if (e.getCause() != null) {
                System.out.println("Causa: " + e.getMessage());
            }
            return Optional.empty();
        }
    }

    public Optional<Alumno> findById_AC(Integer id){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Alumno> query = cb.createQuery(Alumno.class);
        Root<Alumno> fuente = query.from(Alumno.class);

        query.select(fuente)
                .where(cb.equal(fuente.get("id"),id));

        try{
            return Optional.of(em.createQuery(query).getSingleResult());
        }catch(NoResultException e){
            System.out.println("No hay alumno con ese identificador");
            return Optional.empty();
        }catch(Exception e){
            System.out.println("Error: "+e.getMessage());
            if(e.getCause()!= null){
                System.out.println("Causa: "+e.getMessage());
            }
            return Optional.empty();
        }
    }

    public Optional<Alumno> findById_02(Integer id) {
        String str = "SELECT a FROM Alumno a WHERE id=?1 ";
        TypedQuery<Alumno> query = em.createQuery(str, Alumno.class);
        query.setParameter(1, id);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            System.out.println("No hay alumno con esa identificacion");
            return Optional.empty();
        } catch (Exception e) {
            if (e.getCause() != null) {
                System.out.println("Causa: " + e.getMessage());
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

    public List<Alumno> findByNombre_AC(String nombre){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Alumno> query = cb.createQuery(Alumno.class);
        Root<Alumno> fuente = query.from(Alumno.class);

        String patron = "%"+nombre.toUpperCase()+"%";
        query.select(fuente)
                        .where(cb.like(cb.upper(fuente.get("nombre")),patron))
                        .orderBy(cb.asc(fuente.get("id")));

        return em.createQuery(query).getResultList();
    }

    public Long count(){
        String str= "SELECT COUNT(a) FROM Alumno a";
        TypedQuery<Long> query = em.createQuery(str, Long.class);
        return query.getSingleResult();
    }

    public long count_AC(){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<Alumno> fuente = query.from(Alumno.class);

        query.select(cb.count(fuente));
        return em.createQuery(query)
                .getSingleResult();
    }

    public double getNoteAvg(int idCurso){
        String str =
                "SELECT AVG(a.nota) FROM Alumno a " +
                "WHERE a.curso.id= :id";
        TypedQuery<Double> query= em.createQuery(str, Double.class);
        query.setParameter("id",idCurso);
        return query.getSingleResult();
    }

    public double getNoteAvg_02(int idCurso){
        String str =
            "SELECT a FROM Alumno a " +
            "WHERE a.curso.id=:id";
        TypedQuery<Alumno> query = em.createQuery(str, Alumno.class);
        query.setParameter("id",idCurso);

        return query.getResultStream()
                .mapToInt(Alumno::getNota)
                .average()
                .getAsDouble();
    }

    public double getNoteAVG_02_AC(int idCurso){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Double> query = cb.createQuery(Double.class);
        Root<Alumno> fuente = query.from(Alumno.class);

        query.select(cb.avg(fuente.get("nota")))
                .where(cb.equal(fuente.get("curso").get("id"),idCurso));

        return em.createQuery(query)
                .getSingleResult();
    }

    public List<Alumno> getAlumnosAprobados(int id){
        String str = "SELECT a FROM Alumno a " +
            "INNER JOIN a.curso c " +
            "WHERE c.id = :id AND a.nota > 5 " +
            "ORDER BY a.nombre";
        TypedQuery<Alumno> query = em.createQuery(str,Alumno.class);
        query.setParameter("id",id);
        return query.getResultList();
    }

    public List<Alumno> getAlumnosAprobadosAC(int id){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Alumno> query = cb.createQuery(Alumno.class);
        Root<Alumno> fuente = query.from(Alumno.class);

        query.select(fuente)
                .where(
                        cb.equal(fuente.get("curso").get("id"),id),
                        cb.greaterThan(fuente.get("nota"),5)
                )
                .orderBy(cb.asc(fuente.get("nombre")));
        return em.createQuery(query)
                .getResultList();
    }

    public List<ResumenAlumnoDTO> getMatriculaAlumnos(){
        String str= "SELECT new org.example.dto.ResumenAlumnoDTO( " +
            "a.id, a.nombre, c.nombre) FROM Alumno a " +
            "LEFT JOIN a.curso c "+
            "ORDER BY a.nombre";
        TypedQuery<ResumenAlumnoDTO> query = em.createQuery(str,ResumenAlumnoDTO.class);
        return query.getResultList();
    }

    public List<ResumenAlumnoDTO> getMatriculaAlumnos_AC(){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ResumenAlumnoDTO> query = cb.createQuery(ResumenAlumnoDTO.class);
        Root<Alumno> fuente = query.from(Alumno.class);
        Join<Alumno, Curso> curso = fuente.join("curso", JoinType.LEFT);

        query.multiselect(
                fuente.get("id"),
                fuente.get("nombre"),
                curso.get("nombre"))
             .orderBy(cb.asc(fuente.get("nombre")));

        return em.createQuery(query)
                .getResultList();

    }

    public boolean promocionarAlumnos(int idOrigen, int idDestino){
        String str1 = "UPDATE Alumno a SET a.curso.id=:idDestino,a.nota = 0 " +
                "WHERE a.curso.id=:idOrigen AND a.nota<5";
        String str2 = "DELETE FROM Alumno a WHERE a.curso.id = :idOrigen " +
                "AND a.nota < 5";
        try{
            em.getTransaction().begin();

            Query query1 = em.createQuery(str1);
            query1.setParameter("idOrigen",idOrigen);
            query1.setParameter("idDestino",idDestino);
            int result1 = query1.executeUpdate();

            Query query2 = em.createQuery(str2);
            query2.setParameter("idOrigen",idOrigen);
            int result2 = query2.executeUpdate();

            em.getTransaction().commit();
            System.out.println(result1+" alumnos han pasado al siguiente curso y "+
                    result2+" han reprobado.");
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

    public boolean promocionarAlumnosyEliminacion_AC(int idOrigen,int idDestino){
        try{
            em.getTransaction().begin();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaUpdate<Alumno> update = cb.createCriteriaUpdate(Alumno.class);
            Root<Alumno> fuente1 = update.from(Alumno.class);
            update.set(fuente1.get("curso").get("id"),idDestino)
                  .set(fuente1.get("nota"),0)
                  .where(
                          cb.equal(fuente1.get("curso").get("id"),idOrigen),
                          cb.greaterThanOrEqualTo(fuente1.get("nota"),5 ));
            Query query1 = em.createQuery(update);
            int result1 =query1.executeUpdate();

            CriteriaDelete<Alumno> delete = cb.createCriteriaDelete(Alumno.class);
            Root<Alumno> fuente2 = delete.from(Alumno.class);
            delete.where(
                    cb.equal(fuente2.get("curso").get("id"),idOrigen),
                    cb.lessThan(fuente2.get("nota"),5)
            );

            Query query2 = em.createQuery(delete);
            int result2 = query2.executeUpdate();

            em.getTransaction().commit();
            System.out.println(result1+" alumnos han pasado al siguiente curso y "+
                    result2+" han reprobado.");
            return true;
        }catch (Exception e){
            em.getTransaction().rollback();
            System.out.println("Error: "+e.getMessage());
            if(e.getCause()!= null){
                System.out.println("Causa: "+e.getMessage());
            }
            return false;
        }
    }

    public boolean promocionAlumnos_AC(int idOrigen,int idDestino){
        try{
            em.getTransaction().begin();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaUpdate<Alumno> update = cb.createCriteriaUpdate(Alumno.class);
            Root<Alumno> fuente = update.from(Alumno.class);

            update.set(fuente.get("curso").get("id"),idDestino)
                    .set(fuente.get("nota"),0)
                    .where(
                            cb.equal(fuente.get("curso").get("id"),idOrigen),
                            cb.greaterThanOrEqualTo(fuente.get("nota"),5)
                    );
            Query query = em.createQuery(update);
            int result = query.executeUpdate();
            em.getTransaction().commit();
            System.out.println(result+" de los alumnos han pasado de curso");
            return true;
        }catch(Exception e){
            em.getTransaction().rollback();
            System.out.println("Error: "+e.getMessage());
            if(e.getCause()!= null){
                System.out.println("Causa: "+e.getMessage());
            }
            return false;
        }
    }
}
