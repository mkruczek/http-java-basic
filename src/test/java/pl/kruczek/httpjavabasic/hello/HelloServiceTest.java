package pl.kruczek.httpjavabasic.hello;

import org.junit.Test;
import pl.kruczek.httpjavabasic.lang.LangDto;
import pl.kruczek.httpjavabasic.lang.LangService;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static junit.framework.TestCase.assertEquals;

public class HelloServiceTest {

    @Test
    public void test_userNull_getGreeting_returnDefaultValue() {
        //given
        LangDto LangDto = new LangDto(UUID.randomUUID(), "Siemanko", "pl_PL");
        LangService mock = createMockLangDtoRepository(LangDto);
        HelloService sut = new HelloService(mock);
        String user = null;

        //when
        String greeting = sut.getGreeting(user, LangDto.getId().toString());

        //then
        assertEquals("Siemanko world!", greeting);
    }

    @Test
    public void test_userMkruczek_getGreeting_returnMkruczek() {
        //given
        LangDto langDto = new LangDto(UUID.randomUUID(), "Hello", "en_US");
        LangService mock = createMockLangDtoRepository(langDto);
        HelloService sut = new HelloService(mock);
        String user = "mkruczek";
        //when
        String greeting = sut.getGreeting(user, langDto.getId().toString());

        //then
        assertEquals("Hello " + user + "!", greeting);
    }

    @Test
    public void test_idNull_getGreeting_returnDefault() {
        //given
        HelloService sut = new HelloService();
        String user = "world";
        //when
        String greeting = sut.getGreeting(user, null);

        //then
        assertEquals("Hello " + user + "!", greeting);
    }

    @Test
    public void test_idEmpty_nameEmpty_getGreeting_returnDefault() {
        //given
        HelloService sut = new HelloService();
        //when
        String greeting = sut.getGreeting("", "");

        //then
        assertEquals("Hello world!", greeting);
    }

    @Test
    public void test_idNull_userNull_getGreeting_returnDefault() {
        //given
        HelloService sut = new HelloService();
        //when
        String greeting = sut.getGreeting(null, null);

        //then
        assertEquals("Hello world!", greeting);
    }

    @Test
    public void test_idString_userMkruczek_getGreeting_returnDefaultLangDto() {
        //given
        HelloService sut = new HelloService();
        String user = "mkruczek";

        //when
        String greeting = sut.getGreeting(user, "abc");

        //then
        assertEquals("Hello " + user + "!", greeting);
    }

    private LangService createMockLangDtoRepository(LangDto langDto) {
        return new LangService() {
            @Override
            public List<pl.kruczek.httpjavabasic.lang.LangDto> getAll() {
                return Arrays.asList(langDto);
            }

            @Override
            public LangDto findById(UUID id) {
                return langDto;
            }
        };
    }

}
