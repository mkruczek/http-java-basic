package pl.kruczek.httpjavabasic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Getter
@AllArgsConstructor
@ToString
class Lang {

    private UUID id;
    private String msg;
    private String code;

    public String getMsg() {
        return msg;
    }

    public String getCode() {
        return code;
    }
}
