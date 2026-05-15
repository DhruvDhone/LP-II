const express = require("express");
const mongoose = require("mongoose");
const cors = require("cors");
const dotenv = require("dotenv");

dotenv.config();

const app = express();

app.use(cors());
app.use(express.json());

app.use("/api/posts", require("./routes/posts"));

app.get("/", (req, res) => {
  res.send("Blog backend is running successfully");
});

mongoose
  .connect(process.env.MONGO_URI)
  .then(() => {
    console.log("MongoDB connected successfully");

    app.listen(process.env.PORT, "0.0.0.0", () => {
      console.log("Server running on port " + process.env.PORT);
    });
  })
  .catch((error) => {
    console.log("MongoDB connection error:", error);
  });
