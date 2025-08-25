import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int fileCount = 0;
        String path;
        while (true) {
            System.out.print("Введите путь к файлу: ");
            path = new Scanner(System.in).nextLine();

            File file = new File(path);
            boolean fileExists = file.exists();
            boolean isDirectory = file.isDirectory();

            if (!fileExists || isDirectory) {
                System.out.println("Указанный путь неверен.");
                continue;
            }
            fileCount++;
            System.out.println("Путь указан верно");
            System.out.println("Это файл номер " + fileCount);

            FileReader fileReader = null;
            try {
                fileReader = new FileReader(path);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
            BufferedReader reader = new BufferedReader(fileReader);
            String line = "";
            int maxLength = 0;
            int minLength = Integer.MAX_VALUE;
            int lineCount = 0;
            while (true) {
                try {
                    if ((line = reader.readLine()) == null) break;
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                lineCount++;
                int currentLength = line.length();
                if (currentLength > maxLength) {
                    maxLength = currentLength;
                }
                if (currentLength < minLength && currentLength > 0) {
                    minLength = currentLength;
                }
                if (currentLength > 1024) throw new RuntimeException("Длина строки более 1024 символов");
            }
            System.out.println("Количество строк: " + lineCount);
            System.out.println("Максимальная длина строки: " + maxLength);
            System.out.println("Минимальная длина строки: " + minLength);
        }
    }
}