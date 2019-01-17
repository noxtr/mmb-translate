package cz.noxtr.mmbtranslate.service;

import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class TranslateServiceImpl implements TranslateService {
    private static final int SPACE_CHARACTER = 32;
    private static final Set<Character> UPPER_CASE_LETTER_FOR_POSITIONS =
            Sets.newHashSet('a', 'e', 'i', 'o', 'u');

    @Override
    public String transform(String value) {
        if (StringUtils.isBlank(value)) {
            return "";
        }

        char[] chars = value.toCharArray();
        StringBuilder resultBuilder = new StringBuilder(chars.length);

        boolean previousSpace = false;
        for (int i = chars.length - 1; i >= 0; i--) {
            char currentChar = chars[i];
            if (!previousSpace || currentChar != SPACE_CHARACTER) {
                char mirrorChar = chars[chars.length - 1 - i];
                if (UPPER_CASE_LETTER_FOR_POSITIONS.contains(mirrorChar)) {
                    resultBuilder.append(Character.toUpperCase(currentChar));
                } else {
                    resultBuilder.append(Character.toLowerCase(currentChar));
                }
            }
            previousSpace = currentChar == SPACE_CHARACTER;

        }
        return resultBuilder.toString();
    }
}
