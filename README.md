# 🏦 Bank Management System (Java CLI)

**An Automated, Zero-Dependency Banking System with CSV Data Persistence**

![Java](https://img.shields.io/badge/Java-17%2B-orange) ![Interface](https://img.shields.io/badge/Interface-CLI-blue) ![Project](https://img.shields.io/badge/Project-VITyarthi--Evaluation-purple) ![Persistence](https://img.shields.io/badge/Storage-CSV-green)

---

# 👤 Project & Author Details

| Detail | Information |
| :--- | :--- |
| **Project Name** | Bank Management System (Java CLI) |
| **Lead Developer** | Mohit Pillai |
| **Registration Number** | 25BAI11111 |
| **Course Code** | CSE2006 — Programming in Java |
| **Semester** | Fall Semester 2026–27 |
| **Institution** | VIT -VITyarthi Programme |
| **Project Category** | Command-Line Interface (CLI) Application |
| **Submission Type** | Flipped Course Evaluation |

---

# 📖 Project Overview

Managing basic account operations shouldn't require heavy database infrastructure or complex Graphical User Interfaces (GUIs). This project provides a pure Java Command-Line Interface (CLI) application that allows users to create accounts, deposit and withdraw funds, check balances, and persist all transactional data locally across sessions.

The system is optimized for automated headless evaluation environments and zero-dependency terminal execution.

---

# 🎯 Problem Statement

Traditional application evaluation environments often face:

- ❌ Display driver crashes due to unneeded GUI dependencies (Swing/JavaFX)
- ❌ Data loss upon application exit due to lack of local persistence
- ❌ Heavy configuration overhead with external SQL servers
- ❌ Complex setup requirements during automated scoring

Our solution provides a zero-dependency, pure Core Java CLI application with built-in file serialization.

---

# ✨ Features

### 📄 Account Management
- Create new bank accounts with custom account numbers
- Assign account holder names
- Initialize starting account balances

### 💰 Financial Transactions
- Real-time money deposit handling
- Balance validation during withdrawals
- Instant account balance inquiry

### 💾 File Persistence Engine
- Automated loading of records on system launch
- Auto-saving account details into `accounts.csv`
- Error-resilient file parsing (`toCSV` / `fromCSV`)

### 🛡️ Robust CLI Engine
- Input sanitization to prevent non-numeric crashes
- Infinite interactive command loop
- Graceful shutdown and data flush on exit

---

# 🏗️ System Architecture

```text
                  Terminal Input (Scanner)
                             │
                             ▼
                        Main.java
                  (CLI Menu Controller)
                             │
                             ▼
                     BankService.java
                 (Business Logic Engine)
                  ┌──────────┴──────────┐
                  ▼                     ▼
             Account.java         accounts.csv
             (Data Model)     (Persistent Storage)
```

---

# 🛠️ Tech Stack

| Category | Technology |
|-----------|------------|
| Language | Core Java (JDK 17+) |
| Storage | CSV File Persistence |
| Interface | Command Line Interface (CLI) |
| Architecture | Object-Oriented Programming (OOP) |
| Version Control | Git & GitHub |

---

# 📂 Project Structure

```text
BankingSystem/
│
├── Account.java        # Account model & CSV serializer
├── BankService.java    # Business logic & file persistence
├── Main.java           # CLI menu execution engine
├── accounts.csv        # Persistent storage file
└── README.md           # Project documentation
```

---

# 🔄 Project Workflow

```text
System Launched via Terminal
            │
            ▼
BankService loads existing data from accounts.csv
            │
            ▼
Interactive Menu Presented (Options 1-5)
            │
            ▼
User Performs Transaction (Deposit / Withdraw / Balance)
            │
            ▼
In-Memory Account State Updated
            │
            ▼
Data Serialized & Written to accounts.csv
            │
            ▼
Application Exits Gracefully
```

---

# 🚀 Build & Execution Instructions

| Step | Command | Description |
|------|---------|-------------|
| 1. Clone Repo | `git clone https://github.com/mohit25bai11111/BankingSystem.git` | Downloads repository |
| 2. Navigate | `cd BankingSystem` | Opens project directory |
| 3. Compile | `javac *.java` | Compiles all source files |
| 4. Execute | `java Main` | Launches the CLI application |

---

# 🚀 Future Enhancements

- 🔒 PIN Authentication for secure account access
- 💸 Inter-account fund transfer capabilities
- 📜 Timestamped audit log exports (`audit.log`)
- 🗄️ Embedded SQLite database integration

---

# 📜 License & Context

This project is developed for academic evaluation under the **VITyarthi Flipped Course Evaluation System**.

---

# ⭐ Project Status

✅ **Completed & Verified**

Version: **v1.0.0**

---

## 💡 Goal

> **"Build a robust, zero-dependency Java CLI banking system with automatic file persistence tailored for headless execution."**

## 💡 Goal

> **"Build a robust, zero-dependency Java CLI banking system with automatic file persistence tailored for headless execution."**
