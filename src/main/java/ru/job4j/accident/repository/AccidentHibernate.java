package ru.job4j.accident.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.Collection;
import java.util.function.Function;

@Repository
public class AccidentHibernate implements DAO {
    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public Accident addToStore(Accident accident) {
        return this.tx(session -> {
            session.saveOrUpdate(accident);
            return accident;
        });
    }

    @Override
    public Collection<Accident> getAllAccidents() {
        return this.tx(
                session -> {
                    return session.createQuery(
                    "select distinct a from accident a " +
                            "join fetch a.type " +
                            "join fetch a.rules", Accident.class)
                    .list();
                }
        );
    }

    @Override
    public Accident getAccidentById(int id) {
        return this.tx(
                session -> {
                    final Query query = session.createQuery(
                            "select a from accident a where a.id=:a_id");
                    query.setParameter("a_id", id);
                    return (Accident) query.uniqueResult();
                }
        );
    }

    @Override
    public AccidentType getAccidentTypeById(int id) {
        return this.tx(
                session -> {
                    final Query query = session.createQuery(
                            "select at from accident_type at where at.id=:at_id");
                    query.setParameter("at_id", id);
                    return (AccidentType) query.uniqueResult();
                }
        );
    }

    @Override
    public Collection<AccidentType> getAllAccidentTypes() {
        return this.tx(
                session -> {
                    return session.createQuery(
                    "select at from accident_type at", AccidentType.class)
                    .list();
                }
        );
    }

    @Override
    public Rule getRuleById(int id) {
        return this.tx(
                session -> {
                    final Query query = session.createQuery(
                            "select r from rules r where r.id=:r_id");
                    query.setParameter("r_id", id);
                    return (Rule) query.uniqueResult();
                }
        );
    }

    @Override
    public Collection<Rule> getAllRules() {
        return this.tx(
                session -> {
                    return session.createQuery(
                    "select r from rules r", Rule.class)
                    .list();
                }
        );
    }
}