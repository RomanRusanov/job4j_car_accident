package ru.job4j.accident.repository;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;

import java.util.Collection;
import java.util.HashMap;

/**
 * @author Roman Rusanov
 * @version 0.1
 * @since 11.02.2021
 * email roman9628@gmail.com
 */
@Repository
@Component
@Scope("singleton")
public class AccidentMem {

    private HashMap<Integer, Accident> accidents = new HashMap<>();

    private HashMap<Integer, AccidentType> accidentType = new HashMap<>();

    public AccidentMem() {
        this.fillAccidentTypes();
        this.addExamplesRowsToStore();
    }

    private void fillAccidentTypes() {
        this.accidentType.put(1, AccidentType.of(1, "Две машины"));
        this.accidentType.put(2, AccidentType.of(2, "Машина и человек"));
        this.accidentType.put(3, AccidentType.of(3, "Машина и велосипед"));
    }

    private void addExamplesRowsToStore() {
        this.accidents.put(1, Accident.of(
                1,
                "Иван",
                "Текст1",
                "Адрес1",
                this.accidentType.get(1)));
        this.accidents.put(2, Accident.of(
                2,
                "Семен",
                "Текст2",
                "Адрес2",
                this.accidentType.get(2)));
        this.accidents.put(3, Accident.of(
                3,
                "Владимир",
                "Текст3",
                "Адрес3",
                this.accidentType.get(3)));
    }

    public void addToStore(Accident accident) {
        this.accidents.put(accident.getId(), accident);
    }

    public Collection<Accident> getAllAccidents() {
        return this.accidents.values();
    }

    public Accident getAccidentById(int id) {
        return this.accidents.get(id);
    }

    public AccidentType getAccidentTypeById(int id) {
        return this.accidentType.get(id);
    }
}