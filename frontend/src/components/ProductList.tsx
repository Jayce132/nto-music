import Product from './Product';

const ProductList = ({ products, onProductClick, isAdmin }) => {
    return (
        <div id="productList">
            {products.map(product => (
                <Product key={product.id} product={product} onProductClick={onProductClick} isAdmin={isAdmin} />
            ))}
        </div>
    );
};

export default ProductList;
