package uz.pdp.stadium.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.stadium.Entity.abs.BaseEntity;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Hours extends BaseEntity {
    private Date date;
    @ManyToOne
    private Days day;
    private boolean isOccupied=false;
}
