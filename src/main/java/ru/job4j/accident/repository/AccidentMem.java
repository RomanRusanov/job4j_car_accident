package ru.job4j.accident.repository;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

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

    public void addToStore(Accident accident) {
        this.accidents.put(accident.getId(), accident);
    }

    public Collection<Accident> getAllAccidents() {
        return this.accidents.values();
    }
}