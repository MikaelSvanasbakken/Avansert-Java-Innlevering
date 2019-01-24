package no.kristiania.pgr200.database.dao;

import no.kristiania.pgr200.database.entity.Room;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class RoomDaoTest {

    @Test
    public void shouldInsertRoom() throws SQLException {
        RoomDao roomDao = new RoomDao(DataSourceTest.createTestDb());

        Room room = sampleRoom();

        roomDao.save(room);

        assertThat(roomDao.getAll())
                .extracting(r -> r.getName())
                .contains("Test Room");
    }

    @Test
    public void listAllRooms() throws SQLException {
        RoomDao roomDao = new RoomDao(DataSourceTest.createTestDb());

        //Remember that flyway already has populated 4 rooms.
        List<Room> rooms = sampleRooms();

        for (Room room : rooms) {
            roomDao.save(room);
        }
        assertThat(roomDao.getAll())
                .hasSize(6);
    }


    public Room sampleRoom() {
        Room room = new Room();

        room.setName("Test Room");

        return room;
    }

    public List<Room> sampleRooms() {
        ArrayList<Room> rooms = new ArrayList<>();

        Room room1 = new Room("Test Room 1");
        Room room2 = new Room("Test room 2");

        rooms.add(room1);
        rooms.add(room2);

        return rooms;
    }
}
