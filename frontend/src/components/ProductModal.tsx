const ProductModal = ({ product, onClose }) => {
    if (!product) return null;

    return (
        <div className="modal">
            <div className="modal-content">
                <h2>{product.name}</h2>
                <p>{product.description}</p>
                <p>Price: ${product.price}</p>
                <p>Available Quantity: {product.quantityAvailable}</p>
                <p>Category: {product.categoryName}</p>
                <button onClick={onClose}>Close</button>
            </div>
        </div>
    );
};

export default ProductModal;
