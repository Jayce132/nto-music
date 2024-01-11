import {Navigate, useLocation} from 'react-router-dom';

const ProtectedRoute = ({children, adminOnly = false}) => {
    const isAuthenticated = sessionStorage.getItem('isAuthenticated');
    const isAdmin = sessionStorage.getItem('isAdmin');
    const location = useLocation();

    if (!isAuthenticated) {
        return <Navigate to="/login" state={{from: location}}/>;
    }

    if (adminOnly && !isAdmin) {
        return <Navigate to="/"/>;
    }

    return children;
};

export default ProtectedRoute;
