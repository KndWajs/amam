package pl.fit_amam.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AbstractBaseDto {
    private Long id;

    private String userName;

    private Timestamp creationDate;

    private Timestamp updateDate;
}
