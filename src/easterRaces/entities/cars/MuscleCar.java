package easterRaces.entities.cars;

import static easterRaces.common.ExceptionMessages.INVALID_HORSE_POWER;

public class MuscleCar extends BaseCar {
    private static final double CUBIC_CENTIMETERS = 5000.00;

    public MuscleCar(String model, int horsePower) {
        super(model,checkHorsePower(horsePower), CUBIC_CENTIMETERS);
    }

    public static int checkHorsePower(int horsePower) {
        if (horsePower < 400 || horsePower > 600) {
            throw new IllegalArgumentException(String.format(INVALID_HORSE_POWER, horsePower));
        }
        return horsePower;
    }

    //The cubic centimeters for this type of car are 5000. Minimum horsepower is 400 and maximum horsepower is 600
}
