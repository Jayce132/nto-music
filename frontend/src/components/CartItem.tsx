const CartItem = ({cartItem, deleteCartItem}) => {
    return (
        <li>
            <strong>{cartItem.product.name}</strong> - Price: {cartItem.product.price} - Quantity: {cartItem.quantity}
            <button className={"buttonDanger"} onClick={() => deleteCartItem(cartItem.id)}>Delete</button>
        </li>
    );
};

export default CartItem;
