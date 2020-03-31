package db.edu.seu.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@AllArgsConstructor
@Entity
public class product {
    public product() {
    }

    @Id
    private long id;
    private String name;
    private double unitprice;
    private double unitStock;
    private boolean Discontinue;
    @OneToMany
    private List<Supplier> supplierList;


}
