package org.example.dto;

public class ResumenAlumnoDTO {
    private Integer idAlumno;
    private String nombreAlumno;
    private String nombreCurso;

    public ResumenAlumnoDTO(Integer idAlumno,
                            String nombreAlumno,
                            String nombreCurso) {
        this.idAlumno = idAlumno;
        this.nombreAlumno = nombreAlumno;
        this.nombreCurso = nombreCurso;
    }

    @Override
    public String toString() {
        return "ResumenAlumnoDTO{" +
                "idAlumno=" + idAlumno +
                ", nombreAlumno='" + nombreAlumno + '\'' +
                ", nombreCurso='" + nombreCurso + '\'' +
                '}';
    }
}
