package no.kristiania.pgr200.database.entity;

import java.util.Objects;

public class Talk {

    private int id;
    private String title;
    private String desc;
    private int timeId;
    private int roomId;
    private int topicId;

    public Talk() {
    }

    public Talk(String title, String desc, int timeId, int roomId, int topicId) {
        this.title = title;
        this.desc = desc;
        this.timeId = timeId;
        this.roomId = roomId;
        this.topicId = topicId;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getTimeId() {
        return timeId;
    }

    public void setTimeId(int timeId) {
        this.timeId = timeId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Talk talk = (Talk) o;
        return getId() == talk.getId() &&
                getTimeId() == talk.getTimeId() &&
                Objects.equals(getTitle(), talk.getTitle()) &&
                Objects.equals(getDesc(), talk.getDesc());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getDesc(), getTimeId());
    }


}
