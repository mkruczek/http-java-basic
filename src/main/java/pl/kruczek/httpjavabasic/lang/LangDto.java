package pl.kruczek.httpjavabasic.lang;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;


@Getter
@Builder
@ToString
@AllArgsConstructor
public class LangDto {

    private UUID id;
    private String msg;
    private String code;

    static LangDto fromLang(Lang lang) {
        return LangDto.builder()
                .id(lang.getId())
                .msg(lang.getMsg())
                .code(lang.getCode())
                .build();
    }
}
