package uz.pdp.stadium.Entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.stadium.Entity.abs.BaseEntity;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Attachement extends BaseEntity {
    private String name;
}
