package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.AccidentMem;

import java.util.Collection;

/**
 * @author Roman Rusanov
 * @version 0.1
 * @since 11.02.2021
 * email roman9628@gmail.com
 */
@Service
public class AccidentService {

    private final AccidentMem storage;

    public AccidentService(AccidentMem storage) {
        this.storage = storage;
    }

    public void saveAccident(Accident accident, int typeId) {
        this.storage.addToStore(Accident.of(
                accident.getId(),
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                this.storage.getAccidentTypeById(typeId)));
    }

    public Collection<Accident> getAllAccidents() {
        return storage.getAllAccidents();
    }

    public Accident getAccidentById(int id) {
        return this.storage.getAccidentById(id);
    }

    public Collection<AccidentType> getAllAccidentTypes() {
        return this.storage.getAllAccidentTypes();
    }
}