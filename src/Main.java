import java.util.Scanner;

public class Main {

    private final static String[] ROMAN_NUMBERS = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    private final static int[] ARABIC_NUMBERS = {100, 90, 50, 40, 10, 9, 5, 4, 1};


    public static String calc(String input) throws Exception {
        String inputNoSpaces = input.replaceAll(" ", "");

        if (!inputNoSpaces.replaceAll("[\\d-+/*IXV]", "").matches("")) throw new Exception("Ошибка ввода");

        String operator = inputNoSpaces.replaceAll("[^-+/*]", "");
        String[] operands = inputNoSpaces.split("[-+/*]");

        if (operands.length != 2) throw new Exception("Неправильное выражение");

        int op1;
        int op2;

        if (isRoman(operands[0]) && isRoman(operands[1])) {
            op1 = romanToArabic(operands[0]);
            op2 = romanToArabic(operands[1]);
            return intToRoman(operate(operator, op1, op2));

        } else if (!isRoman(operands[0]) && !isRoman(operands[1])) {
            op1 = Integer.parseInt(operands[0]);
            op2 = Integer.parseInt(operands[1]);
            return String.valueOf(operate(operator, op1, op2));

        } else {
            throw new Exception("Разная нумерация");
        }

    }

    public static int operate(String operator, int op1, int op2) throws Exception {

        if (op1 > 10 || op1 < 1 || op2 > 10 || op2 < 1) throw new Exception("Операнды от 1 до 10 включительно");

        switch (operator) {
            case ("+"):
                return op1 + op2;
            case ("-"):
                return op1 - op2;
            case ("*"):
                return op1 * op2;
            case ("/"):
                return  op1 / op2;
            default:
                throw new Exception("Ошибка");
        }
    }

    public static int romanToArabic(String s) throws Exception {

        switch (s) {
            case "I":
                return 1;
            case "II":
                return 2;
            case "III":
                return 3;
            case "IV":
                return 4;
            case "V":
                return 5;
            case "VI":
                return 6;
            case "VII":
                return 7;
            case "VIII":
                return 8;
            case "IX":
                return 9;
            case "X":
                return 10;
            default:
                throw new Exception("Нет такого числа");
        }
    }

    public static boolean isRoman(String s) {
        return s.replaceAll("[XIV]", "").matches("");
    }

    public static String intToRoman(int number) throws Exception {

        if (number < 1) throw new Exception("Отрицательное число");

        var result = new StringBuilder();
        int remaining = number;

        for (int i = 0; i < ROMAN_NUMBERS.length; i++) {
            int arabic = ARABIC_NUMBERS[i];
            String romanNumber = ROMAN_NUMBERS[i];
            while (remaining >= arabic) {
                result.append(romanNumber);
                remaining -= arabic;
            }
        }
        return result.toString();
    }


    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.println("Введите пример: ");
        try {
            System.out.println(calc(scan.nextLine()));
            main(args);
        } catch (Exception ex) {
            System.out.println("Неверный формат. " + ex);
        }
    }
}

