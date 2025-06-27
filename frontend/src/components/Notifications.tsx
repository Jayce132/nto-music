import {useState, useEffect} from 'react';

const Notifications = () => {
    const [notifications, setNotifications] = useState([]);
    const userId = sessionStorage.getItem('userId'); // Get the user ID from session storage

    useEffect(() => {
        fetchNotifications();
    }, [userId]);

    const fetchNotifications = () => {
        if (userId) {
            fetch(`/api/notifications/user/${userId}`)
                .then(response => response.json())
                .then(data => setNotifications(data))
                .catch(error => console.error('Error fetching notifications:', error));
        }
    };

    const deleteNotification = (notificationId) => {
        fetch(`/api/notifications/${notificationId}`, {method: 'DELETE'})
            .then(() => {
                // Remove the notification from the state
                setNotifications(notifications.filter(notification => notification.id !== notificationId));
            })
            .catch(error => console.error('Error deleting notification:', error));
    };

    return (
        <div className="notifications">
            {notifications.map(notification => (
                <div key={notification.id} className="notification">
                    {notification.message}
                    <button onClick={() => deleteNotification(notification.id)}>X</button>
                </div>
            ))}
        </div>
    );
};

export default Notifications;
