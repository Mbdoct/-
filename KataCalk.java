import java.util.Scanner;

class katacalk {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите два числа (арабских или римских): ");
        String expression = scanner.nextLine();
        System.out.println(parse(expression));
    }

    public static String parse(String expression) throws Exception {
        int n1;
        int n2;
        String oper;
        String result;
        boolean isRom;
        String[] operands = expression.split("[+\\-*/]");
        if (operands.length != 2) throw new Exception("Должно быть два числа");
        oper = detectOperation(expression);
        if (oper == null) throw new Exception("Неподдерживаемая математическая операция");
        //если оба числа римские
        if (Rom.isRom(operands[0]) && Rom.isRom(operands[1])) {
            //конвертируем оба числа в арабские для вычесления действия
            n1 = Rom.convertToArabian(operands[0]);
            n2 = Rom.convertToArabian(operands[1]);
            isRom = true;
        }
        //если оба числа арабские
        else if (!Rom.isRom(operands[0]) && !Rom.isRom(operands[1])) {
            n1 = Integer.parseInt(operands[0]);
            n2 = Integer.parseInt(operands[1]);
            isRom = false;
        }
        //если одни число римское, а другое - арабское
        else {
            throw new Exception("Числа должны быть одной языковой группы");
        }
        if (n1 > 10 || n2 > 10) {
            throw new Exception("Числа должны быть от 1 до 10");
        }
        int arabian = calc(n1, n2, oper);
        if (isRom) {
            //если римское число получилось меньше либо равно нулю, генерируем ошибку
            if (arabian <= 0) {
                throw new Exception("Римское число должно быть больше нуля");
            }
            //конвертируем результат операции из арабского в римское
            result = Rom.convertToRom(arabian);
        } else {
            //Конвертируем арабское число в тип String
            result = String.valueOf(arabian);
        }
        //возвращаем результат
        return result;
    }

    static String detectOperation(String expression) {
        if (expression.contains("+")) return "+";
        else if (expression.contains("-")) return "-";
        else if (expression.contains("*")) return "*";
        else if (expression.contains("/")) return "/";
        else return null;
    }

    static int calc(int a, int b, String oper) {

        if (oper.equals("+")) return a + b;
        else if (oper.equals("-")) return a - b;
        else if (oper.equals("*")) return a * b;
        else return a / b;
    }

}

class Rom {
    static String[] romArray = new String[]{"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI",
            "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX"};


    public static boolean isRom(String val) {
        for (int i = 0; i < romArray.length; i++) {
            if (val.equals(romArray[i])) {
                return true;
            }
        }
        return false;
    }

    public static int convertToArabian(String roman) {
        for (int i = 0; i < romArray.length; i++) {
            if (roman.equals(romArray[i])) {
                return i;
            }
        }
        return -1;
    }

    public static String convertToRom(int arabian) {
        return romArray[arabian];
    }

}
