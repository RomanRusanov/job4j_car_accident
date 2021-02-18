package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Roman Rusanov
 * @version 0.1
 * @since 18.02.2021
 * email roman9628@gmail.com
 */
@Repository
public class AccidentSpringData implements DAO {

    private AccidentRepository accidentRepository;
    private AccidentTypeRepository accidentTypeRepository;
    private RulesRepository rulesRepository;

    public AccidentSpringData(AccidentRepository accidentRepository,
                              AccidentTypeRepository accidentTypeRepository,
                              RulesRepository rulesRepository)
    {
        this.accidentRepository = accidentRepository;
        this.accidentTypeRepository = accidentTypeRepository;
        this.rulesRepository = rulesRepository;
    }

    @Override
    public Accident addToStore(Accident accident) {
        this.accidentRepository.save(accident);
        return accident;
    }

    @Override
    public Collection<Accident> getAllAccidents() {
        List<Accident> allAccidents = new ArrayList<>();
        accidentRepository.findAll().forEach(allAccidents::add);
        return allAccidents;
    }

    @Override
    public Accident getAccidentById(int id) {
        return this.accidentRepository.findById(id).get();
    }

    @Override
    public AccidentType getAccidentTypeById(int id) {
        return this.accidentTypeRepository.findById(id).get();
    }

    @Override
    public Collection<AccidentType> getAllAccidentTypes() {
        List<AccidentType> allAccidentTypes = new ArrayList<>();
        accidentTypeRepository.findAll().forEach(allAccidentTypes::add);
        return allAccidentTypes;
    }

    @Override
    public Rule getRuleById(int id) {
        return this.rulesRepository.findById(id).get();
    }

    @Override
    public Collection<Rule> getAllRules() {
        List<Rule> allRules = new ArrayList<>();
        rulesRepository.findAll().forEach(allRules::add);
        return allRules;
    }
}