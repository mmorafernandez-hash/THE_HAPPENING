package es.safareyes.the_happening.modelo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "categoria", schema="the_happening")
@Getter @Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "icono", length = 250)
    private String icono;

    @Column(name = "color", length = 20)
    private String color;

}
