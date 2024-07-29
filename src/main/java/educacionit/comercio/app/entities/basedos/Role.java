package educacionit.comercio.app.entities.basedos;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="authorities", uniqueConstraints= {@UniqueConstraint(columnNames= {"user_id","authority"})}, schema = "educacionit")
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String authority;

}
