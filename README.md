# Auto-Scheduler - a Driving Instructor Schedule Builder

This project was created to help my father, a driving instructor, easily build his weekly work schedule with minimal effort.

Although still in early development, the core system is already up and running, handling key real-life constraints with a clean algorithmic approach.


## What It Does

Given a list of students and their availability, the system builds a schedule that:

✔️ Assigns each student up to **2 lessons per week**  
✔️ Ensures **no student gets more than 1 lesson per day**  
✔️ **Maximizes** the total number of lessons  
✔️ Encourages **fairness** — it's better to give everyone something than to give a few everything 
✔️ Supports **custom time windows** for each student


## How It Works

The problem is modeled as a **min-cost network**, using **Google OR-Tools** in Java.  
I also added a **virtual sink node** to allow flexible total flow — so the system can find the optimal number of lessons automatically, without having to predefine it.

---

Stay tuned for positive updates and improvements! 😊
