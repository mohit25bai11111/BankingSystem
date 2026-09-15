# 🏦 Advanced Secure Bank Management System (Java CLI)

**An Enterprise-Grade, Zero-Dependency Core Java CLI Application with Cryptographic Security & Dual CSV Data Persistence**

![Java](https://img.shields.io/badge/Java-17%2B-orange?style=flat-square&logo=openjdk)
![Security](https://img.shields.io/badge/Security-SHA--256-red?style=flat-square)
![Interface](https://img.shields.io/badge/Interface-ANSI%20CLI-blue?style=flat-square)
![Storage](https://img.shields.io/badge/Storage-Dual%20CSV-green?style=flat-square)
![Evaluation](https://img.shields.io/badge/Evaluation-VITyarthi--CSE2006-purple?style=flat-square)

---

# 👤 Project & Author Details

| Detail | Information |
| :--- | :--- |
| **Project Name** | Advanced Secure Bank Management System (Java CLI) |
| **Lead Developer** | Mohit Pillai |
| **Registration Number** | 25BAI11111 |
| **Course Code** | CSE2006 — Programming in Java |
| **Semester** | Fall Semester 2026–27 |
| **Institution** | VIT - VITyarthi Programme |
| **Project Category** | Enterprise CLI Application |
| **Submission Type** | Flipped Course Evaluation |

---

# 📖 Project Overview

Managing core banking operations shouldn't require complex web frameworks or external SQL infrastructure. This project provides a robust, zero-dependency Core Java Command-Line Interface (CLI) application featuring SHA-256 PIN authentication, account auto-locking, audit trails, fund transfers, statement file exporting, and administrative financial tools.

The application is engineered specifically for automated headless evaluation environments, ensuring 100% compatibility across Windows, macOS, and Linux terminals without display driver dependencies.

---

# 🎯 Problem Statement

Standard Java academic CLI submissions often suffer from:

- ❌ Plaintext password/PIN storage vulnerability.
- ❌ Inability to track historical transactions or generate statements.
- ❌ Crash-prone console interfaces due to unhandled scanner buffer overflows.
- ❌ Loss of transactional history upon execution restart.
- ❌ Heavy configuration overhead with external database engines.

Our enterprise CLI solution solves these issues using standard Java libraries (`java.security`, `java.io`, `java.util`, `java.time`) to deliver cryptographic security, dual CSV persistence, and transactional resilience.

---

# ✨ Core Features

### 🔒 Cryptographic Security & Access Control
- **SHA-256 PIN Hashing:** Raw PINs are cryptographically hashed using `java.security.MessageDigest` before being written to storage.
- **Account Lockout Policy:** Automatically locks accounts (`isLocked = true`) after 3 consecutive failed PIN authentication attempts.

### 💸 Core Banking & Fund Transfers
- **Account Operations:** Supports standard account creation, cash deposits, cash withdrawals, and live balance inquiries.
- **Inter-Account Transfers:** Atomic money transfers between active accounts with dual-ledger transaction logging.

### 📜 Persistent Audit Ledger & Passbook Exporter
- **Transaction History Engine:** Records every creation, deposit, withdrawal, and transfer in `transactions.csv` with unique IDs and system timestamps.
- **Statement File Exporter:** Exports printable, formatted text receipts (`statement_<account_number>.txt`) directly to the file system.

### 🛡️ Admin & Manager Operations Portal
- **Liquidity Monitoring:** Real-time aggregation of total bank liquidity managed across all accounts.
- **Account Recovery:** Administrative manual unlock functionality for locked accounts.
- **Batch Interest Engine:** One-click batch credit distribution of Annual Percentage Yield (APY) interest to all active `SAVINGS` accounts.

### 📊 Financial Utilities
- **Loan EMI Calculator:** Integrated mathematical calculator projecting monthly loan repayments using the formula:

$$\text{EMI} = \frac{P \cdot r \cdot (1 + r)^n}{(1 + r)^n - 1}$$

---

# 🏗️ System Architecture

```text
                    Terminal Input (Scanner / ANSI CLI Menu)
                                       │
                                       ▼
                            Main.java (Controller)
                                       │
                                       ▼
                   BankService.java (Business Logic Engine)
                ┌──────────────────────┼──────────────────────┐
                ▼                      ▼                      ▼
           Account.java         Transaction.java       Statement Exporter
       (Model & Security)      (Audit Ledger Model)   (statement_<acc>.txt)
                │                      │
                ▼                      ▼
           accounts.csv         transactions.csv
       (Account Persistence)  (Transaction Ledger)
```

---

# 🛠️ Tech Stack

| Category | Technology | Description |
| :--- | :--- | :--- |
| **Language** | Core Java (JDK 17+) | Pure Java Standard Library |
| **Security** | SHA-256 | `java.security.MessageDigest` cryptographic hashing |
| **Storage** | Dual CSV File Storage | Custom File I/O serializers (`accounts.csv`, `transactions.csv`) |
| **Interface** | ANSI Terminal CLI | Color-coded console UI with OS-level fallback handling |
| **Architecture** | Object-Oriented Programming | Modular controller-service-model separation |
| **Version Control** | Git & GitHub | Remote synchronization & source control |

---

# 📂 Project Structure

```text
BankingSystem/
│
├── Account.java        # Account model, SHA-256 PIN hashing, auto-lock logic, CSV parser
├── Transaction.java    # Audit trail model, unique ID generation, CSV parser
├── BankService.java    # Core business logic, inter-account transfers, interest engine, file I/O
├── Main.java           # ANSI terminal interface controller, menus, EMI calculator
├── accounts.csv        # Persistent account state storage database
├── transactions.csv    # Persistent transaction audit trail database
└── README.md           # Project documentation
```

---

# 🔄 System Execution Workflow

```text
System Launched via Terminal
             │
             ▼
BankService loads accounts.csv and transactions.csv
             │
             ▼
Interactive Menu Presented (Options 1–9)
             │
             ▼
User Authenticated via SHA-256 Hash Matching
             │
             ▼
Transaction Executed (Deposit / Withdraw / Transfer / Passbook Export)
             │
             ▼
Audit Transaction Logged & In-Memory State Updated
             │
             ▼
State Serialized & Flushed to CSV Storage Files
             │
             ▼
Application Graceful Shutdown (State Preserved)
```

---

# 🚀 Build & Execution Instructions

### Option 1: Direct Terminal Execution (Standard)

| Step | Command | Description |
| :--- | :--- | :--- |
| **1. Clone Repo** | `git clone https://github.com/mohit25bai11111/BankingSystem.git` | Downloads repository source code |
| **2. Navigate** | `cd BankingSystem` | Opens project directory |
| **3. Compile** | `javac *.java` | Compiles all Java source files |
| **4. Execute** | `java Main` | Launches interactive application |

---

### Option 2: Portable JAR Packaging (Executable Package)

To build a standalone portable executable package:

```bash
# 1. Compile source files
javac *.java

# 2. Package into executable JAR file
jar cvfe BankingSystem.jar Main *.class

# 3. Launch portable JAR application
java -jar BankingSystem.jar
```

---

# 🚀 Future Enhancements

- 🗄️ **Database Migration:** Migration option for Relational Databases via JDBC (SQLite / PostgreSQL).
- 🌐 **RESTful API Interface:** Embedded lightweight HTTP server wrapper using `com.sun.net.httpserver`.
- 💱 **Multi-Currency Engine:** Live foreign currency exchange rate calculator for cross-border account transfers.

---

# 📜 License & Context

This project is developed for academic evaluation under the **VITyarthi Flipped Course Evaluation System** at **VIT**.

---

# ⭐ Project Status

✅ **Completed & Enterprise Verified**  
Version: **v2.0.0 (Enterprise CLI Edition)**

> **"Build a robust, zero-dependency Java CLI banking system with automatic file persistence tailored for headless execution."**

## 💡 Goal

> **"Build a robust, zero-dependency Java CLI banking system with automatic file persistence tailored for headless execution."**
