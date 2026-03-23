package org.example.persistencia;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.example.dto.EstadisticaDTO;
import org.example.dto.ResumenCursoDTO;
import org.example.entidades.Curso;

import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class CursoDB {
    private final EntityManager em;

    public CursoDB(EntityManager em) {
        this.em = em;
    }

    public void getEstadisticaCurso(int id){
        String str=
                "SELECT c.nombre, COUNT(a), AVG(a.nota), " +
                        "MAX(nota), MIN(nota) " +
                        "FROM Curso c, Alumno a " +
                        "WHERE c =a.curso AND c.id=:id " +
                        "GROUP BY C.nombre";
        Query query = em.createQuery(str,Object[].class);
        query.setParameter("id",id);
        List<Object[]> estadisticas = query.getResultList();

        for(Object[] estadistica : estadisticas ){
            System.out.println("Curso: "+estadistica[0]);
            System.out.println("Numero de Alumnos: "+estadistica[1]);
            System.out.println("Promedio: "+estadistica[2]);
            System.out.println("Nota Maxima: "+estadistica[3]);
            System.out.println("Nota Minima: "+estadistica[4]);
        }
    }

    public EstadisticaDTO getEstadisticaCurso2(int id) {
        String str =
                "SELECT c.nombre , COUNT(a), AVG(a.nota)," +
                        "MAX(a.nota), MIN(a.nota) " +
                        "FROM Curso c, Alumno a " +
                        "WHERE c = a.curso AND c.id=:id " +
                        "GROUP BY c.nombre";
        Query query = em.createQuery(str, Object[].class);
        query.setParameter("id", id);
        List<Object[]> estadisticas = query.getResultList();

        Object[] estadistica = estadisticas.get(0);

        return new EstadisticaDTO(
                estadistica[0].toString(),
                (long) estadistica[1],
                (BigDecimal) estadistica[2],
                (int) estadistica[3],
                (int) estadistica[4]);
    }

    public EstadisticaDTO getEstadisticaCurso3(int id){
        String str = "SELECT new org.example.dto.EstadisticaDTO( "+
                "c.nombre, COUNT(a), AVG(a.nota), MAX(a.nota), MIN(a.nota)) "+
                "FROM Curso c, Alumno a "+
                "WHERE c=a.curso AND c.id=:id "+
                "GROUP BY c.nombre";
        TypedQuery<EstadisticaDTO> query = em.createQuery(str,EstadisticaDTO.class);
        query.setParameter("id",id);
        return query.getSingleResult();
    }

    public EstadisticaDTO getEstadisticaCurso4_video_18(int id){
        String str="SELECT new org.example.dto.EstadisticaDTO( "+
                "c.nombre, COUNT(a), AVG(a.nota), MAX(a.nota), MIN(a.nota)) "+
                "FROM Curso c INNER JOIN c.alumnos a "+
                "GROUP BY c.nombre";
        TypedQuery<EstadisticaDTO> query = em.createQuery(str,EstadisticaDTO.class);
        query.setParameter("id",id);
        return query.getSingleResult();
    }

    public List<ResumenCursoDTO> getMatriculaCurso(){
        String str="SELECT new org.example.dto.ResumenCursoDTO( "+
                "c.id, c.nombre, c.nivel, a.nombre) "+
                "FROM Alumno a RIGHT JOIN a.curso c "+
                "OREDER BY c.nombre, c.nivel";
        TypedQuery<ResumenCursoDTO> query = em.createQuery(str,ResumenCursoDTO.class);
        return query.getResultList();
    }

    public List<ResumenCursoDTO> getMatriculaTotal(){
        String str="SELECT new org.example.dto.ResumenCursoDTO( "+
                "c.id, c.nombre, c.nivel, a.nombre) "+
                "FROM Curso c, Alumno a "+
                "ORDER BY c.nombre,c.nivel";
        TypedQuery<ResumenCursoDTO> query = em.createQuery(str,ResumenCursoDTO.class);
        return query.getResultList();
    }

    public Optional<Curso> findById_Nativa(int id){
        String sql="SELECT * FROM cursos WHERE id=?";
        Query query = em.createNativeQuery(sql, Curso.class);
        query.setParameter(1,id);
        try{
            return Optional.of((Curso)query.getSingleResult());
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

    public Optional<Curso> findById_Nativa_HQL(int id){
        String sql="SELECT * FROM cursos WHERE id=:id";
        Query query = em.createNativeQuery(sql, Curso.class);
        query.setParameter("id",id);
        try{
            return Optional.of((Curso)query.getSingleResult());
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

    public Optional<Curso> findById_NamedQuery_Native(int id){

        TypedQuery<Curso> query = em.createNamedQuery("Curso.findByIdNative", Curso.class);
        query.setParameter("id",id);
        try{
            return Optional.of(query.getSingleResult());
        }catch (NoResultException e){
            System.out.println("No hay alumno con ese identificador...");
            return Optional.empty();
        }catch (Exception e){
            System.out.println("Error "+e.getMessage());
            if(e.getCause() != null){
                System.out.println("Causa: "+e.getMessage());
            }
            return Optional.empty();
        }
    }

    public EstadisticaDTO getEstadistica_Native(int id){
        Query query = em.createNamedQuery("Curso.getEstadisticaById",EstadisticaDTO.class);
        query.setParameter("id",id);
        return (EstadisticaDTO) query.getSingleResult();
    }

    public int updateCursoName_Native(int id,String nombre){
        String sql = "UPDATE cursos SET nombre= :nombre "+
                "WHERE id = :id";
        Query query = em.createNativeQuery(sql);
        query.setParameter("nombre",nombre);
        query.setParameter("id",id);
        try{
            em.getTransaction().begin();
            int nrows = query.executeUpdate();
            em.getTransaction().commit();

            return nrows;

        }catch(Exception e){
            em.getTransaction().rollback();
            System.out.println("Error: "+e.getMessage());
            return -1;
        }
    }


}
