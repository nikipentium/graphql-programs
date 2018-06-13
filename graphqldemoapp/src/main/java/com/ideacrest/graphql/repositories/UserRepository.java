package com.ideacrest.graphql.repositories;

import com.ideacrest.graphql.types.Address;
import com.ideacrest.graphql.types.Address.AddressType;
import com.ideacrest.graphql.types.AddressInput;
import com.ideacrest.graphql.types.User;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class UserRepository {

    private final MongoCollection<Document> users;

    public UserRepository(MongoCollection<Document> users){
        this.users = users;
    }

    public User findById(String id) {
        Document doc = users.find(eq("_id", new ObjectId(id))).first();
        return user(doc);
    }

    private User user(Document doc) {
        return new User(
                doc.get("_id").toString(),
                doc.getString("name"),
                doc.getString("email"),
                doc.getString("password"),
                populateAddressList((List<Document>)doc.get("addresses")));
    }

    private List<Address> populateAddressList(List<Document> addressListDoc){
        List<Address> addresses = new ArrayList<>();
        for (Document addressDoc : addressListDoc) {
            Address address = new Address(
                    addressDoc.getString("line1"),
                    addressDoc.getString("line2"),
                    addressDoc.getString("pinCode"),
                    AddressType.valueOf(addressDoc.getString("addressType"))
            );
            addresses.add(address);
        }
        return addresses;
    }

    public List<User> getAllUsers(){
        List<User> allUsers = new ArrayList<>();
        for (Document doc : users.find()) {
            allUsers.add(user(doc));
        }
        return allUsers;
    }

    public User saveUser(User user, List<AddressInput> addressInputs) {
        Document doc = new Document();
        doc.append("name", user.getName());
        doc.append("email", user.getEmail());
        doc.append("password", user.getPassword());
        doc.append("addresses", addressInputs);
        users.insertOne(doc);
        return new User(
                doc.get("_id").toString(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                null);
    }

}
