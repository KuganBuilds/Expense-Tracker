Expense Tracker – Java CLI Application

A simple Command Line Expense Tracker built using Core Java.
This project demonstrates clean architecture, proper layering,
and real-world input validation using a console-based interface.


FEATURES
--------
- Add expenses with description, amount, and date
- View all expenses in a formatted CLI table
- Update an expense by ID
- Delete an expense by ID
- View total expense summary
- View monthly expense summary (current year)
- Input validation for amount and date
- Uses LocalDate for accurate date handling


TECH STACK
----------
- Java (Core Java)
- Java Collections (ArrayList)
- java.time.LocalDate
- CLI using Scanner
- In-memory storage (no database)


PROJECT STRUCTURE
-----------------
```
src/
 ├── console/
 │   └── ConsoleUI.java
 ├── model/
 │   └── Expense.java
 ├── repository/
 │   └── ExpenseRepository.java
 ├── service/
 │   └── ExpenseService.java
 └── Main.java
```


HOW TO RUN
----------
1. Clone the repository:
   git clone <your-github-repo-url>

2. Compile:
   javac Main.java

3. Run:
   java Main


SAMPLE OUTPUT
-------------
```
$ expense-tracker list
# ID  Date        Description   Amount
# 1   2024-08-06  LUNCH         ₹20
# 2   2024-08-06  DINNER        ₹10

# Total expenses for month 8: ₹30
```


VALIDATION RULES
----------------
- Amount must be greater than zero
- Expense date cannot be in the future
- Date format must be YYYY-MM-DD
- Handles invalid input without crashing


LEARNING OUTCOMES
-----------------
- Clean separation of UI, Service, and Repository layers
- Proper use of object references
- CLI input handling best practices
- Real-world use of LocalDate
- Strong foundation for Spring Boot and database integration


FUTURE ENHANCEMENTS (OPTIONAL)
------------------------------
- File persistence (JSON / CSV)
- Category-based expenses
- Monthly budget limits
- Unit testing
- Migration to Spring Boot and JPA


PROJECT STATUS
--------------
Completed (v1.0)

This project is finalized and stable.
Next step: rebuilding the same application using Spring Boot.


AUTHOR
------
Kugan
Aspiring Java Full Stack Developer
