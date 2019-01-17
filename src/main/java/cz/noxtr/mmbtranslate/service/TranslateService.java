package cz.noxtr.mmbtranslate.service;

/**
 * Service for translate entered text
 */
public interface TranslateService {

    /**
     * Translate param input param by requested logic
     *
     * Reverse string and make all letter lowercase except of ones on original position where was (a,e,i,o,u)
     *
     * @param value entered value to transform
     * @return tranns
     */
    String transform(String value);
}
