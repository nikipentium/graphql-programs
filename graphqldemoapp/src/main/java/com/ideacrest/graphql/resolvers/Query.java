package com.ideacrest.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLRootResolver;
import com.ideacrest.graphql.repositories.UserRepository;
import com.ideacrest.graphql.types.User;

import java.util.List;

public class Query implements GraphQLRootResolver{
    private final UserRepository userRepository;

    public Query(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> allUsers(){
        return userRepository.getAllUsers();
    }

    public User user(String id){
        return userRepository.findById(id);
    }

}

