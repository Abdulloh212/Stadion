package uz.pdp.stadium.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.stadium.Entity.abs.BaseEntity;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class AttachementContent extends BaseEntity {
    private byte[] content;
    @ManyToOne
    private Attachement attachement;
}
