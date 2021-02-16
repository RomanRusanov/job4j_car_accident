package ru.job4j.accident.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.DAO;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Roman Rusanov
 * @version 0.1
 * @since 11.02.2021
 * email roman9628@gmail.com
 */
@Service
public class AccidentService {

    private final DAO storage;
    private static final Logger LOG = LoggerFactory.getLogger(AccidentService.class.getName());
    private static final Marker MARKER = MarkerFactory.getMarker("Service");

    public AccidentService(@Qualifier("accidentJdbcTemplate") DAO storage) {
        this.storage = storage;
    }

    public void saveAccident(Accident accident, int typeId, String[] ids) {
        Set<Rule> allRulesAccident = this.idsConvertToRules(ids);
        Accident accidentToStore = Accident.of(
                accident.getId(),
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                this.storage.getAccidentTypeById(typeId),
                allRulesAccident
        );
        this.storage.addToStore(accidentToStore);
        LOG.info(MARKER, "AccidentControl save accident {}", accidentToStore);
    }

    private Set<Rule> idsConvertToRules(String[] ids) {
        Set<Rule> rules = new HashSet<>();
        Arrays.stream(ids).forEach(id -> rules.add(this.storage.getRuleById(Integer.parseInt(id))));
        return rules;
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

    public Collection<Rule> getAllRules() {
        return this.storage.getAllRules();
    }
}