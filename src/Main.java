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
            int yandexBotRequests = 0;
            int googleBotRequests = 0;
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


                if (line.contains("(compatible")) {
                    // Выделяем часть между первым набором круглых скобок
                    int startIndex = line.indexOf('(');
                    int endIndex = line.indexOf(')', startIndex);
                    if (startIndex != -1 && endIndex != -1) {
                        String inFirstBrackets = line.substring(startIndex + 1, endIndex);

                        // Разбиваем по ";"
                        String[] parts = inFirstBrackets.split(";");
                        for (int i = 0; i < parts.length; i++) {
                            parts[i] = parts[i].trim();
                        }

                        if (parts.length >= 2) {
                            String secondPart = parts[1];


                            int slashIndex = secondPart.indexOf('/');
                            if (slashIndex != -1) {
                                String programName = secondPart.substring(0, slashIndex);


                                switch (programName.toLowerCase()) {
                                    case "googlebot":
                                        googleBotRequests++;
                                        break;
                                    case "yandexbot":
                                        yandexBotRequests++;
                                        break;
                                }
                            }
                        }
                    }
                }
            }

            double yandexBotRatio = (double) yandexBotRequests / lineCount * 100;
            double googleBotRatio = (double) googleBotRequests / lineCount * 100;
            System.out.println("Количество строк: " + lineCount);
            System.out.printf("Доля запросов от YandexBot: %.2f%%\n", yandexBotRatio);
            System.out.printf("Доля запросов от Googlebot: %.2f%%\n", googleBotRatio);
            //            System.out.println("Максимальная длина строки: " + maxLength);
            //            System.out.println("Минимальная длина строки: " + minLength);
        }
    }
}