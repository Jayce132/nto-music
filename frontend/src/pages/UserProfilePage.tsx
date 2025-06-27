import {useState, useEffect} from 'react';

const UserProfilePage = () => {
    const [user, setUser] = useState({firstName: '', lastName: '', email: '', phoneNumber: ''});
    const customerId = sessionStorage.getItem('userId'); // Get customer ID from session storage

    useEffect(() => {
        fetch(`/api/users/${customerId}`)
            .then(response => response.json())
            .then(data => setUser(data))
            .catch(error => console.error('Error fetching user data:', error));
    }, [customerId]);

    const handleInputChange = (e) => {
        setUser({...user, [e.target.name]: e.target.value});
    };

    const saveChanges = () => {
        fetch(`/api/users/${customerId}`, {
            method: 'PUT',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(user)
        })
            .then(response => {
                if (!response.ok) {
                    console.error('Failed to update user data');
                }
            })
            .catch(error => console.error('Error updating user data:', error));
    };

    return (
        <div>
            <h1>Edit Profile</h1>
            <input
                type="text"
                name="firstName"
                value={user.firstName}
                onChange={handleInputChange}
                placeholder="First Name"
            />
            <input
                type="text"
                name="lastName"
                value={user.lastName}
                onChange={handleInputChange}
                placeholder="Last Name"
            />
            <input
                type="email"
                name="email"
                value={user.email}
                onChange={handleInputChange}
                placeholder="Email"
            />
            <input
                type="text"
                name="phoneNumber"
                value={user.phoneNumber}
                onChange={handleInputChange}
                placeholder="Phone Number"
            />
            <button onClick={saveChanges}>Save Changes</button>
        </div>
    );
};

export default UserProfilePage;
