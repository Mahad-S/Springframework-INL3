package se.yrgo.dataaccess;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import se.yrgo.domain.Action;

@Repository
public class ActionDaoJdbcTemplateImpl implements ActionDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(Action newAction) {
    }

    @Override
    public List<Action> getIncompleteActions(String userId) {
        return null;
    }

    @Override
    public void update(Action actionToUpdate) {
    }

    @Override
    public void delete(Action oldAction) {
    }
}