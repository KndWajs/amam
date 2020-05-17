package pl.fit_amam.api.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class AbstractBaseEntity {

    @Column(name = "userName")
    private String userName;

    @Column(name = "creationDate")
    private Timestamp creationDate;

    @Column(name = "updateDate")
    private Timestamp updateDate;
}
