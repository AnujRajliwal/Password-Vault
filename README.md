# Password Vault

**Password Vault** is a simple and secure Java application for storing and managing your passwords locally. Passwords are encrypted before being stored, providing a safe way to keep sensitive credentials.

---

## Table of Contents

- [Features](#features)  
- [Architecture](#architecture)  
- [Getting Started](#getting-started)  
  - [Prerequisites](#prerequisites)  
  - [Installation & Build](#installation--build)  
  - [Running the Application](#running-the-application)  
- [Usage](#usage)  
- [Security Details](#security-details)  
- [Future Improvements](#future-improvements)  
- [Contributing](#contributing)  
- [License](#license)

---

## Features

- Add, view, edit, and delete password entries  
- GUI-based interface for ease of use  
- Encryption of stored passwords using secure algorithms  
- Local persistence of encrypted data (in a file or database)  
- Master password protection (optional)  

---

## Architecture

The project is organized in the following packages:

- **Algorithms** — contains encryption/decryption logic  
- **Controller** — handles user input, application logic, and coordination  
- **Database** — manages storage (saving, loading) of encrypted data  
- **GUI** — graphical user interface for interacting with the vault  

---

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 11+ (or version you used)  
- Maven or Gradle (if you use a build tool)  
- (Optional) Git to clone the repository

### Installation & Build

1. Clone the repository:

   ```bash
   git clone https://github.com/AnujRajliwal/Password-Vault.git
   cd Password-Vault
2. Build the project:
        If using Maven:
                 mvn clean install
        If using Gradle:
                 gradle build
3. Generate or locate the executable / jar (if it's packaged). For example, if you created a jar, it may be under target/ or build/libs/.


##Usage
  1.Launch the application.
  2.(Optional) Set or enter a master password.
  3.Use the GUI to add new entries:
     .Provide a name/label (e.g. "Gmail", "Bank")
     .Provide the password or secret data
     .Save it securely — it will be encrypted and stored
  4.To view or copy a password:
     .Select the entry in the list
     .Decrypt and display or copy the plaintext
  5.To remove an entry, use the delete option in the GUI.
  6.To change your master password (if supported), follow the GUI instructions (or re-encrypt your vault).


##Security Details
  1.Encryption Algorithm: The project uses [describe algorithm — e.g. AES-128 or AES-256] for encryption and decryption.
  2.Key Management: The master password (or other secret) is used to derive the encryption key.
  3.Storage: Passwords are stored in an encrypted form in a local file or simple database.
  4.Best Practices:
     .Do not store your master password in plaintext.
     .Back up your encrypted vault file — losing it may mean losing access to your stored passwords.
     .Consider securing your vault file (e.g. with OS-level file permissions).

<img width="459" height="314" alt="Screenshot 2025-11-18 232212" src="https://github.com/user-attachments/assets/ee2a435f-e6e3-419a-9763-2e3fd85ea28c" />
