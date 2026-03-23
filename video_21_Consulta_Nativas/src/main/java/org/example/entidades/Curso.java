package org.example.entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.dto.EstadisticaDTO;

import java.util.List;

@Entity
@Table(name = "cursos")
@SqlResultSetMapping(
        name = "EstadisticasCursoMapping",
        classes = @ConstructorResult(
                targetClass = EstadisticaDTO.class,
                columns = {
                        @ColumnResult(name = "nombreCurso"),
                        @ColumnResult(name = "numeroAlumnos"),
                        @ColumnResult(name = "promedio"),
                        @ColumnResult(name = "notaMaxima"),
                        @ColumnResult(name = "notaMinima")

                }
        )
)
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "Curso.findByIdNative",
                query = "SELECT * FROM cursos WHERE id = :id",
                resultClass = Curso.class
        ),
        @NamedNativeQuery(
                name = "Curso.getEstadisticaById",
                query = "SELECT c.nombre AS nombreCurso ,COUNT(a.*) AS numeroAlumnos,  " +
                        "AVG(a.nota_alumno) AS promedio, MAX(a.nota_alumno) AS notaMaxima, " +
                        "MIN(a.nota_alumno) AS notaMinima " +
                        "FROM Cursos c INNER JOIN Alumnos a ON c.id = A.id_curso " +
                        "WHERE c.id = :id GROUP BY c.nombre ",
                resultSetMapping ="EstadisticasCursoMapping"
        )
})
@Getter @Setter @NoArgsConstructor
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "nombre", nullable = false,length = 50)
    private String nombre;

    @Column(name = "nivel", nullable = false)
    private int nivel;

    @Column(name = "profesor", length = 50, nullable = false)
    private String profesor;

    @OneToMany(mappedBy = "curso",
                fetch = FetchType.LAZY,
                cascade = CascadeType.ALL)
    private List<Alumno> alumnos;

    public Curso(String nombre,
                 int nivel,
                 String profesor) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.profesor = profesor;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "profesor='" + profesor + '\'' +
                ", nivel=" + nivel +
                ", nombre='" + nombre + '\'' +
                ", id=" + id +
                '}';
    }
}
