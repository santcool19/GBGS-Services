package com.gbgs.edu.application.programm.spring;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenericBasic {

    @Autowired
    static JdbcTemplate jdbcTemplate;

    public static void main(String args[]) {
    }

    private void springJdbcTemplate() {
        List<Student> studentList = new ArrayList<>();
        jdbcTemplate.execute("", new PreparedStatementCallback<Object>() {
            @Override
            public Object doInPreparedStatement(PreparedStatement preparedStatement) throws SQLException, DataAccessException {
                return preparedStatement.execute();
            }
        });

        List<Student> list = jdbcTemplate.query("", new Object[]{123}, new RowMapper<Student>() {
            @Override
            public Student mapRow(ResultSet resultSet, int i) throws SQLException {
                return null;
            }
        });

        jdbcTemplate.batchUpdate("", new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
            }

            @Override
            public int getBatchSize() {
                return 0;
            }
        });

        int[][] updateCounts = jdbcTemplate.batchUpdate(
                "insert into books (name, price) values(?,?)",
                studentList,
                101,
                new ParameterizedPreparedStatementSetter<Student>() {
                    public void setValues(PreparedStatement ps, Student argument)
                            throws SQLException {
                        ps.setString(1, argument.getName());
                        ps.setBigDecimal(2, new BigDecimal(argument.id));
                    }
                });

    }
}

@Data
@EqualsAndHashCode
@ToString
@Getter
class Student {
    String name;
    int rank;
    int id;

    public Student(int rank, String name, int id) {
        this.name = name;
        this.rank = rank;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

