package no.kristiania.pgr200.database.entity;

public class Timeslot {
    int id;
    int time;

    public Timeslot() {
    }

    public Timeslot(int time) {
        this.time = time;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return
                "\n" + "Klokkeslett: " + time;

    }
}
