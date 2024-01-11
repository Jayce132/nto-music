import Product from './Product';

const ProductList = ({products}) => {
    return (
        <div id="productList">
            {products.map(product => (
                <Product key={product.id} product={product}/>
            ))}
        </div>
    );
};

export default ProductList;
