package com.polarsparc.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;
import com.polarsparc.model.Contact;
import com.polarsparc.model.Person;

public class PersonDemo {
    public static void main(String[] args) {
        Contact c = Contact.newBuilder()
                .setEmail("alice@earth.net")
                .setMobile("111-222-3333")
                .build();
        Person p = Person.newBuilder()
                .setName("Alice")
                .setAge(33)
                .setContact(c)
                .build();

        System.out.printf("Person p = %s\n", p);

        byte[] ba = p.toByteArray();

        Person p2 = null;
        try {
            p2 = Person.parseFrom(ba);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }

        System.out.printf("Person p2 = %s\n", p2);
    }
}
