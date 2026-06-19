const API_BASE = "http://localhost:8080/api";
let cart = [];

async function checkHealth() {
    const result = document.getElementById("healthResult");

    try {
        const res = await fetch(`${API_BASE}/health`);
        result.innerText = await res.text();
    } catch (error) {
        result.innerText = "Backend not connected";
    }
}

async function loadFoods() {
    const container = document.getElementById("foodList");
    container.innerHTML = "Loading menu...";

    try {
        const res = await fetch(`${API_BASE}/foods`);  // ✅ Fixed: was /food
        const foods = await res.json();
        renderFoods(foods);
    } catch (error) {
        container.innerHTML = "Failed to load foods";
    }
}

function renderFoods(foods) {
    const container = document.getElementById("foodList");

    container.innerHTML = foods.map(food => `
        <div class="card">
            <img src="${food.image}" alt="${food.foodName}">
            <div class="card-body">
                <h3>${food.foodName}</h3>
                <p>${food.type}</p>
                <h3>₹${food.foodPrice}</h3>
                <button onclick='addToCart(${JSON.stringify(food)})'>Add to Cart</button>
            </div>
        </div>
    `).join("");
}

async function searchFoods() {
    const query = document.getElementById("searchInput").value;
    const res = await fetch(`${API_BASE}/foods/search?keyword=${encodeURIComponent(query)}`);  // ✅ Fixed: was /food/search
    const foods = await res.json();
    renderFoods(foods);
}

function addToCart(food) {
    cart.push({ ...food, quantity: 1 });
    renderCart();
}

function removeFromCart(id) {
    cart = cart.filter(item => item.foodId !== id);
    renderCart();
}

function renderCart() {
    const cartItems = document.getElementById("cartItems");
    const totalElement = document.getElementById("cartTotal");

    cartItems.innerHTML = cart.map(item => `
        <div class="order">
            <b>${item.foodName}</b> x ${item.quantity}
            <p>₹${item.foodPrice * item.quantity}</p>
            <button onclick="removeFromCart(${item.foodId})">Remove</button>
        </div>
    `).join("");

    const total = cart.reduce((sum, item) => sum + item.foodPrice * item.quantity, 0);
    totalElement.innerText = `Total: ₹${total}`;
}

async function placeOrder() {
    const customerName = document.getElementById("customerName").value;

    const foodName = cart.map(item => item.foodName).join(", ");
    const quantity = cart.length;
    const totalPrice = cart.reduce((sum, item) => sum + item.foodPrice, 0);

    const order = {
        name: customerName,
        itemName: foodName,
        qty: quantity,
        amount: totalPrice,
        status: "PLACED"
    };

    const res = await fetch(`${API_BASE}/orders`, {  // ✅ Fixed: was /order
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(order)
    });

    if (!res.ok) {
        alert(await res.text());
        return;
    }

    alert("Order placed successfully");
    cart = [];
    renderCart();
    loadOrders();
}

async function loadOrders() {
    const container = document.getElementById("orderList");
    const res = await fetch(`${API_BASE}/orders`);  // ✅ Fixed: was /order
    const orders = await res.json();

    container.innerHTML = orders.map(order => `
        <div class="order">
            <h3>Order #${order.id}</h3>
            <p><b>Name:</b> ${order.name}</p>
            <p><b>Food:</b> ${order.itemName}</p>
            <p><b>Total:</b> ₹${order.amount}</p>
            <p><b>Status:</b> ${order.status}</p>
        </div>
    `).join("");
}

loadFoods();
renderCart();
loadOrders();