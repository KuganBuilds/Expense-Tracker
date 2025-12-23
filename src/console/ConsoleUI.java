package console;

import model.Expense;
import service.ExpenseService;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class ConsoleUI {


    Scanner scanner = new Scanner(System.in);
    ExpenseService service = new ExpenseService();


    public void start() {
        boolean running = true;

        while (running) {
            try {
                showMenu();
                int choice = scanner.nextInt();
                scanner.nextLine();


                switch (choice) {
                    case 1 -> addExpenseUI();
                    case 2 -> getAllExpenseUI();
                    case 3 -> getExpenseSummaryUI();
                    case 4 -> getByMonthUI();
                    case 5 -> updateExpenseUI();
                    case 6 -> deleteUI();
                    case 7 -> {
                        System.out.println("Exiting application...");
                        running = false;
                    }
                    default -> System.out.println("Invalid option. Try again.");
                }
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("\nInvalid option. Try again.");
                scanner.nextLine();
            }

        }

    }


    private void showMenu() {
        System.out.println("\n*** Expense Tracker ***");
        System.out.println("1. Add Expense");
        System.out.println("2. View all Expenses");
        System.out.println("3. View expenses Summary");
        System.out.println("4. Expenses Summary by month");
        System.out.println("5. Update Expense");
        System.out.println("6. Delete an Expense");
        System.out.println("7. Exit");
        System.out.print("# Enter your choice : ");
    }

    private void addExpenseUI() {

        try {
            System.out.println("\n$ Expense-tracker add : ");

            System.out.print("Description : ");
            String description = scanner.nextLine().trim().toUpperCase();

            System.out.print("Amount : ");
            long amount = scanner.nextLong();
            scanner.nextLine(); // consume newline

            if (amount <= 0) {
                System.out.println("Error: Amount must be greater than zero");
                return;
            }

            System.out.print("Spent date (YYYY-MM-DD): ");
            String dateInput = scanner.nextLine().trim();

            LocalDate date;
            try {
                date = LocalDate.parse(dateInput);
            } catch (java.time.format.DateTimeParseException e) {
                System.out.println("Error: Invalid date format. Use YYYY-MM-DD");
                return;
            }

            if (date.isAfter(LocalDate.now())) {
                System.out.println("Error: Expense date cannot be in the future");
                return;
            }

            Expense expense = service.addExpense(description, amount, date);
            System.out.printf("# Expense added successfully. ID: %d%n", expense.getId());

        } catch (InputMismatchException e) {
            System.out.println("Error: Invalid input type");
            scanner.nextLine(); // clear bad input
        }
    }


    private void getAllExpenseUI() {

        List<Expense> expenses = service.getAllExpenses();

        System.out.println("\n$ expense-tracker list");
        System.out.println("# ID  Date        Description   Amount");

        for (Expense expense : expenses) {
            System.out.printf(
                    "# %-3d %-11s %-13s ₹%d%n",
                    expense.getId(),
                    expense.getDate(),
                    expense.getDescription(),
                    expense.getAmount()
            );
        }

    }

    private void getExpenseSummaryUI() {


        long total = service.getTotalSummary();

        System.out.println("\n$ Expense-tracker summary");
        System.out.printf("# Total expenses: ₹%,d%n", total);

    }

    private void deleteUI() {

        try {
            System.out.println("$ expense-tracker delete --id : ");
            long id = scanner.nextInt();
            scanner.nextLine();

            System.out.println(service.deleteExpense(id));
        } catch (InputMismatchException inputMismatchException) {
            System.out.println("Error : " + inputMismatchException.getMessage());
        }

    }

    private void updateExpenseUI() {

        try {
            System.out.print("\nEnter Expense ID to update: ");
            long id = scanner.nextLong();
            scanner.nextLine();

            System.out.print("Description: ");
            String description = scanner.nextLine().trim().toUpperCase();

            System.out.print("Amount: ");
            long amount = scanner.nextLong();
            scanner.nextLine();

            Expense updatedExpense = service.updateExpense(id, description, amount);

            System.out.printf(
                    "# Expense updated successfully. ID: %d%n",
                    updatedExpense.getId()
            );

        } catch (InputMismatchException e) {
            System.out.println("Error: Invalid input type");
            scanner.nextLine();
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    private void getByMonthUI() {
        try {
            System.out.print("Enter month (1-12): ");
            int month = scanner.nextInt();
            scanner.nextLine();

            long total = service.getMonthlySummary(month);

            System.out.printf(
                    "# Total expenses for month %d: ₹%,d%n",
                    month,
                    total
            );
        } catch (InputMismatchException inputMismatchException) {
            System.out.println("Error : " + inputMismatchException.getMessage());
        }

    }
}
