import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

//клас для сортування
class Record implements Comparable<Record> {
    String name;
    double value;

    public Record(String name, double value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public int compareTo(Record other) {
        return this.name.compareTo(other.name); //сортування за назвою
    }
}

class Lab5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nМЕНЮ ЛАБОРАТОРНОЇ РОБОТИ №5");
            System.out.println("1 — Завдання 1: Робота з ArrayList, Comparable та файлом");
            System.out.println("2 — Завдання 2 (Варіант 2): Реверс цифр числа за допомогою Stack");
            System.out.println("0 — Вихід");
            System.out.print("Оберіть пункт меню: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    runTask1(scanner);
                    break;
                case 2:
                    runTask2(scanner);
                    break;
                case 0:
                    System.out.println("Вихід з програми.");
                    break;
                default:
                    System.out.println("Невірний вибір. Спробуйте ще раз.");
            }
        } while (choice != 0);

        scanner.close();
    }

    // ЗАВДАННЯ 1
    public static void runTask1(Scanner scanner) {
        ArrayList<Record> records = new ArrayList<>();

        records.add(new Record("Banana", 15.5));
        records.add(new Record("Apple", 10.0));
        records.add(new Record("Orange", 20.0));

        System.out.print("Введіть назву нового елемента: ");
        String name = scanner.nextLine();
        System.out.print("Введіть числове значення: ");
        double value = scanner.nextDouble();
        scanner.nextLine();
        records.add(new Record(name, value));

        Collections.sort(records);

        String fileName = "records_output.txt";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            for (Record r : records) {
                writer.write(r.name + " - " + r.value);
                writer.newLine();
            }
            writer.close();
            System.out.println("Дані успішно відсортовано і записано у файл '" + fileName + "'!");
        } catch (IOException e) {
            System.out.println("Помилка запису у файл: " + e.getMessage());
        }
    }

    // ЗАВДАННЯ 2 (Варіант 2)
    public static void runTask2(Scanner scanner) {
        System.out.print("Введіть ціле число для реверсу: ");
        String inputStr = scanner.nextLine();

        try {
            long number = Long.parseLong(inputStr);
            Stack<Character> stack = new Stack<>();

            boolean isNegative = number < 0;
            String absStr = isNegative ? inputStr.substring(1) : inputStr;

            //кожну цифру у стек
            for (int i = 0; i < absStr.length(); i++) {
                stack.push(absStr.charAt(i));
            }

            StringBuilder reversedStr = new StringBuilder();
            if (isNegative) {
                reversedStr.append("-");
            }

            while (!stack.isEmpty()) {
                reversedStr.append(stack.pop());
            }

            System.out.println("Початкове число: " + inputStr);
            System.out.println("Число у зворотному порядку: " + reversedStr.toString());

        } catch (NumberFormatException e) {
            System.out.println("Помилка: введено некоректне число!");
        }
    }
}