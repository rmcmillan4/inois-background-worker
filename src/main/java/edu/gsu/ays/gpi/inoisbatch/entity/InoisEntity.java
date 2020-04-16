package edu.gsu.ays.gpi.inoisbatch.entity;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public abstract class InoisEntity {

    private static JdbcTemplate jdbcTemplate;
    private static List<InoisEntity> batch = new ArrayList<>();

    public InoisEntity(){}

    public InoisEntity(JdbcTemplate entityJdbcTemplate){
        jdbcTemplate = entityJdbcTemplate;
    }

    public void hash() {
        throw new NotImplementedException("No attribute hashing method is defined for the current entity");
    }

    public static void writeBatch(List<InoisEntity> instances){
        throw new NotImplementedException("No batch database write method is defined for the current entity");
    }

    public static void addToBatch(InoisEntity entity){
        batch.add(entity);
    }
}
