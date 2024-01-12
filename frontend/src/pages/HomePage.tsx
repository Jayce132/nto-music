import {useState, useEffect} from 'react';
import ProductList from '../components/ProductList';
import ProductModal from "../components/ProductModal.tsx";

const HomePage = ({isAdmin}) => {
    const [products, setProducts] = useState([]);
    const [selectedProduct, setSelectedProduct] = useState(null);
    const [detailedProduct, setDetailedProduct] = useState(null);

    useEffect(() => {
        // Will use the SimpleProductDTO to show just the name and price of a product
        fetch('http://localhost:8080/api/products')
            .then(response => response.json())
            .then(data => setProducts(data))
            .catch(error => console.error('Error fetching products:', error));
    }, []);

    const handleProductClick = (product) => {
        setSelectedProduct(product);
        // Will use the DetailedProductDTO to show all the details of a product
        fetch(`http://localhost:8080/api/products/${product.id}`)
            .then(response => response.json())
            .then(data => setDetailedProduct(data))
            .catch(error => console.error('Error fetching product details:', error));
    };

    const handleCloseModal = () => {
        setSelectedProduct(null);
        setDetailedProduct(null);
    };

    return (
        <div>
            <h1>Welcome to the Music Shop!</h1>
            <ProductList products={products} onProductClick={handleProductClick} isAdmin={isAdmin}/>
            {selectedProduct && detailedProduct && <ProductModal product={detailedProduct} onClose={handleCloseModal}/>}
        </div>
    );
};

export default HomePage;
