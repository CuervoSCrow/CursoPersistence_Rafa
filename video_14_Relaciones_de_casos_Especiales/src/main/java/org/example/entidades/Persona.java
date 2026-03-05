package org.example.entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.embeddable.FormaContacto;

import java.util.List;

@Entity
@Table(name="personas")
@Getter @Setter @NoArgsConstructor
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(length = 50,nullable = false)
    private String nombre;

//    Esta anotación se carga de forma lazy si quieres EAGER
//    tienes que agregar a ElementCollection(fetch = FetchType.EAGER)
    @ElementCollection()
    @CollectionTable(
            name = "formas_contacto",
            joinColumns=@JoinColumn(name = "persona_id")
    )
    private List<FormaContacto> formasContacto;


    public Persona(String nombre,
                   List<FormaContacto> formasContacto) {
        this.nombre = nombre;
        this.formasContacto = formasContacto;
    }
}
