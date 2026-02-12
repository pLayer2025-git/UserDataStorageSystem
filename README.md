# User Data Storage System in Java (CSV Format)

A **console-based Java application** to store and manage user personal details (name, email, phone number) in a **CSV file**.  
This project demonstrates **Java File I/O**, **data validation**, **exception handling**, and **resource management**.

---

## **Features**

- Store user data in **CSV format** for easy access in Excel or other programs.
- Validate **email** format and **phone number** before saving.
- Append new records without overwriting existing data.
- Read and display all stored records in a **formatted console output**.
- Handle scenarios where the file does not exist by creating it dynamically.
- Proper **exception handling** for I/O failures and permission issues.
- Automatic resource management using **try-with-resources**.

---

## **CSV File Structure**

```csv
Name,Email,Phone
Mohan,mohan@example.com,9876543210
Aman,aman@gmail.com,9123456780
