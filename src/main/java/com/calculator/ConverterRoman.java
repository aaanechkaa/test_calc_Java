package com.calculator;

class ConverterRoman {

    static String init(int value) {
        String result = "";

        int[] roman_value_list = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] roman_char_list = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        for (int i = 0; i < roman_value_list.length; i += 1) {
            while (value >= roman_value_list[i]){
                value -= roman_value_list[i];
                result += roman_char_list[i];
            }
        }

        return result;
    }

}
