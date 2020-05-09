package pl.kruczek.httpjavabasic.lang;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Getter
@ToString
@AllArgsConstructor
@Table(name = "lang")
class Lang {

    @Id
    private UUID id;
    private String msg;
    private String code;

    //for Hibernate
    public Lang() {
    }
}
