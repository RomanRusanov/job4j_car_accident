package ru.job4j.accident.repository;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.Collection;

public interface DAO {

     Accident addToStore(Accident accident);

     Collection<Accident> getAllAccidents();

     Accident getAccidentById(int id);

     AccidentType getAccidentTypeById(int id);

     Collection<AccidentType> getAllAccidentTypes();

     Rule getRuleById(int id);

     Collection<Rule> getAllRules();
}
