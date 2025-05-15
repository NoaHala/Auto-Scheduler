# Auto-Scheduler - a Driving Instructor Schedule Builder

This project was created to help my father, a driving instructor, easily build his weekly work schedule with minimal effort.

Although still in early development, the core system is already up and running, handling key real-life constraints with a clean algorithmic approach.


## What It Does

Given a list of students and their availability, the system builds a schedule that:

✔️ Assigns each student up to **2 lessons per week**  
✔️ Makes sure **no student gets more than 1 lesson per day**  
✔️ **Maximizes** the number of lessons overall  
✔️ Encourages **fairness** — better to give everyone something than a few everything  
✔️ Works with **custom time windows** for each student


## How It Works

The problem is modeled as a **min-cost network**, using **Google OR-Tools** in Java.  
I also added a **virtual sink node** to allow flexible total flow — so the system finds the optimal number of lessons automatically, instead of fixing it in advance.

---

Stay tuned for positive updates and improvements! 😊
