import {useState, useEffect} from 'react';

const AdminPage = () => {
    const [products, setProducts] = useState([]);
    const [newProduct, setNewProduct] = useState({name: '', price: 0});
    const [selectedDiscount, setSelectedDiscount] = useState('fixed');

    useEffect(() => {
        fetchProducts();
    }, []);

    const fetchProducts = () => {
        fetch('http://localhost:8080/api/products')
            .then(response => response.json())
            .then(data => setProducts(data))
            .catch(error => console.error('Error fetching products:', error));
    };

    const addProduct = () => {
        fetch('http://localhost:8080/api/products', {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(newProduct)
        })
            .then(response => {
                if (response.ok) {
                    fetchProducts(); // Refresh the list after adding
                } else {
                    console.error('Failed to add product');
                }
            })
            .catch(error => console.error('Error adding product:', error));
    };

    const handleEdit = (id, updatedProduct) => {
        fetch(`http://localhost:8080/api/products/${id}`, {
            method: 'PATCH',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(updatedProduct)
        })
            .then(response => {
                if (response.ok) {
                    fetchProducts(); // Refresh the list after editing
                } else {
                    console.error('Failed to update product');
                }
            })
            .catch(error => console.error('Error updating product:', error));
    };

    const deleteProduct = (id) => {
        fetch(`http://localhost:8080/api/products/${id}`, {method: 'DELETE'})
            .then(response => {
                if (response.ok) {
                    fetchProducts(); // Refresh the list after deleting
                } else {
                    console.error('Failed to delete product');
                }
            })
            .catch(error => console.error('Error deleting product:', error));
    };

    const applyDiscount = (id, discountType) => {
        fetch(`http://localhost:8080/api/products/${id}/apply-discount?discountType=${discountType}`, {
            method: 'PATCH'
        })
            .then(response => {
                if (response.ok) {
                    fetchProducts(); // Refresh the list after applying discount
                } else {
                    console.error('Failed to apply discount');
                }
            })
            .catch(error => console.error('Error applying discount:', error));
    };

    return (
        <div>
            <h1>Admin Page</h1>
            <div>
                <h2>Add Product</h2>
                <input
                    type="text"
                    placeholder="Product Name"
                    value={newProduct.name}
                    onChange={e => setNewProduct({...newProduct, name: e.target.value})}
                />
                <input
                    type="number"
                    placeholder="Product Price"
                    value={newProduct.price}
                    onChange={e => setNewProduct({...newProduct, price: e.target.value})}
                />
                <button onClick={addProduct}>Add Product</button>
            </div>
            <div>
                <h2>Product List</h2>
                <ul>
                    {products.map(product => (
                        <li key={product.id}>
                            <input
                                type="text"
                                value={product.name}
                                onChange={(e) => product.name = e.target.value}
                            />
                            <input
                                type="number"
                                value={product.price}
                                onChange={(e) => product.price = parseFloat(e.target.value) || 0}
                            />
                            <button
                                onClick={() => handleEdit(product.id, {name: product.name, price: product.price})}>Save
                            </button>
                            <button onClick={() => deleteProduct(product.id)}>Delete</button>

                            <select onChange={(e) => setSelectedDiscount(e.target.value)}>
                                <option value="fixed">Fixed Amount</option>
                                <option value="percentage">Percentage</option>
                            </select>
                            <button onClick={() => applyDiscount(product.id, selectedDiscount)}>Apply Discount</button>
                        </li>
                    ))}
                </ul>
            </div>
        </div>
    );
};

export default AdminPage;
