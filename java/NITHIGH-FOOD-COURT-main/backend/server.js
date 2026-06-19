// Simple Node.js backend replacing Spring Boot for quick testing
const http = require("http");

// In-memory data store
let foods = [
  { id: 1, foodId: 1, foodName: "Cheese Burger",  type: "Fast Food",    foodPrice: 129, image: "https://images.unsplash.com/photo-1568901346375-23c9450c58cd?w=600", available: true },
  { id: 2, foodId: 2, foodName: "Veg Pizza",       type: "Pizza",        foodPrice: 199, image: "https://images.unsplash.com/photo-1513104890138-7c749659a591?w=600", available: true },
  { id: 3, foodId: 3, foodName: "Masala Dosa",     type: "South Indian", foodPrice: 89,  image: "https://images.unsplash.com/photo-1668236543090-82eba5ee5976?w=600", available: true },
  { id: 4, foodId: 4, foodName: "Cold Coffee",     type: "Beverages",    foodPrice: 79,  image: "https://images.unsplash.com/photo-1461023058943-07fcbe16d735?w=600", available: true },
  { id: 5, foodId: 5, foodName: "Paneer Butter Masala", type: "North Indian", foodPrice: 159, image: "https://images.unsplash.com/photo-1565557623262-b51c2513a641?w=600", available: true },
  { id: 6, foodId: 6, foodName: "Mango Lassi",     type: "Beverages",    foodPrice: 69,  image: "https://images.unsplash.com/photo-1626200419199-391ae4be7a41?w=600", available: true },
];

let orders = [];
let orderIdCounter = 1;

function sendJSON(res, status, data) {
  res.writeHead(status, {
    "Content-Type": "application/json",
    "Access-Control-Allow-Origin": "*",
    "Access-Control-Allow-Methods": "GET, POST, OPTIONS",
    "Access-Control-Allow-Headers": "Content-Type",
  });
  res.end(JSON.stringify(data));
}

function sendText(res, status, text) {
  res.writeHead(status, {
    "Content-Type": "text/plain",
    "Access-Control-Allow-Origin": "*",
  });
  res.end(text);
}

function readBody(req) {
  return new Promise((resolve) => {
    let body = "";
    req.on("data", (chunk) => (body += chunk));
    req.on("end", () => resolve(body));
  });
}

const server = http.createServer(async (req, res) => {
  const url = new URL(req.url, `http://localhost:8080`);
  const path = url.pathname;
  const method = req.method;

  // Handle CORS preflight
  if (method === "OPTIONS") {
    res.writeHead(204, {
      "Access-Control-Allow-Origin": "*",
      "Access-Control-Allow-Methods": "GET, POST, OPTIONS",
      "Access-Control-Allow-Headers": "Content-Type",
    });
    res.end();
    return;
  }

  console.log(`${method} ${path}`);

  // GET /api/health
  if (method === "GET" && path === "/api/health") {
    return sendText(res, 200, "NitHigh Bites backend is running ✅");
  }

  // GET /api/foods
  if (method === "GET" && path === "/api/foods") {
    return sendJSON(res, 200, foods);
  }

  // GET /api/foods/search?keyword=xxx
  if (method === "GET" && path === "/api/foods/search") {
    const keyword = (url.searchParams.get("keyword") || "").toLowerCase();
    const results = foods.filter((f) =>
      f.foodName.toLowerCase().includes(keyword)
    );
    return sendJSON(res, 200, results);
  }

  // GET /api/orders
  if (method === "GET" && path === "/api/orders") {
    return sendJSON(res, 200, orders);
  }

  // POST /api/orders
  if (method === "POST" && path === "/api/orders") {
    const body = await readBody(req);
    try {
      const order = JSON.parse(body);
      if (!order.name || order.name.trim() === "") {
        return sendText(res, 400, "Customer name is required");
      }
      if (!order.itemName || order.itemName.trim() === "") {
        return sendText(res, 400, "Order must have at least one item");
      }
      const saved = { id: orderIdCounter++, ...order, status: "PLACED" };
      orders.push(saved);
      return sendJSON(res, 200, saved);
    } catch (e) {
      return sendText(res, 400, "Invalid request body");
    }
  }

  // 404
  sendText(res, 404, "Not Found");
});

const PORT = 8080;
server.listen(PORT, () => {
  console.log("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
  console.log("  NitHigh Bites Node.js Backend");
  console.log(`  Running at: http://localhost:${PORT}`);
  console.log("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
  console.log("  Endpoints:");
  console.log("  GET  /api/health");
  console.log("  GET  /api/foods");
  console.log("  GET  /api/foods/search?keyword=");
  console.log("  GET  /api/orders");
  console.log("  POST /api/orders");
  console.log("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
});
