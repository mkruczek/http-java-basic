package pl.kruczek.httpjavabasic;

import org.junit.Test;

import java.util.Optional;
import java.util.UUID;

import static junit.framework.TestCase.assertEquals;

public class HelloServiceTest {


    @Test
    public void test_userNull_getGreeting_returnDefaultValue() {
        //given
        Lang lang = new Lang(UUID.randomUUID(), "Siemanko", "pl_PL");
        LangRepository mock = createMockLangRepository(lang);
        HelloService sut = new HelloService(mock);
        String user = null;

        //when
        String greeting = sut.getGreeting(user, lang.getId().toString());

        //then
        assertEquals("Siemanko world!", greeting);
    }


    @Test
    public void test_userMkruczek_getGreeting_returnMkruczek() {
        //given
        Lang lang = new Lang(UUID.randomUUID(), "Hello", "en_US");
        LangRepository mock = createMockLangRepository(lang);
        HelloService sut = new HelloService(mock);
        String user = "mkruczek";
        //when
        String greeting = sut.getGreeting(user, lang.getId().toString());

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
    public void test_idString_userMkruczek_getGreeting_returnDefaultLang() {
        //given
        HelloService sut = new HelloService();
        String user = "mkruczek";

        //when
        String greeting = sut.getGreeting(user, "abc");

        //then
        assertEquals("Hello " + user + "!", greeting);
    }

    @Test
    public void test_idEmptyLang_getGreeting_returnDefaultLang() {
        //given
        LangRepository mock = new LangRepository() {
            @Override
            Optional<Lang> findById(UUID id) {
                return Optional.empty();
            }
        };
        HelloService sut = new HelloService(mock);

        //when
        String greeting = sut.getGreeting(null, UUID.randomUUID().toString());

        //then
        assertEquals("Hello world!", greeting);
    }

    private LangRepository createMockLangRepository(Lang lang) {
        return new LangRepository() {
            @Override
            Optional<Lang> findById(UUID id) {
                return Optional.of(lang);
            }
        };
    }

}
