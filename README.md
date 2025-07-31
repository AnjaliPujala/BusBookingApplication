# 🚌 Bus Booking Application

This application is built to understand and implement key software engineering concepts such as **Multithreading**, **Concurrency and Synchronization**, **Object-Oriented Programming (OOP)**, **Basic System Design**, and **Continuous Integration using GitHub**. It's a Java console-based simulation of a bus seat reservation system.

---

## 🎯 Objectives

- Practice Java **multithreading** and ensure thread safety using synchronization.
- Apply **object-oriented design principles** to structure real-world entities.
- Understand **system modeling** using abstraction, inheritance, and encapsulation.
- Gain hands-on experience with **CI/CD workflows** using GitHub.
- Build a clean and extendable base for future upgrades like GUIs or web integration.

---

## 📚 Concepts Covered

- ✅ Thread lifecycle and concurrent execution
- ✅ `synchronized` blocks for thread-safe booking
- ✅ OOP with classes for Bus, Seat, User, Routes
- ✅ Java collections (Map, List)
- ✅ Layered design separation
- ✅ CI support using GitHub Actions (can be added optionally)

---

## 🛠️ Tech Stack

- **Language:** Java
- **Paradigms:** Object-Oriented, Multithreaded
- **Tools:** Java Collections, Threads
- **Platform:** GitHub for version control and CI

---

## 🔍 Project Overview

The system allows users to **book seats on buses for different routes**. Users act as threads trying to reserve a specific seat concurrently. The system ensures thread-safe booking by synchronizing access to seat resources.

---

## 📦 Class Breakdown

### 🔹 `Main.java`
- Entry point of the application
- Creates buses, routes, and users (threads)
- Starts threads for booking seats

### 🔹 `BusBookingApplication.java`
- Acts as the central manager
- Adds, removes, and fetches route-specific buses

### 🔹 `SourceToDestination.java` (Abstract)
- Parent class for each specific route
- Contains booking logic using synchronization

### 🔹 Subclasses (HyderabadToBhimavaram, etc.)
- Represent different bus routes
- Inherit from `SourceToDestination`

### 🔹 `Bus.java`
- Contains bus details and 32 seats
- Manages seat creation and assignment

### 🔹 `Seat.java`
- Each seat has a seat number and booking status
- Booking status updated during reservation

### 🔹 `User.java`
- Implements `Runnable`
- Represents a user attempting to book a seat

---

## 💻 How It Works (Flow)

1. Three buses are created for three different routes.
2. Each bus is initialized with 32 unbooked seats.
3. The routes are added to the application using keys (e.g., `"banglore_hyderabad"`).
4. 12 users are created, each trying to book a specific seat on a route.
5. Each user runs on a separate thread.
6. Booking is synchronized at the `Seat` level to ensure no double bookings.

---

## 🧪 Sample Output

```bash
Bus from banglore to hyderabad added into application!
Bus from hyderabad to bhimavaram added into application!
Bus from bhimavaram to kanigiri added into application!
Bus from banglore to hyderabad booked for Anjali, Seat number 10
Oops..! Rani, from banglore to hyderabad, Seat number 10 is already booked
```
---

## 🔐 Concurrency Handling
Concurrency is handled using:

```bash
synchronized(seat) {
    if (!seat.booking_status) {
        seat.booking_status = true;
        return true;
    }
}
```
This guarantees that only one thread can book a particular seat at a time.

---

## 🚀 Features
- ✅ Add and remove buses by route
- ✅ Thread-safe booking with seat-level locking
- ✅ Multiple users booking seats simultaneously
- ✅ Console-based output for success/failure feedback
