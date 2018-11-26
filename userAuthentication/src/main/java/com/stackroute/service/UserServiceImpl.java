package com.stackroute.service;

import com.stackroute.domain.User;
import com.stackroute.domain.Userprofiledomain;
import com.stackroute.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;



    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }


@KafkaListener(topics = "${kafka.listeningTopic}" ,groupId = "${kafka.groupId}",
        containerFactory="${kafka.containerFactory}")
    public void getUserFrmTopic(@Payload Userprofiledomain userprofiledomain) {
        System.out.println("Consumed Json Message: "+ userprofiledomain.toString());
        User user = new User();
        user.setUserName(userprofiledomain.getUserName());
        user.setPassword(userprofiledomain.getPassword());
        userRepository.save(user);
    }
}

