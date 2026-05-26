package com.bajaj.bfhl.service.impl;

import com.bajaj.bfhl.dto.BfhlRequest;
import com.bajaj.bfhl.dto.BfhlResponse;
import com.bajaj.bfhl.service.BfhlService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BfhlServiceImpl implements BfhlService {

    private static final String USER_ID = "sanvi_nahar_31082004";
    private static final String EMAIL = "sanvinahar230695@acropolis.in";
    private static final String ROLL_NUMBER = "0827CI231117";

    @Override
    public BfhlResponse processData(BfhlRequest request) {
        List<String> data = request.getData();

        List<String> oddNumbers = new ArrayList<>();
        List<String> evenNumbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        List<String> specialCharacters = new ArrayList<>();
        int sum = 0;

        for (String item : data) {
            if (isNumber(item)) {
                int num = Integer.parseInt(item);
                sum += num;
                if (num % 2 == 0) {
                    evenNumbers.add(item);
                } else {
                    oddNumbers.add(item);
                }
            } else if (isAlphabet(item)) {
                alphabets.add(item.toUpperCase());
            } else {
                specialCharacters.add(item);
            }
        }

        String concatString = buildConcatString(alphabets);

        return BfhlResponse.builder()
                .success(true)
                .userId(USER_ID)
                .email(EMAIL)
                .rollNumber(ROLL_NUMBER)
                .oddNumbers(oddNumbers)
                .evenNumbers(evenNumbers)
                .alphabets(alphabets)
                .specialCharacters(specialCharacters)
                .sum(String.valueOf(sum))
                .concatString(concatString)
                .build();
    }

    private boolean isNumber(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isAlphabet(String s) {
        return s.length() == 1 && Character.isLetter(s.charAt(0));
    }

    private String buildConcatString(List<String> alphabets) {
        if (alphabets.isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (String a : alphabets) {
            sb.append(a.toLowerCase());
        }

        String reversed = sb.reverse().toString();

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < reversed.length(); i++) {
            char c = reversed.charAt(i);
            if (i % 2 == 0) {
                result.append(Character.toUpperCase(c));
            } else {
                result.append(Character.toLowerCase(c));
            }
        }

        return result.toString();
    }
}
