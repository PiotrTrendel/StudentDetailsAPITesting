package org.utils;


import com.github.javafaker.Faker;

public class RandomDataGenerator {

    public static String generateRandomLastName() {
        Faker faker = new Faker();
        return faker.name().lastName();
    }
    public static String generateRandomName() {
        Faker faker = new Faker();
        return faker.name().firstName();
    }



}
