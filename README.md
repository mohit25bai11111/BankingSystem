# 🏦 Java CLI Bank Management System

> **An automated, lightweight, terminal-driven banking application built with Core Java and persistent CSV storage.**

---

## 📖 Project Overview

Managing basic account operations shouldn't require heavy database infrastructure or complex Graphical User Interfaces (GUIs). This project provides a pure Java Command-Line Interface (CLI) application that allows users to create accounts, deposit and withdraw funds, check balances, and persist all transactional data locally across sessions.

The system is optimized for automated headless evaluation environments and zero-dependency terminal execution.

---

## 🎯 Problem Statement

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
