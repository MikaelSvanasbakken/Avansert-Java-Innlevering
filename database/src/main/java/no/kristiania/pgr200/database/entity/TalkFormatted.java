package no.kristiania.pgr200.database.entity;

public class TalkFormatted {

    private static final String COLUMNFORMAT = "%-3s %-40s %-100s %-15s %-15s %-8s\n";
    int id, time;
    String title, description, topic, room;

    public TalkFormatted(int id, int time, String title, String description, String topic, String room) {
        this.id = id;
        this.time = time;
        this.title = title;
        this.description = description;
        this.topic = topic;
        this.room = room;
    }

    public int getId() {
        return id;
    }

    public int getTime() {
        return time;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getTopic() {
        return topic;
    }

    public String getRoom() {
        return room;
    }

    public static String createHeaderLine() {
        return String.format(COLUMNFORMAT, "|ID|", "|TITLE|", "|DESCRIPTION|", "|TOPIC|", "|ROOM|", "|TIME|");
    }

    public String formatOutput() {
        return String.format(COLUMNFORMAT, id, title, description, topic, room, time);
    }
}
