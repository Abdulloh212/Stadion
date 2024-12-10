package uz.pdp.stadium.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.stadium.Entity.abs.BaseEntity;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Stadium extends BaseEntity {
    private String name;
    private Date startTime;
    private Date endTime;
    private String description;
    private Integer hourlyPrice;
    @ManyToOne
    private Region region;
    @ManyToOne
    private Attachement cover;
}
