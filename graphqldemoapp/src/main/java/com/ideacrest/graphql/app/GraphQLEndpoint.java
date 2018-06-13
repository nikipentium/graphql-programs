package com.ideacrest.graphql.app;

import com.coxautodev.graphql.tools.SchemaParser;
import com.ideacrest.graphql.repositories.UserRepository;
import com.ideacrest.graphql.resolvers.Mutation;
import com.ideacrest.graphql.resolvers.Query;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import graphql.schema.GraphQLSchema;
import graphql.servlet.GraphQLServlet;
import graphql.servlet.SimpleGraphQLServlet;

import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = "/graphql")
public class GraphQLEndpoint extends SimpleGraphQLServlet{

    private static final UserRepository userRepository;

    static {
        /*MongoClientURI uri = new MongoClientURI(
                "mongodb+srv://mongotest:math1640@hackernews-b49zo.mongodb.net/hackernews");

        MongoClient mongoClient = new MongoClient(uri);*/
        MongoClient mongoClient = new MongoClient();
        MongoDatabase database = mongoClient.getDatabase("users-demo");
        userRepository = new UserRepository(database.getCollection("users"));
    }


    public GraphQLEndpoint() {
        super(buildSchema());
    }

    private static GraphQLSchema buildSchema() {
        return SchemaParser.newParser()
                .file("schema.graphqls")
                .resolvers(new Query(userRepository),
                        new Mutation(userRepository))
                .build()
                .makeExecutableSchema();
    }
}
