package org.restassured.utilities;

import com.github.javafaker.Faker;

public class RandomDataGenerator {

    Faker faker = new Faker();



    public static void main(String[] args) {
        RandomDataGenerator testData = new RandomDataGenerator();
        System.out.println(testData.generateRandomBs());
    }

    public String generateRandomName() {
        return faker.name().fullName();
    }

    public String generateRandomUsername() {
        return faker.name().username();
    }

    public String generateRandomEmail() {
        return faker.internet().emailAddress();
    }

    public String generateRandomStreet() {
        return faker.address().streetName();
    }

    public String generateRandomSuite() {
        return faker.address().secondaryAddress();
    }

    public String generateRandomCity() {
        return faker.address().city();
    }

    public String generateRandomZipCode() {
        return faker.address().zipCode();
    }

    public String generateRandomLatitude() {
        return faker.address().latitude();
    }

    public String generateRandomLongitude() {
        return faker.address().longitude();
    }

    public String generateRandomPhoneNumber() {
        return faker.phoneNumber().phoneNumber();
    }

    public String generateRandomWebsite() {
        return faker.internet().domainName();
    }

    public String generateRandomCompanyName() {
        return faker.company().name();
    }

    public String generateRandomCatchPhrase() {
        return faker.company().catchPhrase();
    }

    public String generateRandomBs() {
        return faker.company().bs();
    }

}
