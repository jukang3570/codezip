package com.example.csvtest2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvParser {

    public static List<List<String>> parseCsv(InputStream inputStream, String targetDistrict) {
        List<List<String>> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] token = line.split(",");
                List<String> tempList = new ArrayList<>(Arrays.asList(token));
                // '자치구'가 'Jongno-gu'인 경우만 리스트에 추가
                if (tempList.size() > 0 && tempList.get(0).trim().equalsIgnoreCase(targetDistrict)) {
                    result.add(tempList);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}


