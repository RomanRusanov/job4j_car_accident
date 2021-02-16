package ru.job4j.accident.repository;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author Roman Rusanov
 * @version 0.1
 * @since 11.02.2021
 * email roman9628@gmail.com
 */
@Repository
@Component
public class AccidentMem implements DAO {

    private Map<Integer, Accident> accidents = new HashMap<>();

    private Map<Integer, AccidentType> accidentType = new HashMap<>();

    private Map<Integer, Rule> rules = new HashMap<>();

    public AccidentMem() {
        this.fillAccidentTypes();
        this.fillAccidentRules();
        this.addExamplesRowsToStore();
    }

    private void fillAccidentTypes() {
        this.accidentType.put(1, AccidentType.of(1, "Две машины"));
        this.accidentType.put(2, AccidentType.of(2, "Машина и человек"));
        this.accidentType.put(3, AccidentType.of(3, "Машина и велосипед"));
    }

    private void fillAccidentRules() {
        this.rules.put(1, Rule.of(1, "Статья. 1"));
        this.rules.put(2, Rule.of(2, "Статья. 2"));
        this.rules.put(3, Rule.of(3, "Статья. 3"));
    }

    private void addExamplesRowsToStore() {
        this.accidents.put(1, Accident.of(
                1,
                "Иван",
                "Текст1",
                "Адрес1",
                this.accidentType.get(1),
                new HashSet<>(rules.values()))
        );
        this.accidents.put(2, Accident.of(
                2,
                "Семен",
                "Текст2",
                "Адрес2",
                this.accidentType.get(2),
                new HashSet<>(rules.values()))
        );
        this.accidents.put(3, Accident.of(
                3,
                "Владимир",
                "Текст3",
                "Адрес3",
                this.accidentType.get(3),
                new HashSet<>(rules.values()))
        );
    }

    public Accident addToStore(Accident accident) {
        this.accidents.put(accident.getId(), accident);
        return accident;
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

    public Collection<AccidentType> getAllAccidentTypes() {
        return this.accidentType.values();
    }

    public Rule getRuleById(int id) {
        return rules.get(id);
    }

    public Collection<Rule> getAllRules() {
        return this.rules.values();
    }
}