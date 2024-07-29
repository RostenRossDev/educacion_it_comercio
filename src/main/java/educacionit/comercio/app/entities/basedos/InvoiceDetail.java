package educacionit.comercio.app.entities.basedos;

import jakarta.persistence.*;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
//@Data
@Entity
@Table(name="invoice_details", schema = "educacionit")
public class InvoiceDetail implements Serializable {
    private static final long serialVersionUID=1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Se agrego esto
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="product_id")
    private Product product;

    private Integer quantity;

    public Double detailTotal(){
        return this.product.getPrice() * quantity;
    }
}
