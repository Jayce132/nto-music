// src/App.js
import React, {useEffect, useState} from 'react';
import {BrowserRouter as Router, Routes, Route, Link} from 'react-router-dom';
import HomePage from './pages/HomePage';
import CartPage from './pages/CartPage';
import AdminPage from './pages/AdminPage';
import UserProfilePage from './pages/UserProfilePage';
import LoginPage from './pages/LoginPage';
import ProtectedRoute from './components/ProtectedRoute';
import "./App.css";
import Notifications from "./components/Notifications.tsx";

function App() {
    const [isAuthenticated, setIsAuthenticated] = useState(false);
    const [isAdmin, setIsAdmin] = useState(false);

    useEffect(() => {
        // Check session storage and update state accordingly
        setIsAuthenticated(!!sessionStorage.getItem("isAuthenticated"));
        setIsAdmin(!!sessionStorage.getItem("isAdmin"));
    }, []);

    const handleLogout = () => {
        sessionStorage.clear(); // Clear all stored session data
        setIsAuthenticated(false);
        setIsAdmin(false);
    };

    return (
        <Router>
            <div>
                <Notifications/>
                <nav>
                    {isAuthenticated && <Link to="/">Home</Link>}
                    {isAuthenticated && !isAdmin && <span> | <Link to="/cart">Cart</Link></span>}
                    {isAuthenticated && !isAdmin && <span> | <Link to="/user-profile">User Profile</Link></span>}
                    {isAdmin && <span> | <Link to="/admin"> Admin</Link></span>}
                    {isAuthenticated && <span> | <Link to="/login" onClick={handleLogout}>Log Out</Link></span>}
                </nav>
                <Routes>
                    <Route path="/" element={
                        <ProtectedRoute>
                            <HomePage isAdmin={isAdmin}/>
                        </ProtectedRoute>
                    }/>
                    <Route path="/cart" element={
                        <ProtectedRoute>
                            <CartPage/>
                        </ProtectedRoute>
                    }/>
                    <Route path="/admin" element={
                        <ProtectedRoute adminOnly={true}>
                            <AdminPage/>
                        </ProtectedRoute>
                    }/>
                    <Route path="/user-profile" element={
                        <ProtectedRoute>
                            <UserProfilePage/>
                        </ProtectedRoute>
                    }/>
                    <Route path="/login" element={<LoginPage setIsAuthenticated={setIsAuthenticated} setIsAdmin={setIsAdmin}/>} />
                </Routes>
            </div>
        </Router>
    );
}

export default App;
