package testdata;

import testdata.RegistrationData;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class RegistrationDataBuilder {

    private final RegistrationData data = new RegistrationData();

    public static RegistrationDataBuilder newUser() {
        return new RegistrationDataBuilder();
    }

    public RegistrationDataBuilder withDefaultValidData() {
        data.setFirstName(randomAlpha("First"));
        data.setLastName(randomAlpha("Last"));
        data.setEmail("auto_" + UUID.randomUUID() + "@test.com");
        data.setAge(randomInt(18, 65));
        data.setAddress("Auto Address " + randomInt(1, 999));
        data.setMobile("9" + randomInt(100000000, 999999999));
        data.setCountry("India");
        data.setHeight(randomDouble(150, 190));
        data.setWeight(randomDouble(50, 100));
        data.setDiabetic(false);
        return this;
    }

    public RegistrationDataBuilder diabeticUser() {
        data.setDiabetic(true);
        return this;
    }

    public RegistrationData build() {
        return data;
    }

    private static int randomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }

    private static double randomDouble(double min, double max) {
        return ThreadLocalRandom.current().nextDouble(min, max);
    }

    private static String randomAlpha(String prefix) {
        return prefix + randomInt(1000, 9999);
    }
}