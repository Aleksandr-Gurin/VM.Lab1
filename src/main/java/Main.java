import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Read> reads = new ArrayList<>();
        reads.add(new ConsoleMatrixReader());
        reads.add(new FileMatrixReader());

        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Меню:");
                int i = 1;
                for (Read read : reads) {
                    System.out.println(i++ + ". " + read.getMessage());
                }
                if (scanner.hasNext()) {
                    String result = scanner.nextLine();
                    if (isNumeric(result) && Integer.parseInt(result) > 0 && Integer.parseInt(result) <= reads.size()) {
                        reads.get(Integer.parseInt(result) - 1).read();
                    } else {
                        System.out.println("Такого варианта нет. Попробуйте ещё раз");
                    }
                } else {
                    System.out.println("Завершение работы");
                    System.exit(0);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
