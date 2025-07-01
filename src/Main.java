import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Вводим с клавиатуры два числа
        System.out.print("Введите первое число: ");
        int firstNum = new Scanner(System.in).nextInt();
        System.out.print("Введите второе число: ");
        int secondNum = new Scanner(System.in).nextInt();

        //Вычисляем сумму
        int sum = firstNum + secondNum;
        System.out.println("Сумма двух чисел: " + sum);

        //Вычисляем разность
        int difference = firstNum - secondNum;
        System.out.println("Сумма двух чисел: " + difference);

        //Вычисляем произведение
        int multiplication = firstNum * secondNum;
        System.out.println("Произведение двух чисел: " + multiplication);

        //Вычисляем частное от деления двух чисел
        double quotient = (double) firstNum / secondNum;
        System.out.println("Частное от деления двух чисел: " + quotient);
    }
}
