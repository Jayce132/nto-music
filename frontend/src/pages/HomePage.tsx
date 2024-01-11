import {useState, useEffect} from 'react';
import ProductList from '../components/ProductList';

const HomePage = () => {
    const [products, setProducts] = useState([]);

    useEffect(() => {
        fetch('http://localhost:8080/api/products')
            .then(response => response.json())
            .then(data => setProducts(data))
            .catch(error => console.error('Error fetching products:', error));
    }, []);

    return (
        <div>
            <h1>Welcome to the Music Shop!</h1>
            <ProductList products={products}/>
        </div>
    );
};

export default HomePage;
