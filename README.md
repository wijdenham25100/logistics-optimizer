# Logistics Optimizer 🚚

A Java CLI tool that calculates shortest delivery routes between depots using **Dijkstra's algorithm**.

Built as a portfolio project demonstrating graph data structures, algorithm implementation, and OOP design in Java.

---

## Network Overview
Berlin ──289km── Hamburg
│                │
191km            235km
│                │
Leipzig        Dortmund
│                │
395km            236km
│                │
└──── Frankfurt ─┘
│
393km
│
München
## Features

- Graph-based depot network with weighted edges
- Dijkstra's shortest path algorithm (min-heap / priority queue)
- CLI input: choose any start depot as argument
- Visual distance output with bar chart

---

## Getting Started

**Requirements:** Java 17+

```bash
# Clone
git clone https://github.com/YOUR_USERNAME/logistics-optimizer.git
cd logistics-optimizer

# Compile
javac -d out src/main/java/logistics/*.java

# Run (default start: Dortmund)
java -cp out logistics.Main

# Run with custom start depot
java -cp out logistics.Main Berlin
```
## Example Output

## Tech Stack
Berlin           0 km
Leipzig        191 km  ██████
Hamburg        289 km  █████████
Frankfurt      586 km  ███████████████████
Dortmund       524 km  █████████████████
München        979 km  ████████████████████████████████

## Tech Stack
- Java 21 (OpenJDK)
- Data structure: Adjacency List (`HashMap`)
- Algorithm: Dijkstra with `PriorityQueue`
- No external dependencies

## Project Structure
logistics-optimizer/
├── src/main/java/logistics/
│   ├── Graph.java        # Adjacency list graph
│   ├── Dijkstra.java     # Shortest path algorithm
│   └── Main.java         # CLI entry point + sample network
├── out/                  # Compiled classes
└── README.md

---

*Developed as part of a Java portfolio for software engineering roles.*