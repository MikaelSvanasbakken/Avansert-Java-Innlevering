package no.kristiania.pgr200.database.dao;

import no.kristiania.pgr200.database.entity.Room;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RoomDao extends AbstractDao {

    public RoomDao(DataSource dataSource) {
        super(dataSource);
    }


    public List<Room> getAll() throws SQLException {
        return list("SELECT * FROM ROOM", this::mapToRoom);
    }

    public void save(Room room) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO ROOM (ROOM) VALUES (?)";
            try (PreparedStatement statement = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, room.getName());

                statement.executeUpdate();

                try (ResultSet resultSet = statement.getGeneratedKeys()) {
                    resultSet.next();
                    room.setId(resultSet.getInt(1));
                }
            }
        }

    }

    private Room mapToRoom(ResultSet rs) throws SQLException {
        Room room = new Room();
        room.setId(rs.getInt("ID"));
        room.setName(rs.getString("ROOM"));

        return room;
    }
}


