package com.ideacrest.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLRootResolver;
import com.ideacrest.graphql.repositories.UserRepository;
import com.ideacrest.graphql.types.Address;
import com.ideacrest.graphql.types.AddressInput;
import com.ideacrest.graphql.types.AuthData;
import com.ideacrest.graphql.types.User;

import java.util.List;

public class Mutation implements GraphQLRootResolver {
    private final UserRepository userRepository;


    public Mutation( UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User createUser(String name, AuthData auth, List<AddressInput> addresses) {
        User newUser = new User(name, auth.getEmail(), auth.getPassword(),null);
        return userRepository.saveUser(newUser, addresses);
    }
}
