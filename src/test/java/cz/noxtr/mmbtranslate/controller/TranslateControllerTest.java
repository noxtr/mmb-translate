package cz.noxtr.mmbtranslate.controller;

import cz.noxtr.mmbtranslate.service.TranslateService;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
class TranslateControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    protected TranslateService translateService;

    @Test
    void findAllCarBrand() throws Exception {
        String entered = "Abcd";
        String translated = "Dcba";
        when(translateService.transform(entered)).thenReturn(translated);
        mockMvc.perform(post("/translate").content(entered).contentType("text/plain"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(translated)));
    }
}