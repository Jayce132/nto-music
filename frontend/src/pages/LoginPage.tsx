import {useNavigate} from "react-router-dom";

const LoginPage = ({setIsAuthenticated, setIsAdmin}) => {
    const navigate = useNavigate();

    const handleCustomerLogin = () => {
        sessionStorage.setItem('isAuthenticated', 'true');
        sessionStorage.setItem('userId', '1'); // Assuming John Doe has ID 1
        setIsAuthenticated(true);
        setIsAdmin(false);
        navigate('/');
    };

    const handleAdminLogin = () => {
        sessionStorage.setItem('isAuthenticated', 'true');
        sessionStorage.setItem('isAdmin', 'true');
        setIsAuthenticated(true);
        setIsAdmin(true);
        navigate('/admin');
    };

    return (
        <div>
            <h1>Login</h1>
            <button onClick={handleCustomerLogin}>Log in as Customer</button>
            <button onClick={handleAdminLogin}>Log in as Admin</button>
        </div>
    );
};

export default LoginPage;
