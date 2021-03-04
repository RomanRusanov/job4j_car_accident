package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.sql.PreparedStatement;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class AccidentJdbcTemplate implements DAO {
    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Accident addToStore(Accident accident) {
        if (existInDBUserWithID(accident.getId())) {
            updateAccident(accident);
        } else {
            saveAccident(accident);
        }
        return accident;
    }

    private Boolean existInDBUserWithID(int id) {
        return jdbc.queryForObject(
                "select exists(select 1 from accident as a where a.id = ?);",
                Boolean.class, id
        );
    }

    private void updateAccident(Accident accident) {
        jdbc.update(
                "update accident as a set name = ?, text = ?, address = ?, accident_type_id = ? " +
                        "where id = ?;",
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getType().getId(),
                accident.getId()
        );
        jdbc.update(
                "delete from accident_rules where accident_id = ?;",
                accident.getId()
        );
        accident.getRules().forEach(rule ->
            jdbc.update(
                    "insert into accident_rules as ar values (?, ?);",
                    accident.getId(),
                    rule.getId()
            )
        );
    }

    private void saveAccident(Accident accident) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(connection ->
                {
                    PreparedStatement ps = connection.prepareStatement(
                            "insert into accident (name, text, address, accident_type_id) " +
                                    "values (?, ?, ?, ?)",
                            new String[] { "id" }
                    );
                    ps.setString(1, accident.getName());
                    ps.setString(2, accident.getText());
                    ps.setString(3, accident.getAddress());
                    ps.setInt(4, accident.getType().getId());
                    return ps;
                }, keyHolder
        );
        Integer newIdGeneratedDB = (Integer) keyHolder.getKey();
        accident.getRules().forEach(rule ->
                jdbc.update(
                        "insert into accident_rules as ar values (?, ?);",
                        newIdGeneratedDB,
                        rule.getId()
                )
        );
    }

    @Override
    public Accident getAccidentById(int id) {
        return jdbc.queryForObject(
                "select a.id, a.name, a.text, a.address, t.id as type_id, t.name as type_name from accident as a " +
                        "left join accident_type as t on a.id = t.id where a.id = ?;",
                accidentRowMapper
                ,id
        );
    }

    @Override
    public Collection<Accident> getAllAccidents() {
        return jdbc.query(
                "select a.id, a.name, a.text, a.address, at.id as type_id, at.name as type_name from accident as a " +
                        "left join accident_type as at on a.accident_type_id = at.id;",
                accidentRowMapper
        );
    }

    private final RowMapper<Accident> accidentRowMapper = (rs, rowNum) -> {
        Accident accident = new Accident();
        accident.setId(rs.getInt("id"));
        accident.setName(rs.getString("name"));
        accident.setText(rs.getString("text"));
        accident.setAddress(rs.getString("address"));
        accident.setType(AccidentType.of(
                rs.getInt("type_id"),
                rs.getString("type_name")));
        accident.setRules(this.retireAllRulesForAccident(accident.getId()));
        return accident;
    };

    private Set<Rule> retireAllRulesForAccident(int id) {
        List<Rule> list = jdbc.query("select r.id, r.name from rules as r " +
                        "left join accident_rules as ar on r.id = ar.rules_id " +
                        "where ar.accident_id = ?;",
                (rs, row) -> {
                    Rule rule = Rule.of(
                            rs.getInt("id"),
                            rs.getString("name")
                    );
                    return rule;
                }, id);
        return new HashSet<>(list);
    }

    @Override
    public AccidentType getAccidentTypeById(int id) {
        return jdbc.queryForObject(
                "select id, name from accident_type as at where at.id = ?;",
                accidentTypeRowMapper, id
        );
    }

    @Override
    public Collection<AccidentType> getAllAccidentTypes() {
        return jdbc.query("select id, name from accident_type;", accidentTypeRowMapper);
    }

    private final RowMapper<AccidentType> accidentTypeRowMapper = (resultSet, rowNum) -> {
        AccidentType at = new AccidentType();
        at.setId(resultSet.getInt("id"));
        at.setName(resultSet.getString("name"));
        return at;
    };

    @Override
    public Rule getRuleById(int id) {
        return jdbc.queryForObject(
                "select id, name from rules as r where r.id = ?;",
                ruleRowMapper, id
        );
    }

    @Override
    public Collection<Rule> getAllRules() {
        return jdbc.query("select id, name from rules;", ruleRowMapper);
    }

    private final RowMapper<Rule> ruleRowMapper = (resultSet, rowNum) -> {
        Rule rule = new Rule();
        rule.setId(resultSet.getInt("id"));
        rule.setName(resultSet.getString("name"));
        return rule;
    };
}