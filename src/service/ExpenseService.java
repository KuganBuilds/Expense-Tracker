package service;

import Repository.ExpenseRepository;
import model.Expense;

import java.time.LocalDate;
import java.util.List;

public class ExpenseService {


    private final ExpenseRepository repository;

    public ExpenseService(){
        this.repository = new ExpenseRepository();
    }


    public Expense addExpense(String description, long amount, LocalDate date) {
        Expense expense = new Expense(description, amount, date);
        return repository.save(expense);
    }



    public Expense updateExpense(long id, String description, long amount) {

        Expense expense = repository.findById(id);

        if (expense == null) {
            throw new IllegalArgumentException("Expense not found");
        }

        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }

        expense.setDescription(description);
        expense.setAmount(amount);

        return expense;
    }



    public String deleteExpense(long id) {
        return repository.deleteById(id);
    }


    public List<Expense> getAllExpenses() {
        return repository.findAll();
    }


    public long getTotalSummary() {
        List<Expense> expenses = repository.findAll();
        long total = 0;
        for (Expense expense : expenses) {
            total += expense.getAmount();
        }
        return total;
    }


    public long getMonthlySummary(int month) {
        int currentYear = LocalDate.now().getYear();

        return repository.findAll().stream()
                .filter(e -> e.getDate().getYear() == currentYear)
                .filter(e -> e.getDate().getMonthValue() == month)
                .mapToLong(Expense::getAmount)
                .sum();
    }
}
