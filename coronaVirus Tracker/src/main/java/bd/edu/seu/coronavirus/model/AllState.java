package bd.edu.seu.coronavirus.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllState {

    private String State;
    private String country;
    private int totalCurrentEffected;
    private int differentFromPreviousDay;
}
