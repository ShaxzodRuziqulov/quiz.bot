/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:04.07.2024
 * TIME:8:33
 */
package com.telegram_bot.zakovat_bot.service;

import com.telegram_bot.zakovat_bot.entity.User;
import com.telegram_bot.zakovat_bot.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.time.LocalDateTime;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByChatId(Long chatId) {
        return userRepository.findByChatId(chatId);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void registerUser(Message message) {
        if (userRepository.findById(message.getChatId()).isEmpty()) {
            var chatId = message.getChatId();
            var chat = message.getChat();

            User user = new User();
            user.setChatId(chatId);
            user.setFirstName(chat.getFirstName());
            user.setLastName(chat.getLastName());
            user.setUserName(chat.getUserName());
            user.setRegisteredAt(LocalDateTime.now());
            userRepository.save(user);
        }
    }
}