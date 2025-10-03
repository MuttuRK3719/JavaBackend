-- Customers 
drop table Categories;
CREATE TABLE Customers (
    CustomerID NUMBER PRIMARY KEY,
    FullName   VARCHAR2(100) NOT NULL,
    Email      VARCHAR2(100) UNIQUE NOT NULL,
    Phone      VARCHAR2(20),
    CreatedAt  DATE DEFAULT SYSDATE
);
 -- Categories 
CREATE TABLE Categories (
    CategoryID NUMBER PRIMARY KEY,
    CategoryName VARCHAR2(50) UNIQUE NOT NULL
);
 
 -- Products 
CREATE TABLE Products (
    ProductID NUMBER PRIMARY KEY,
    ProductName VARCHAR2(100) NOT NULL,
    Price NUMBER(10,2) NOT NULL,
    Stock NUMBER NOT NULL,
    CategoryID NUMBER,
    FOREIGN KEY (CategoryID) REFERENCES Categories(CategoryID)
); 
 -- Orders 
CREATE TABLE Orders (
    OrderID NUMBER PRIMARY KEY,
    CustomerID NUMBER,
    OrderDate DATE DEFAULT SYSDATE,
    PaymentMethod VARCHAR2(50),
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);
 -- Order Details (many-to-many between Orders and Products) 
CREATE TABLE OrderDetails ( 
    OrderDetailID INT PRIMARY KEY , 
    OrderID INT, 
    ProductID INT, 
    Quantity INT NOT NULL, 
    Price DECIMAL(10,2) NOT NULL, -- store price at time of order 
    FOREIGN KEY (OrderID) REFERENCES Orders(OrderID), 
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID) 
); 
 -- Audit Table 
CREATE TABLE OrderDetails (
    OrderDetailID NUMBER PRIMARY KEY,
    OrderID NUMBER,
    ProductID NUMBER,
    Quantity NUMBER NOT NULL CHECK (Quantity > 0),
    Price NUMBER(10,2) NOT NULL, -- store price at time of order
    FOREIGN KEY (OrderID) REFERENCES Orders(OrderID),
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
);
 -- High Value Orders 
CREATE TABLE OrderAudit (
    AuditID NUMBER PRIMARY KEY,
    OrderID NUMBER,
    CustomerID NUMBER,
    OrderDate DATE,
    ActionType VARCHAR2(20),
    LoggedAt DATE DEFAULT SYSDATE
);
CREATE TABLE HighValueOrders (
    HVOrderID NUMBER PRIMARY KEY,
    OrderID NUMBER,
    CustomerID NUMBER,
    TotalAmount NUMBER(12,2),
    CreatedAt DATE DEFAULT SYSDATE
);  


SELECT *
FROM (
    SELECT 
        c.CustomerID,
        c.FullName,
        SUM(od.Quantity * od.Price) AS TotalSpent,
        COUNT(DISTINCT p.CategoryID) AS CategoryCount
    FROM Customers c
    JOIN Orders o 
        ON c.CustomerID = o.CustomerID
    JOIN OrderDetails od 
        ON o.OrderID = od.OrderID
    JOIN Products p 
        ON od.ProductID = p.ProductID
    WHERE o.OrderDate >= ADD_MONTHS(SYSDATE, -6)   -- last 6 months
    GROUP BY c.CustomerID, c.FullName
    HAVING COUNT(DISTINCT p.CategoryID) >= 3       -- at least 3 categories
    ORDER BY TotalSpent DESC
)
WHERE ROWNUM <= 3;  -- Top 3 customers

CREATE INDEX idx_orders_orderdate ON Orders(OrderDate);

CREATE INDEX idx_products_categoryid ON Products(CategoryID);



CREATE TABLE Department (
    dept_id INT PRIMARY KEY,
    dept_name VARCHAR2(50) NOT NULL
);

CREATE TABLE Employee (
    emp_id INT PRIMARY KEY,
    name VARCHAR2(50),
    designation VARCHAR2(50),
    salary NUMBER(10,2),
    dept_id INT,
    date_of_joining DATE,
    tax_id VARCHAR2(20),
    is_active CHAR(1) DEFAULT 'Y',  -- soft delete flag
    CONSTRAINT fk_dept FOREIGN KEY (dept_id) REFERENCES Department(dept_id)
);

CREATE TABLE SalaryHistory (
    hist_id INT PRIMARY KEY,
    emp_id INT,
    old_salary NUMBER(10,2),
    new_salary NUMBER(10,2),
    change_date DATE DEFAULT SYSDATE,
    CONSTRAINT fk_emp FOREIGN KEY (emp_id) REFERENCES Employee(emp_id)
);

drop table student;

CREATE TABLE Book (
    book_id INT PRIMARY KEY,
    title VARCHAR2(100),
    author VARCHAR2(50),
    total_copies INT,
    available_copies INT
);

CREATE TABLE Student (
    student_id INT PRIMARY KEY,
    name VARCHAR2(50),
    branch VARCHAR2(50)
);

CREATE TABLE Issue (
    issue_id INT PRIMARY KEY,
    student_id INT,
    book_id INT,
    issue_date DATE,
    return_date DATE,
    CONSTRAINT fk_student FOREIGN KEY (student_id) REFERENCES Student(student_id),
    CONSTRAINT fk_book FOREIGN KEY (book_id) REFERENCES Book(book_id)
);

CREATE TABLE IssueHistory (
    hist_id INT PRIMARY KEY,
    issue_id INT,
    old_return_date DATE,
    new_return_date DATE,
    change_date DATE DEFAULT SYSDATE,
    CONSTRAINT fk_issue FOREIGN KEY (issue_id) REFERENCES Issue(issue_id)
);


