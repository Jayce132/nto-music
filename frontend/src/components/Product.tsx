const Product = ({product}) => {
    const addToCart = (productId) => {
        const quantity = parseInt(prompt('Enter quantity:', '1'), 10);
        if (!isNaN(quantity) && quantity > 0) {
            fetch(`http://localhost:8080/api/carts/1/products/${productId}?quantity=${quantity}`, {
                method: 'POST'
            })
                .then(response => {
                    if (response.status === 200) {
                        alert(`Added ${quantity} items to the cart.`);
                    } else {
                        alert('Failed to add items to the cart.');
                    }
                })
                .catch(error => {
                    console.error('Error adding items to the cart:', error);
                });
        } else {
            alert('Please enter a valid quantity.');
        }
    };

    return (
        <div className="product">
            <strong>{product.name}</strong> - Price: ${product.price}
            <button onClick={() => addToCart(product.id)}>Add to Cart</button>
        </div>
    );
};

export default Product;
