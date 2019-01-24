package no.kristiania.pgr200.database.dao;

import no.kristiania.pgr200.database.entity.Timeslot;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TimeslotDao extends AbstractDao {

    private String sqlGetAll = "SELECT * FROM TIMESLOT";
    private String sqlSave = "INSERT INTO TIMESLOT (TIME) VALUES (?)";

    public TimeslotDao(DataSource dataSource) {
        super(dataSource);
    }


    public List<Timeslot> getAll() throws SQLException {
        return list(sqlGetAll, this::mapToTime);
    }


    public void save(Timeslot timeslot) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            try (PreparedStatement statement = conn.prepareStatement(sqlSave, PreparedStatement.RETURN_GENERATED_KEYS)) {
                statement.setInt(1, timeslot.getTime());
                statement.executeUpdate();

                try (ResultSet resultSet = statement.getGeneratedKeys()) {
                    resultSet.next();
                    timeslot.setId(resultSet.getInt(1));
                }
            }
        }
    }

    private Timeslot mapToTime(ResultSet rs) throws SQLException {
        Timeslot timeslot = new Timeslot();
        timeslot.setId(rs.getInt("ID"));
        timeslot.setTime(rs.getInt("TIME"));

        return timeslot;
    }


}
