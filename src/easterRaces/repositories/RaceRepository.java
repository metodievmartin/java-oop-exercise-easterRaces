package easterRaces.repositories;

import easterRaces.entities.cars.Car;
import easterRaces.entities.drivers.Driver;
import easterRaces.entities.drivers.DriverImpl;
import easterRaces.entities.racers.Race;
import easterRaces.repositories.interfaces.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class RaceRepository implements Repository<Race> {
    private Collection<Race> models;
    public RaceRepository() {
        this.models = new ArrayList<>();
    }

    @Override
    public Race getByName(String name) {
        for (Race race : this.models) {
            if (race.getName().equals(name)) {
                return race;
            }
        }
        return null;
    }

    @Override
    public Collection<Race> getAll() {
        return Collections.unmodifiableCollection(this.models);
    }

    @Override
    public void add(Race model) {
        this.models.add(model);
    }

    @Override
    public boolean remove(Race model) {
        return this.models.remove(model);
    }
}
