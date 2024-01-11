import {useState, useEffect} from 'react';
import CartItem from '../components/CartItem';

const CartPage = () => {
    const [cartItems, setCartItems] = useState([]);

    useEffect(() => {
        fetch('http://localhost:8080/api/carts/1/details')
            .then(response => response.json())
            .then(data => setCartItems(data))
            .catch(error => console.error('Error fetching cart items:', error));
    }, []);

    const deleteCartItem = (cartItemId) => {
        if (window.confirm('Are you sure you want to delete this item from the cart?')) {
            fetch(`http://localhost:8080/api/carts/details/${cartItemId}`, {
                method: 'DELETE'
            })
                .then(response => {
                    if (response.status === 200) {
                        alert('Cart item deleted.');
                        setCartItems(cartItems.filter(item => item.id !== cartItemId));
                    } else {
                        alert('Failed to delete cart item.');
                    }
                })
                .catch(error => console.error('Error deleting cart item:', error));
        }
    };

    return (
        <div id="cart">
            <h2>Shopping Cart</h2>
            <ul id="cartItems">
                {cartItems.map(cartItem => (
                    <CartItem key={cartItem.id} cartItem={cartItem} deleteCartItem={deleteCartItem}/>
                ))}
            </ul>
        </div>
    );
};

export default CartPage;
