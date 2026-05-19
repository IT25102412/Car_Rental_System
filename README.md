# 🚗 DriveEase - Car Rental System

A web-based Car Rental Management System developed as a group project for **SE1020 - Object Oriented Programming** module at SLIIT.

---

## 👥 Group Members & Modules

| Member | Student ID | Module |
|--------|-----------|--------|
| Member 01 | IT25102412 | Vehicle Inventory Management |
| Member 02 | IT25103032 | User & Driver Management |
| Member 03 | IT25102317 | Payment & Billing Management |
| Member 04 | IT25101998 | Booking & Reservation Management |
| Member 05 | IT25101250 | Rental Return & Damage Reports |
| Member 06 | IT25102732 | Insurance & Policy Management |

---

## 🛠️ Technologies Used

- **Backend:** Java, Spring Boot
- **Frontend:** HTML, CSS, Bootstrap 5, Thymeleaf
- **Data Storage:** Text files (.txt)
- **Tools:** IntelliJ IDEA, Git, GitHub

---

## 🎯 OOP Concepts Implemented

- **Encapsulation** — All model classes use private fields with getters/setters
- **Inheritance** — ElectricCar, PetrolCar extend Vehicle; CorporateClient, IndividualDriver extend User; BasicInsurance, FullCoverage extend Insurance
- **Polymorphism** — calculateFee() in Payment, calculateFine() in ReturnReport
- **Abstraction** — isAvailable() in Booking hides availability logic

---

## 📁 Project Structure

src/main/java/com/example/demo/
├── controller/     # HTTP request handlers
├── model/          # OOP class definitions
├── service/        # Business logic & file CRUD
└── util/           # Shared FileUtil helper
src/main/resources/
├── templates/      # Thymeleaf HTML pages
└── application.properties
data/               # Text file database
├── cars.txt
├── users.txt
├── bookings.txt
├── payments.txt
├── returns.txt
└── insurance.txt

---

## 🚀 How to Run

1. Clone the repository
2. Open in IntelliJ IDEA
3. Make sure JDK 17 is installed
4. Run `DemoApplication.java`
5. Open browser and go to `http://localhost:8080/login`

---

## 🔑 Login Credentials

| Role | Username | Password |
|------|----------|----------|
| Admin | admin | admin123 |
| Customer | Register via signup page | Your password |

---

## ✨ Features

- ✅ Admin dashboard with 6 management modules
- ✅ Customer portal with car browsing and booking
- ✅ Admin booking approval system
- ✅ Demo payment gateway
- ✅ Forgot password with security question
- ✅ User registration with NIC/Passport
