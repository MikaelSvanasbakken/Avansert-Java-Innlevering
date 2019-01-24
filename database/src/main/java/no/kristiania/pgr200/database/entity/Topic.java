package no.kristiania.pgr200.database.entity;

public class Topic {

    private long id;
    private String topic;


    public Topic() {
    }

    public Topic(String topic) {
        topic = topic;
    }


    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }


    @Override
    public String toString() {
        return "\n" + topic;

    }
}
