const express = require("express");
const cors = require("cors");
const sqlite3 = require("sqlite3").verbose();

const app = express();
const PORT = 5000;

app.use(cors());
app.use(express.json());

const db = new sqlite3.Database("./students.db");

db.run(`
CREATE TABLE IF NOT EXISTS students (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    roll_no TEXT NOT NULL,
    department TEXT NOT NULL,
    email TEXT NOT NULL,
    phone TEXT NOT NULL
)
`);

app.get("/", (req, res) => {
    res.send("Student Record Management Backend API is running");
});

app.get("/students", (req, res) => {
    db.all("SELECT * FROM students", [], (err, rows) => {
        if (err) {
            res.status(500).json({ error: err.message });
        } else {
            res.json(rows);
        }
    });
});

app.post("/students", (req, res) => {
    const { name, roll_no, department, email, phone } = req.body;

    db.run(
        "INSERT INTO students (name, roll_no, department, email, phone) VALUES (?, ?, ?, ?, ?)",
        [name, roll_no, department, email, phone],
        function (err) {
            if (err) {
                res.status(500).json({ error: err.message });
            } else {
                res.json({ message: "Student added successfully", id: this.lastID });
            }
        }
    );
});

app.put("/students/:id", (req, res) => {
    const { name, roll_no, department, email, phone } = req.body;
    const id = req.params.id;

    db.run(
        "UPDATE students SET name=?, roll_no=?, department=?, email=?, phone=? WHERE id=?",
        [name, roll_no, department, email, phone, id],
        function (err) {
            if (err) {
                res.status(500).json({ error: err.message });
            } else {
                res.json({ message: "Student updated successfully" });
            }
        }
    );
});

app.delete("/students/:id", (req, res) => {
    const id = req.params.id;

    db.run("DELETE FROM students WHERE id=?", [id], function (err) {
        if (err) {
            res.status(500).json({ error: err.message });
        } else {
            res.json({ message: "Student deleted successfully" });
        }
    });
});

app.listen(PORT, "0.0.0.0", () => {
    console.log("Server running on port " + PORT);
});
