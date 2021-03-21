package easterRaces.core;

import easterRaces.core.interfaces.Controller;
import easterRaces.entities.cars.Car;
import easterRaces.entities.cars.MuscleCar;
import easterRaces.entities.cars.SportsCar;
import easterRaces.entities.drivers.Driver;
import easterRaces.entities.drivers.DriverImpl;
import easterRaces.entities.racers.Race;
import easterRaces.entities.racers.RaceImpl;
import easterRaces.repositories.CarRepository;
import easterRaces.repositories.DriverRepository;
import easterRaces.repositories.RaceRepository;
import easterRaces.repositories.interfaces.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static easterRaces.common.ExceptionMessages.*;
import static easterRaces.common.OutputMessages.*;

public class ControllerImpl implements Controller {
    private Repository<Car> carRepository;
    private Repository<Driver> driverRepository;
    private Repository<Race> raceRepository;

    //new ControllerImpl(riderRepository, motorcycleRepository, raceRepository);
    public ControllerImpl(Repository<Driver> driverRepository, Repository<Car> carRepository,Repository<Race> raceRepository) {
        this.driverRepository = driverRepository;
        this.carRepository = carRepository;
        this.raceRepository = raceRepository;
    }

    @Override
    public String createDriver(String driver) {
        Driver currentDriver = driverRepository.getByName(driver);

        if (currentDriver != null) {
            throw new IllegalArgumentException(String.format(DRIVER_EXISTS, driver));
        }

        currentDriver = new DriverImpl(driver);
        driverRepository.add(currentDriver);

        return String.format(DRIVER_CREATED, driver);
    }

    @Override
    public String createCar(String type, String model, int horsePower) {
        Car car = carRepository.getByName(model);
        if (car != null) {
            throw new IllegalArgumentException(String.format(CAR_EXISTS, model));
        }
        if (type.equals("Muscle")) {
            car = new MuscleCar(model, horsePower);
            carRepository.add(car);
            type = "MuscleCar";

        } else if (type.equals("Sports")) {
            car = new SportsCar(model, horsePower);
            carRepository.add(car);
            type = "SportsCar";
        }

        return String.format(CAR_CREATED, type, model);
    }

    @Override
    public String addCarToDriver(String driverName, String carModel) {
        Driver driver = driverRepository.getByName(driverName);
        if (driver == null) {
            throw new IllegalArgumentException(String.format(DRIVER_NOT_FOUND, driverName));
        }
        Car car = carRepository.getByName(carModel);
        if (car == null) {
            throw new IllegalArgumentException(String.format(CAR_NOT_FOUND, carModel));
        }

        driver.addCar(car);

        return String.format(CAR_ADDED, driverName, carModel);
    }

    @Override
    public String addDriverToRace(String raceName, String driverName) {
        Race race = raceRepository.getByName(raceName);
        if (race == null) {
            throw new IllegalArgumentException(String.format(RACE_NOT_FOUND, raceName));
        }

        Driver driver = driverRepository.getByName(driverName);
        if (driver == null) {
            throw new IllegalArgumentException(String.format(DRIVER_NOT_FOUND, driverName));
        }

        race.addDriver(driver);

        return String.format(DRIVER_ADDED, driverName, raceName);
    }

    @Override
    public String startRace(String raceName) {
        Race currentRace = raceRepository.getByName(raceName);
        if (currentRace == null) {
            throw new IllegalArgumentException(String.format(RACE_NOT_FOUND, raceName));
        }

        Collection<Driver> drivers = currentRace.getDrivers();
        if (drivers.size() < 3) {
            throw new IllegalArgumentException(String.format(RACE_INVALID, raceName, 3));
        }

        List<Driver> top3 = drivers.stream()
                .sorted((d1, d2) -> {
                    double left = d1.getCar().calculateRacePoints(currentRace.getLaps());
                    double right = d2.getCar().calculateRacePoints(currentRace.getLaps());
                    return Double.compare(right, left);
                })
                .limit(3)
                .collect(Collectors.toList());

        return String.format(DRIVER_FIRST_POSITION, top3.get(0).getName(), raceName) +
                System.lineSeparator() +
                String.format(DRIVER_SECOND_POSITION, top3.get(1).getName(), raceName) +
                System.lineSeparator() +
                String.format(DRIVER_THIRD_POSITION, top3.get(2).getName(), raceName);
    }

    @Override
    public String createRace(String name, int laps) {

        Race race = raceRepository.getByName(name);

        if (race != null) {
            throw new IllegalArgumentException(String.format(RACE_EXISTS, name));
        }

        race = new RaceImpl(name, laps);
        raceRepository.add(race);

        return String.format(RACE_CREATED, name);
    }
}
