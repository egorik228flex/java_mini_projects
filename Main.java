import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, List<Integer>> expensesMap = new HashMap<>();

        List<String> categories = Arrays.asList("еда", "транспорт", "развлечения");

        for (String category : categories) {
            List<Integer> categoryExpenses = readExpenses(scanner, category);
            expensesMap.put(category, categoryExpenses);
        }

        System.out.println("\nИтоги расходов по категориям:");
        int overallTotal = 0;
        String maxCategory = "";
        int maxSum = 0;

        for (String category : categories) {
            List<Integer> list = expensesMap.get(category);
            int sum = list.stream().mapToInt(Integer::intValue).sum();
            System.out.println(capitalize(category) + ": " + sum + " руб.");
            overallTotal += sum;

            if (sum > maxSum) {
                maxSum = sum;
                maxCategory = category;
            }
        }

        System.out.println("\nОбщая сумма расходов: " + overallTotal + " руб.");
        System.out.println("Самая затратная категория: " + capitalize(maxCategory) + " (" + maxSum + " руб.)");
    }

    public static List<Integer> readExpenses(Scanner scanner, String categoryName) {
        List<Integer> expenses = new ArrayList<>();
        while (true) {
            System.out.println("Введите затраты на " + categoryName + " (0 — закончить):");
            int amount = scanner.nextInt();
            if (amount == 0) {
                break;
            }
            if (amount < 0) {
                System.out.println("Сумма не может быть отрицательной. Попробуй снова.");
                continue;
            }
            expenses.add(amount);
        }
        return expenses;
    }

    public static String capitalize(String word) {
        if (word == null || word.isEmpty()) return word;
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }
}
