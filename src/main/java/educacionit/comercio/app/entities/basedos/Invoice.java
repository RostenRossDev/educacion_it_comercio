package educacionit.comercio.app.entities.basedos;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
//@Data
@Entity
@Table(name="INVOICE", schema = "educacionit")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Se agrego esto
    @OneToMany(mappedBy = "invoice", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InvoiceDetail> details;

    public Double total(){
        Double tot = 0.0;
        for (InvoiceDetail item : details){
            tot += item.detailTotal();
        };
        return tot;
    }
}
