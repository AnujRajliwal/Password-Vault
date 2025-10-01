🔐 Secure Password Vault (Java Swing + Cryptography)
📌 Project Overview
-->The Secure Password Vault is a Java-based desktop application that allows users to:
-->Create an account (sign up with username and password).
-->Log in securely.
-->Save and view platform-specific usernames and passwords.
-->Store data permanently using a database.
-->Ensure security with Java Cryptography (AES encryption).
This project demonstrates Java Swing GUI, JDBC Database connectivity, and Cryptography concepts in one application.

🚀 Features
✅ Login & Signup System – user authentication with username & password.
✅ Password Manager – save platform credentials (e.g., Gmail, Facebook, LinkedIn).
✅ Secure Storage – passwords are encrypted before being stored.
✅ Java Swing UI – user-friendly graphical interface.
✅ Database Integration – credentials persist even after application restart.

🛠️ Technologies Used
-->Java SE 8+
-->Swing (GUI framework)
-->JDBC (Database Connectivity)
-->SQLite / MySQL (for storing users & passwords)
-->AES Encryption (Java Cryptography API)

📂 Project Structure
 /SecurePasswordVault
│── src/
│   ├── prj/
│   │   ├── PasswordVault.java     # Main Application with GUI
│   │   ├── DatabaseManager.java   # Handles DB operations (CRUD)
│   │   ├── CryptoUtils.java       # AES Encryption/Decryption
│── database/
│   ├── vault.db                   # SQLite Database file
│── README.md

⚡ How It Works
  1.User signs up with username & password.
  2.On login, credentials are validated with database.
  3.User can add a platform name, username, and password.
  4.The password is encrypted using AES before being saved in database.
  5.User can click Show to view saved credentials.


  ▶️ How to Run
  -->Clone this repository:
        git clone  https://github.com/AnujRajliwal/Password-Vault.git
-->Open in Eclipse or VS Code.
-->Ensure SQLite/MySQL JDBC Driver is added to your project.
-->Run the PasswordVault.java file.

📷 Screenshots
<img width="480" height="539" alt="image" src="https://github.com/user-attachments/assets/3b6871e1-a36c-4556-b878-aa2dd8725af8" />
<img width="356" height="237" alt="Screenshot 2025-10-01 161017" src="https://github.com/user-attachments/assets/63418d47-51e7-40f4-bb0f-9e36e982dda7" />
<img width="352" height="236" alt="Screenshot 2025-10-01 161046" src="https://github.com/user-attachments/assets/76e90b4a-73df-411e-be2c-d8225c355989" />
<img width="475" height="547" alt="Screenshot 2025-10-01 161128" src="https://github.com/user-attachments/assets/ded8eee0-72a5-4d68-97b0-59b03a2c2389" />



