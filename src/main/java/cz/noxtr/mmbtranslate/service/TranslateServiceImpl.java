package cz.noxtr.mmbtranslate.service;

import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class TranslateServiceImpl implements TranslateService {
    private static final int HARD_SPACE_CHARACTER = 32;
    private static final Set<Character> UPPER_CASE_LETTER_FOR_POSITIONS =
            Sets.newHashSet('a', 'e', 'i', 'o', 'u');

    @Override
    public String transform(String value) {
        if (StringUtils.isBlank(value)) {
            return "";
        }

        Set<Integer> foundPositionToUpperCase = new HashSet<>();

        char[] chars = value.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (UPPER_CASE_LETTER_FOR_POSITIONS.contains(chars[i])) {
                foundPositionToUpperCase.add(i);
            }
        }

        StringBuilder reverseStringBuilder = new StringBuilder(value.toLowerCase());
        reverseStringBuilder.reverse();

        foundPositionToUpperCase
                .forEach(index -> {
                    char upperCasedCharacter = Character.toUpperCase(reverseStringBuilder.charAt(index));
                    reverseStringBuilder.deleteCharAt(index);
                    reverseStringBuilder.insert(index.intValue(), upperCasedCharacter);
                });


        return trimWhiteSpaces(reverseStringBuilder);
    }

    private String trimWhiteSpaces(StringBuilder reverseStringBuilder) {
        StringBuilder trimmedStringBuilder = new StringBuilder(reverseStringBuilder.length());

        boolean previousSpace = false;
        for (int i = 0; i < reverseStringBuilder.length(); i++) {
            char currentChar = reverseStringBuilder.charAt(i);
            if (!previousSpace || currentChar != HARD_SPACE_CHARACTER) {
                trimmedStringBuilder.append(currentChar);
            }
            previousSpace = currentChar == HARD_SPACE_CHARACTER;

        }
        return trimmedStringBuilder.toString().trim();
    }
}
