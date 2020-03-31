package db.edu.seu.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class priceHistory
{
    @Id
    @GeneratedValue
    private int hestory_id;

    @ManyToOne
    private product product;
    private LocalDateTime localDateTime;
    private double pricePerUnit;
}
