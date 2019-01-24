package no.kristiania.pgr200.database.dao;

import no.kristiania.pgr200.database.entity.Topic;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TopicDao extends AbstractDao {

    public TopicDao(DataSource dataSource) {
        super(dataSource);
    }


    public List<Topic> getAll() throws SQLException {
        return list("SELECT * FROM TOPIC", this::mapToTopic);
    }


    public void save(Topic topic) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO TOPIC (TOPIC) VALUES (?)";
            try (PreparedStatement statement = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, topic.getTopic());
                statement.executeUpdate();

                try (ResultSet resultSet = statement.getGeneratedKeys()) {
                    resultSet.next();
                    topic.setId(resultSet.getInt(1));
                }
            }
        }
    }

    private Topic mapToTopic(ResultSet rs) throws SQLException {
        Topic topic = new Topic();
        topic.setId(rs.getInt("id"));
        topic.setTopic(rs.getString("topic"));

        return topic;
    }
}
