package no.kristiania.pgr200.database.dao;

import no.kristiania.pgr200.database.entity.Talk;
import no.kristiania.pgr200.database.entity.TalkFormatted;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TalkDao extends AbstractDao {


    private String sqlSave = "INSERT INTO TALK (TITLE, DESCRIPTION, TIME_ID, ROOM_ID, TOPIC_ID) VALUES (?,?,?,?,?)";
    private String sqlUpdateTitle = "UPDATE TALK SET TITLE = ? WHERE ID = ? ";
    private String sqlUpdateTopic = "UPDATE TALK SET TOPIC_ID = ? WHERE ID = ? ";
    private String sqlUpdateDesc = "UPDATE TALK SET DESCRIPTION = ? WHERE ID = ? ";
    private String sqlGet = "SELECT * FROM TALK WHERE ID = ";
    private String sqlGetAll = "SELECT * FROM TALK";
    private String sqlGetAllByTopic = "SELECT * FROM TALK WHERE TOPIC_ID = ";


    public TalkDao(DataSource dataSource) {
        super(dataSource);
    }


    public void save(Talk talk) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            try (PreparedStatement statement = conn.prepareStatement(sqlSave, PreparedStatement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, talk.getTitle());
                statement.setString(2, talk.getDesc());
                statement.setInt(3, talk.getTimeId());
                statement.setInt(4, talk.getRoomId());
                statement.setInt(5, talk.getTopicId());
                statement.executeUpdate();

                try (ResultSet resultSet = statement.getGeneratedKeys()) {
                    resultSet.next();
                    talk.setId(resultSet.getInt(1));
                }
            }
        }
    }

    public void updateTitle(int id, String title) throws SQLException {
        addToStatement(id, title, sqlUpdateTitle);
    }

    public void updateDesc(int id, String desc) throws SQLException {
        addToStatement(id, desc, sqlUpdateDesc);
    }


    public void updateTopic(int id, int topic_id) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            try (PreparedStatement statement = conn.prepareStatement(sqlUpdateTopic)) {
                statement.setInt(1, topic_id);
                statement.setInt(2, id);
                statement.executeUpdate();

            }
        }
    }


    public Talk get(int id) throws SQLException {
        return get(sqlGet, this::mapToTalk, id);
    }

    @Override
    public String toString() {
        return "TalkDao{" +
                "sqlGetAll='" + sqlGetAll + '\'' +
                '}';
    }

    public List<Talk> getAll() throws SQLException {
        return list(sqlGetAll, this::mapToTalk);
    }

    public List<TalkFormatted> getAllTalkFormatted() throws SQLException {
        String sql = "SELECT ta.id, ta.title, ta.description, t2.topic, r.room, ti.time FROM TALK ta"
                + " INNER JOIN timeslot ti on (ti.id = ta.time_id)"
                + " INNER JOIN room r on (ta.room_id = r.id)"
                + " INNER JOIN topic t2 on ta.topic_id = t2.id ORDER BY ta.id";
        try (Connection conn = dataSource.getConnection()) {
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                try (ResultSet rs = statement.executeQuery()) {
                    List<TalkFormatted> result = new ArrayList<>();
                    while (rs.next()) {
                        TalkFormatted talkList = new TalkFormatted(rs.getInt("id"),
                                rs.getInt("time"),
                                rs.getString("title"),
                                rs.getString("description"),
                                rs.getString("topic"),
                                rs.getString("room"));
                        result.add(talkList);
                    }
                    return result;
                }
            }
        }
    }

    public List<Talk> getAllByTopic(int topicId) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            try (PreparedStatement statement = conn.prepareStatement(sqlGetAllByTopic + topicId)) {
                try (ResultSet rs = statement.executeQuery()) {
                    List<Talk> result = new ArrayList<>();
                    while (rs.next()) {
                        result.add(mapToTalk(rs));
                    }
                    return result;
                }
            }
        }
    }

    private Talk mapToTalk(ResultSet rs) throws SQLException {
        Talk talk = new Talk();
        talk.setId(rs.getInt("ID"));
        talk.setTitle(rs.getString("TITLE"));
        talk.setDesc(rs.getString("DESCRIPTION"));
        talk.setTimeId(rs.getInt("TIME_ID"));
        talk.setRoomId(rs.getInt("ROOM_ID"));
        talk.setTopicId(rs.getInt("TOPIC_ID"));

        return talk;
    }

    private void addToStatement(int id, String title, String sqlUpdate) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            try (PreparedStatement statement = conn.prepareStatement(sqlUpdate)) {
                statement.setString(1, title);
                statement.setLong(2, id);
                statement.executeUpdate();
            }
        }
    }
}
