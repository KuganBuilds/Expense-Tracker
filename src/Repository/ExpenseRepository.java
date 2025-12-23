package Repository;

import model.Expense;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExpenseRepository {


    private long idCounter = 1;


    private List<Expense> expenseList = new ArrayList<>(
            Arrays.asList()
    );


    public Expense save(Expense expense) {
        expense.setId(idCounter++);
        expenseList.add(expense);
        return expense;
    }

    public List<Expense> findAll() {
        return new ArrayList<>(expenseList);
    }

    public Expense findById(long id) {
        for (Expense expense : expenseList) {
            if (expense.getId() == id) {
                return expense;
            }
        }
        return null;
    }


    public String deleteById(long id) {
        int index = 0;
        boolean found = false;
        for (int i = 0;i < expenseList.size();i++){
            if (expenseList.get(i).getId() == id){
                index = i;
                found = true;
            }
        }
        if (!found){
            return "# No Expense Found in that ID";
        }else {
            expenseList.remove(index);
            return "# Expense deleted successfully";
        }
    }


}
