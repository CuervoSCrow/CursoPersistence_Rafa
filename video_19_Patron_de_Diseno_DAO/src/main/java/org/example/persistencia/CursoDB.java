package org.example.persistencia;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.example.dto.EstadisticaDTO;
import org.example.dto.ResumenCursoDTO;

import java.util.List;

public class CursoDB {
    private final EntityManager em;

    public CursoDB(EntityManager em){
        this.em = em;
    }

    public void getEstadisticaCurso(int id) {
        String str =
                "SELECT c.nombre , COUNT(a), AVG(a.nota)," +
                        "MAX(a.nota), MIN(a.nota) " +
                        "FROM Curso c, Alumno a " +
                        "WHERE c = a.curso AND c.id=:id " +
                        "GROUP BY c.nombre";
        Query query = em.createQuery(str, Object[].class);
        query.setParameter("id", id);
        List<Object[]> estadisticas = query.getResultList();

        for (Object[] estadistica : estadisticas) {
            System.out.println("Curso: " + estadistica[0]);
            System.out.println("Número de alumnos: " + estadistica[1]);
            System.out.println("Promedio: " + estadistica[2]);
            System.out.println("Nota Maxima: " + estadistica[3]);
            System.out.println("Nota Minima: " + estadistica[4]);
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
                (double) estadistica[2],
                (int) estadistica[3],
                (int) estadistica[4]);
    }
    public EstadisticaDTO getEstadisticaCurso3(int id){
        String str = "SELECT new org.example.dto.EstadisticaDTO( " +
                "c.nombre, COUNT(a), AVG(a.nota), MAX(a.nota), MIN(a.nota)) " +
                "FROM Curso c, Alumno a " +
                "WHERE c=a.curso AND c.id=:id " +
                "GROUP BY c.nombre ";
        TypedQuery<EstadisticaDTO> query = em.createQuery(str,EstadisticaDTO.class);
        query.setParameter("id",id);
        return query.getSingleResult();
    }
    public EstadisticaDTO getEstadisticaCurso4_video_18(int id){
        String str="SELECT new org.example.dto.EstadisticaDTO( " +
                "c.nombre, COUNT(a), AVG(a.nota), MAX(a.nota), MIN(a.nota)) " +
                "FROM Curso c INNER JOIN c.alumnos a " +
                "WHERE c.id=:id " +
                "GROUP BY c.nombre";
        TypedQuery<EstadisticaDTO> query = em.createQuery(str, EstadisticaDTO.class);
        query.setParameter("id",id);
        return query.getSingleResult();
    }

    public List<ResumenCursoDTO> getMatriculaCurso(){
        String str = "SELECT new org.example.dto.ResumenCursoDTO( " +
                "c.id, c.nombre,c.nivel,a.nombre) " +
                "FROM Alumno a RIGHT JOIN a.curso c " +
                "ORDER BY c.nombre , c.nivel";
        TypedQuery<ResumenCursoDTO> query=em.createQuery(str, ResumenCursoDTO.class);
        return query.getResultList();
    }

    public List<ResumenCursoDTO> getMatriculaTotal(){
        String str = "SELECT new org.example.dto.ResumenCursoDTO( " +
                "c.id, c.nombre, c.nivel, a.nombre) " +
                "FROM Curso c, Alumno a "+
                "ORDER BY c.nombre, c.nivel";
        TypedQuery<ResumenCursoDTO> query = em.createQuery(str, ResumenCursoDTO.class);
        return  query.getResultList();
    }
}
