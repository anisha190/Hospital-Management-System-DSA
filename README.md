# Hospital Management System (DSA Implementation)

A console-based Java application demonstrating practical implementations of core Data Structures and Algorithms (DSA) within a real-world Hospital Management System.

## 📌 Project Overview

This project simulates daily operations in a medical center—including patient admissions, doctor rosters, appointment scheduling, and billing queues—while displaying real-time algorithm metrics, time complexities, and comparison counts.

## 🛠️ Data Structures & Algorithms Applied

* **LinkedList:** Used for dynamic storage and management of patient and doctor records (`patients`, `doctors`, `appointments`)[cite: 6].
* **PriorityQueue:** Manages scheduled appointments prioritized by date and time (`priorityAppointments`)[cite: 6].
* **FIFO Queue:** Handles billing workflows (`billingQueue`), processing invoices sequentially as patients check out[cite: 6].
* **Linear Search Algorithm:** Locates patients by unique ID or Name, reporting total comparisons made and $O(n)$ complexity[cite: 6].
* **Bubble Sort Algorithm:** Sorts patient records alphabetically by name, displaying total comparisons, swaps, and $O(n^2)$ time complexity[cite: 6].
* **Object-Oriented Programming (OOP):** Utilizes abstract base classes (`Person`), inheritance (`Patient`, `Doctor`), encapsulation, and custom interfaces (`Comparable<Appointment>`)[cite: 6].

---

## ⚡ Core Features

1. **Patient & Doctor Roster Management:** Add, discharge, search, and sort patient records[cite: 6].
2. **Appointment Scheduling:** Book appointments and retrieve upcoming slots using priority queue ordering[cite: 6].
3. **Queue-Based Billing:** Enqueue invoices upon service completion and dequeue them during payment processing[cite: 6].
4. **Algorithmic Statistics:** Outputs comparison numbers, swap counters, and computational complexity metrics directly in the terminal[cite: 6].

---

## 🚀 How to Run

1. **Compile the code:**
   ```bash
   javac HospitalManagementSystemDSA.java
