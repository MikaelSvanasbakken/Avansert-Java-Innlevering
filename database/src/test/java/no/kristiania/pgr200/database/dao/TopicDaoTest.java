package no.kristiania.pgr200.database.dao;

import no.kristiania.pgr200.database.entity.Topic;
import org.junit.Test;

import java.sql.SQLException;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class TopicDaoTest {

    @Test
    public void shouldListAllTopics() throws SQLException {
        TopicDao topicDao = new TopicDao(DataSourceTest.createTestDb());

        assertThat(topicDao.getAll())
                .hasSize(6);

        Topic newTopic = new Topic("SqlInjection");

        topicDao.save(newTopic);

        assertThat(topicDao.getAll())
                .hasSize(7);

    }


}
