package no.kristiania.pgr200.database.dao;

import no.kristiania.pgr200.database.entity.Talk;
import org.flywaydb.core.Flyway;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.Assert.assertEquals;


public class TalkDaoTest {


    @Before
    public void init() {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        dataSource.setUser("sa");

        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource);
        flyway.clean();
        flyway.migrate();
    }

    @Test
    public void shouldInsertTalk() throws SQLException {
        TalkDao daoh2 = new TalkDao(DataSourceTest.createTestDb());


        Talk talk = sampleTalk();
        talk.setTimeId(1);
        talk.setRoomId(4);
        talk.setTopicId(1);
        daoh2.save(talk);


        assertThat(daoh2.getAll())
                .extracting(t -> t.getTitle())
                .contains("Java 101");
        assertThat(daoh2.getAll())
                .extracting(t -> t.getId())
                .contains((long) 1);
        assertThat(daoh2.getAll())
                .extracting(t -> t.getDesc())
                .contains("Description");
        assertThat(daoh2.getAll())
                .extracting(t -> t.getTimeId())
                .contains(1);
        assertThat(daoh2.getAll())
                .extracting(t -> t.getRoomId())
                .contains(4);
        assertThat(daoh2.getAll())
                .extracting(t -> t.getTopicId())
                .contains(1);

    }


    @Test
    public void shouldListAllTalks() throws SQLException {
        TalkDao daoh2 = new TalkDao(DataSourceTest.createTestDb());

        List<Talk> talks = sampleTalks();

        for (Talk talk : talks) {
            daoh2.save(talk);
        }

        assertEquals(9, daoh2.getAll().size());
    }


    @Test
    public void getTalkById() throws SQLException {
        TalkDao daoh2 = new TalkDao(DataSourceTest.createTestDb());

        List<Talk> talks = sampleTalks();

        for (Talk talk : talks) {
            daoh2.save(talk);
        }

        assertThat(daoh2.get(1))
                .extracting(t -> t.getTitle())
                .isEqualTo("Python for programmers");

    }

    @Test
    public void getTalksByTopic() throws SQLException {
        TalkDao daoh2 = new TalkDao(DataSourceTest.createTestDb());

        List<Talk> talks = sampleTalks();

        for (Talk talk : talks) {
            daoh2.save(talk);
        }

        assertThat(daoh2.getAllByTopic(2))
                .hasSize(1);

    }

    @Test
    public void updateTitleTalk() throws SQLException {
        TalkDao daoh2 = new TalkDao(DataSourceTest.createTestDb());

        Talk talk1 = sampleTalk();

        daoh2.save(talk1);

        daoh2.updateTitle(1, "Java 666");

        assertThat(daoh2.get(1))
                .extracting(t -> t.getTitle())
                .isEqualTo("Java 666");

    }

    @Test
    public void updateDescTalk() throws SQLException {
        TalkDao daoh2 = new TalkDao(DataSourceTest.createTestDb());

        Talk talk = sampleTalk();

        daoh2.save(talk);

        daoh2.updateDesc(1, "Description 2");

        assertThat(daoh2.get(1))
                .extracting(t -> t.getDesc())
                .isEqualTo("Description 2");
    }

    @Test
    public void updateTopicTalk() throws SQLException {
        TalkDao daoh2 = new TalkDao(DataSourceTest.createTestDb());

        Talk talk = sampleTalk();

        daoh2.save(talk);

        daoh2.updateTopic(1, 2);

        assertThat(daoh2.get(1))
                .extracting(t -> t.getTopicId())
                .isEqualTo(2);
    }

    private Talk sampleTalk() {
        Talk talk = new Talk();
        talk.setTitle("Java 101");
        talk.setDesc("Description");
        talk.setTimeId(3);
        talk.setRoomId(4);
        talk.setTopicId(1);

        return talk;
    }


    private List<Talk> sampleTalks() {
        Talk talk1 = new Talk("Java 102", "Java for beginners", 1, 1, 1);
        Talk talk2 = new Talk("Java 103", "java for notsobeginners", 2, 2, 1);
        Talk talk3 = new Talk("JavaScript 2", "javascript desc", 3, 3, 2);

        List<Talk> talks = new ArrayList<>();
        talks.add(talk1);
        talks.add(talk2);
        talks.add(talk3);

        return talks;
    }
}
