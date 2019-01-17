package cz.noxtr.mmbtranslate.controller;

import cz.noxtr.mmbtranslate.service.TranslateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

@RestController
@RequestMapping(value = "/translate")
@Api(tags = ApiTags.TRANSLATE)
@RequiredArgsConstructor
public class TranslateController {

    private final TranslateService translateService;

    @PostMapping(produces = TEXT_PLAIN_VALUE, consumes = TEXT_PLAIN_VALUE)
    @ApiOperation(value = "Return translated string", response = String.class)
    public String translate(@RequestBody String value) {
        return translateService.transform(value);
    }

}
