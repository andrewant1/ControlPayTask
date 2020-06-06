package com.controlpay.data;

import io.github.sskorol.core.DataSupplier;

import java.util.stream.Stream;

public class UsersDataProvider {
    @DataSupplier
    public Stream<Users> getData(){

        return Stream.of(
                new Users(1, "John","Willington","admin@test.com","qwerty1", 24,"Admin","READ, CREATE, UPDATE, DELETE, BULK_CREATE"),
                new Users(2, "Peter","Stones","j.accountant@test.com","qwerty2", 29,"Junior accountant","READ")
        );

    }
}
