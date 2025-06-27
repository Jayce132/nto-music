const Product = ({product, onProductClick, isAdmin}) => {
    const addToCart = (e, productId, availableQuantity, isAdmin) => {
        e.stopPropagation(); // Prevents the click from reaching the parent div

        const quantity = parseInt(prompt('Enter quantity:', '1'), 10);

        if (isNaN(quantity) || quantity <= 0) {
            alert('Please enter a valid quantity.');
            return;
        }

        if (quantity > availableQuantity) {
            alert(`Only ${availableQuantity} items available.`);
            return;
        }

        fetch(`/api/carts/1/products/${productId}?quantity=${quantity}`, {
            method: 'POST'
        })
            .then(response => {
                if (response.ok) {
                    alert(`Added ${quantity} items to the cart.`);
                } else {
                    alert('Failed to add items to the cart.');
                }
            })
            .catch(error => {
                console.error('Error adding items to the cart:', error);
            });
    };

    return (
        <div className="product" onClick={() => onProductClick(product)}>
            <strong>{product.name}</strong> - Price: ${product.price}
            {/*Admins don't have add to cart functionality*/}
            {!isAdmin && (
                <button onClick={(e) => addToCart(e, product.id, product.quantityAvailable)}>Add to Cart</button>
            )}        </div>
    );
};

export default Product;
