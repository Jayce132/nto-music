// script.js

document.getElementById('viewProducts').addEventListener('click', viewProducts);

function viewProducts() {
    fetch('http://localhost:8080/products')
        .then(response => response.json())
        .then(products => {
            const productList = document.getElementById('productList');
            productList.innerHTML = '';
            products.forEach(product => {
                const productDiv = document.createElement('div');
                productDiv.classList.add('product');
                productDiv.innerHTML = `
                    <strong>${product.name}</strong> - Price: ${product.price}
                    <button onclick="addToCart(${product.id})">Add to Cart</button>
                    <button onclick="editProduct(${product.id})">Edit</button>
                `;
                productList.appendChild(productDiv);
            });
        });
}

function editProduct(productId) {
    const newName = prompt('Enter new product name:', '');
    const newPrice = prompt('Enter new product price:', '');

    if (newName && !isNaN(parseFloat(newPrice))) {
        const updatedProduct = {
            name: newName, price: parseFloat(newPrice)
        };

        fetch(`http://localhost:8080/products/${productId}`, {
            method: 'PUT', headers: {
                'Content-Type': 'application/json'
            }, body: JSON.stringify(updatedProduct)
        })
            .then(response => {
                if (response.status === 200) {
                    alert('Product updated successfully.');
                    viewProducts(); // Refresh the product list
                    viewCart(); // Refresh the cart
                } else {
                    alert('Failed to update the product.');
                }
            })
            .catch(error => {
                console.error('Error updating product:', error);
            });
    } else {
        alert('Invalid input. Please try again.');
    }
}

function addToCart(productId) {
    const quantity = parseInt(prompt('Enter quantity:', '1'), 10);
    if (isNaN(quantity) || quantity <= 0) {
        alert('Please enter a valid quantity.');
        return;
    }

    fetch(`http://localhost:8080/carts/1/products/${productId}?quantity=${quantity}`, {
        method: 'POST'
    })
        .then(response => {
            if (response.status === 200) {
                alert(`Added ${quantity} items to the cart.`);
                viewCart(); // Refresh the cart view
            } else {
                alert('Failed to add items to the cart.');
            }
        })
        .catch(error => {
            console.error('Error adding items to the cart:', error);
        });
}

function viewCart() {
    fetch('http://localhost:8080/carts/1/details')
        .then(response => response.json())
        .then(cartDetails => {
            const cartItems = document.getElementById('cartItems');
            cartItems.innerHTML = '';

            cartDetails.forEach(cartItem => {
                const product = cartItem.product;
                const cartItemLi = document.createElement('li');
                cartItemLi.innerHTML = `
                    <strong>${product.name}</strong> - Price: ${product.price} - Quantity: ${cartItem.quantity}
                    <button onclick="editCartItem(${cartItem.id})">Edit</button>
                    <button onclick="deleteCartItem(${cartItem.id})">Delete</button>
                `;
                cartItems.appendChild(cartItemLi);
            });
        });
}


function editCartItem(cartItemId) {
    const newQuantity = parseInt(prompt('Enter new quantity:', '1'), 10);
    if (isNaN(newQuantity) || newQuantity <= 0) {
        alert('Please enter a valid quantity.');
        return;
    }

    fetch(`http://localhost:8080/carts/details/${cartItemId}?newQuantity=${newQuantity}`, {
        method: 'PUT'
    })
        .then(response => {
            if (response.status === 200) {
                alert('Cart item updated.');
                viewCart(); // Refresh the cart view
            } else {
                alert('Failed to update cart item.');
            }
        })
        .catch(error => {
            console.error('Error updating cart item:', error);
        });
}

function deleteCartItem(cartItemId) {
    if (confirm('Are you sure you want to delete this item from the cart?')) {
        fetch(`http://localhost:8080/carts/details/${cartItemId}`, {
            method: 'DELETE'
        })
            .then(response => {
                if (response.status === 200) {
                    alert('Cart item deleted.');
                    viewCart(); // Refresh the cart view
                } else {
                    alert('Failed to delete cart item.');
                }
            })
            .catch(error => {
                console.error('Error deleting cart item:', error);
            });
    }
}
