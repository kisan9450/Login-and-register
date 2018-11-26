package com.stackroute.service;


import com.stackroute.domain.Userprofiledomain1;
import com.stackroute.repository.UserprofileRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserprofileServiceImplementation implements UserprofileService {

    private UserprofileRepository userprofileRepository;

    @Autowired
    public UserprofileServiceImplementation(UserprofileRepository userprofileRepository){
        this.userprofileRepository = userprofileRepository;
    }

    @Override
    public Userprofiledomain1 saveid(Userprofiledomain1 id){
        id.setSid(ObjectId.get().toString());
        Userprofiledomain1 savedid= userprofileRepository.save(id);
        return savedid;
    }

    @Override
    public List<Userprofiledomain1> getallinfo(){
        return userprofileRepository.findAll();
    }

}
