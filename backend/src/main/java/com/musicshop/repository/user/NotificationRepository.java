package com.musicshop.repository.user;

import com.musicshop.model.user.Notification;
import com.musicshop.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUser(User user);
}


