package es.safareyes.the_happening.modelo;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "lugar", schema="the_happening")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "eventos")
public class Lugar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 250)
    private String nombre;

    @Column(name = "aforo_max")
    private Integer aforomax;

   // @OneToMany(mappedBy = "lugar", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
   // private List<Evento> eventos;

}