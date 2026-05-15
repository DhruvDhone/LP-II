const express = require("express");
const mongoose = require("mongoose");
const path = require("path");

const app = express();
app.use(express.json());
app.use(express.static(path.join(__dirname, "public")));

const MONGO_URL = process.env.MONGO_URL || "mongodb://127.0.0.1:27017/ecommerceDB";

mongoose.connect(MONGO_URL)
.then(() => console.log("MongoDB connected successfully"))
.catch(err => console.log("MongoDB connection error:", err));

const productSchema = new mongoose.Schema({
    name: String,
    price: Number,
    category: String,
    image: String,
    description: String
});

const orderSchema = new mongoose.Schema({
    customerName: String,
    customerEmail: String,
    items: Array,
    totalAmount: Number,
    orderDate: {
        type: Date,
        default: Date.now
    }
});

const Product = mongoose.model("Product", productSchema);
const Order = mongoose.model("Order", orderSchema);

async function seedProducts() {
    const count = await Product.countDocuments();

    if (count === 0) {
        await Product.insertMany([
            {
                name: "Wireless Headphones",
                price: 1499,
                category: "Electronics",
                image: "https://images.unsplash.com/photo-1505740420928-5e560c06d30e?w=500",
                description: "High quality wireless headphones with clear sound."
            },
            {
                name: "Smart Watch",
                price: 2499,
                category: "Electronics",
                image: "https://images.unsplash.com/photo-1523275335684-37898b6baf30?w=500",
                description: "Smart watch with fitness tracking and notifications."
            },
            {
                name: "Running Shoes",
                price: 1999,
                category: "Fashion",
                image: "https://images.unsplash.com/photo-1542291026-7eec264c27ff?w=500",
                description: "Comfortable running shoes for daily use."
            },
            {
                name: "Backpack",
                price: 999,
                category: "Accessories",
                image: "https://images.unsplash.com/photo-1553062407-98eeb64c6a62?w=500",
                description: "Stylish and durable backpack for students."
            },
            {
                name: "Bluetooth Speaker",
                price: 1799,
                category: "Electronics",
                image: "https://images.unsplash.com/photo-1608043152269-423dbba4e7e1?w=500",
                description: "Portable Bluetooth speaker with powerful bass."
            },
            {
                name: "Cotton T-Shirt",
                price: 499,
                category: "Fashion",
                image: "https://images.unsplash.com/photo-1521572163474-6864f9cf17ab?w=500",
                description: "Soft cotton t-shirt available in multiple colors."
            }
        ]);

        console.log("Sample products inserted");
    }
}

seedProducts();

app.get("/api/products", async (req, res) => {
    try {
        const products = await Product.find();
        res.json(products);
    } catch (error) {
        res.status(500).json({ message: "Error fetching products" });
    }
});

app.post("/api/orders", async (req, res) => {
    try {
        const { customerName, customerEmail, items, totalAmount } = req.body;

        if (!customerName || !customerEmail || !items || items.length === 0) {
            return res.status(400).json({ message: "Invalid order details" });
        }

        const order = new Order({
            customerName,
            customerEmail,
            items,
            totalAmount
        });

        await order.save();

        res.json({
            message: "Order placed successfully",
            orderId: order._id
        });
    } catch (error) {
        res.status(500).json({ message: "Error placing order" });
    }
});

app.get("/api/orders", async (req, res) => {
    try {
        const orders = await Order.find().sort({ orderDate: -1 });
        res.json(orders);
    } catch (error) {
        res.status(500).json({ message: "Error fetching orders" });
    }
});

app.get("*", (req, res) => {
    res.sendFile(path.join(__dirname, "public", "index.html"));
});

const PORT = process.env.PORT || 5000;

app.listen(PORT, "0.0.0.0", () => {
    console.log("Server running on port " + PORT);
});
