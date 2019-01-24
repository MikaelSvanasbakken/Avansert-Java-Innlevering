package no.kristiania.pgr200.database.DatabaseHandler;

import no.kristiania.pgr200.database.dao.TalkDao;
import no.kristiania.pgr200.database.dao.TimeslotDao;
import no.kristiania.pgr200.database.dao.TopicDao;
import no.kristiania.pgr200.database.dataSource.DatabaseConnector;
import no.kristiania.pgr200.database.entity.Talk;
import no.kristiania.pgr200.database.entity.TalkFormatted;
import no.kristiania.pgr200.database.entity.Timeslot;
import no.kristiania.pgr200.database.entity.Topic;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class DatabaseHandler {

    // Establish database connection
    DatabaseConnector dbConnector = new DatabaseConnector();
    DataSource conferenceDb = dbConnector.connect();

    //DAO OBJECT
    TalkDao talk = new TalkDao(conferenceDb);
    TopicDao topicDao = new TopicDao(conferenceDb);
    TimeslotDao timeDao = new TimeslotDao(conferenceDb);


    public DatabaseHandler() throws SQLException {
    }

    public String listAllTalks() throws SQLException {
        //List all talks
        List<TalkFormatted> talks = talk.getAllTalkFormatted();

        if (talks.isEmpty()) {
            System.out.println("Det er ingen registrerte foredrag. Vennligst legg til.");
        }

        StringBuilder response = new StringBuilder();
        response.append(TalkFormatted.createHeaderLine());
        for (TalkFormatted talk : talks) {
            response.append(talk.formatOutput());
        }
        return response.toString();
    }


    public void insertTalk(Map<String, String> parameters) throws SQLException {
        Talk tempTalk = new Talk();

        String title = parameters.get("title");
        String desc = parameters.get("description");
        int topicId = Integer.parseInt(parameters.get("topic"));
        int timeslotId = Integer.parseInt(parameters.get("time"));


        tempTalk.setTitle(title);
        tempTalk.setDesc(desc);
        tempTalk.setTimeId(timeslotId);
        tempTalk.setTopicId(topicId);
        tempTalk.setRoomId(4);

        talk.save(tempTalk);

    }

    public void updateTalkTitle(Map<String, String> parameters) throws SQLException {


        int id = Integer.parseInt(parameters.get("id"));
        String title = parameters.get("title");


        talk.updateTitle(id, title);
    }

    public void updateTalkDesc(Map<String, String> parameters) throws SQLException {


        int id = Integer.parseInt(parameters.get("id"));
        String desc = parameters.get("desc");

        talk.updateDesc(id, desc);
    }

    public void updateTalkTopic(Map<String, String> parameters) throws SQLException {


        int id = Integer.parseInt(parameters.get("id"));
        int topic = Integer.parseInt(parameters.get("topic"));

        talk.updateTopic(id, topic);
    }

    public List<Timeslot> getAllAvailableTimeslots() throws SQLException {
        return timeDao.getAll();
    }

    public List<Topic> getAllAvailableTopics() throws SQLException {
        return topicDao.getAll();
    }

    public List<Talk> getAllAvailableTalks() throws SQLException {
        return talk.getAll();
    }
}
