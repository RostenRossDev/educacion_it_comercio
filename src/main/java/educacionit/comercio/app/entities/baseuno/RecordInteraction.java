package educacionit.comercio.app.entities.baseuno;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
//@Data
@Entity
@Table(name="LOG_REGISTRI", schema = "educacionit")
public class RecordInteraction implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String parameters;

    private String controller;

    private String method;

    private LocalDateTime date;
}
