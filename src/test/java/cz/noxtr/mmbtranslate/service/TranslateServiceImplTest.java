package cz.noxtr.mmbtranslate.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class TranslateServiceImplTest {

    private TranslateServiceImpl translateService;

    @BeforeEach
    void setUp() {
        translateService = new TranslateServiceImpl();
    }

    @ParameterizedTest
    @CsvSource(value = {
            "Ahoj, jak se máš?;?šÁm es kaj ,joha",
            "Je     mi   fajn.;.Njaf Im ej",
            "aaaadddd;DDDDaaaa",
            ";''"
    }, delimiter = ';')
    void transformTextAccordingBusinessLogic(String value, String expected) {
        String transform = translateService.transform(value);
        assertThat(transform)
                .isEqualTo(expected);
    }
}