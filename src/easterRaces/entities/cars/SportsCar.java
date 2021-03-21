package easterRaces.entities.cars;

import static easterRaces.common.ExceptionMessages.INVALID_HORSE_POWER;

public class SportsCar extends BaseCar{
    private static final double CUBIC_CENTIMETERS = 3000.00;

    public SportsCar(String model, int horsePower) {
        super(model, checkHorsePower(horsePower), CUBIC_CENTIMETERS);
    }

    public static int checkHorsePower(int horsePower) {
        if (horsePower < 250 || horsePower > 450) {
            throw new IllegalArgumentException(String.format(INVALID_HORSE_POWER, horsePower));
        }
        return horsePower;
    }

    //The cubic centimeters for this type of car are 3000. Minimum horsepower is 250 and maximum horsepower is 450.
}
