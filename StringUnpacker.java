package com.company;

public class StringUnpacker {

    private int number = 0;
//метод проверки валидности строки
    private boolean validCheck(String str) {

        if (this.number != 0) {
            return true;
        }
        int intervalStart = 0;
        int intervalEnd = 0;
//проверка использования чисел только для обозначения повторяющихся сегментов
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) > 47 && str.charAt(i) < 58) {
                while (str.charAt(i) > 47 && str.charAt(i) < 58) {
                    i++;
                }
                if (str.charAt(i) != '[') {
                    System.out.println("Error: the numbers must be adjacent to the brackets.");
                    return false;
                }
            }
        }
//проверка допустимых символов и корректной расстановки скобок
        int tmp = 0;
        int cnt = 0;
        while (cnt < str.length()) {
            if (!((str.charAt(cnt) > 47 && str.charAt(cnt) < 58) || (str.charAt(cnt) > 64 && str.charAt(cnt) < 92) || (str.charAt(cnt) > 96 && str.charAt(cnt) < 123) || str.charAt(cnt) == 93)) {
                System.out.println("Error: invalid characters");
                return false;
            }
            if (str.charAt(cnt) == '[') {
                tmp++;
            }
            if (str.charAt(cnt) == ']') {
                tmp--;
            }
            cnt++;
        }
        if (tmp != 0) {
            System.out.println("Error: incorrect brackets.");
            return false;
        }
        return true;

    }
//метод распаковывания строки
    public String unpack(String str) {

        if (!this.validCheck(str)) {
            return "";
        }
        StringBuilder res = new StringBuilder();
        int intervalStart = 0;
        int intervalEnd = 0;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) > 47 && str.charAt(i) < 58) {
                if (intervalStart <= intervalEnd) {
                    //поиск повторяющегося сегмента
                    res.append(str.substring(intervalStart, intervalEnd));
                    intervalStart = intervalEnd;
                    while (str.charAt(intervalEnd) != '[') {
                        intervalEnd++;
                    }
                    int repeats = Integer.parseInt(str.substring(intervalStart, intervalEnd));
                    intervalStart = intervalEnd + 1;

                    intervalEnd++;
                    int tmp = 1;
                    while (tmp != 0) {
                        if (str.charAt(intervalEnd) == '[') {
                            tmp++;
                        }
                        if (str.charAt(intervalEnd) == ']') {
                            tmp--;
                        }
                        intervalEnd++;
                    }
                    intervalEnd--;
                    //рекурсионный вызов метода для обработки сегмента необходимое количество раз
                    while (repeats > 0) {
                        this.number++;
                        res.append(this.unpack(str.substring(intervalStart, intervalEnd)));
                        this.number--;
                        repeats--;
                    }

                    intervalStart = intervalEnd + 1;
                    if (intervalStart >= str.length()) {
                        intervalStart = str.length();
                    }
                    if (intervalEnd >= str.length()) {
                        intervalEnd = str.length();
                    }
                    i = intervalEnd - 1;
                }
            }
            else {
                intervalEnd++;
                if (intervalEnd >= str.length()) {
                    intervalEnd = str.length();
                }
            }

            if (i == str.length() - 1) {
                if (intervalStart < intervalEnd) {
                    res.append(str.substring(intervalStart, intervalEnd));
                }
            }
        }

        return res.toString();

    }

}
