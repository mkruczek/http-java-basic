package pl.kruczek.httpjavabasic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "lang")
@Getter
@AllArgsConstructor
@ToString
class Lang {

    @Id
    private UUID id;
    private String msg;
    private String code;

    public String getMsg() {
        return msg;
    }

    public String getCode() {
        return code;
    }

    //for Hibernate
    public Lang() {
    }
}
