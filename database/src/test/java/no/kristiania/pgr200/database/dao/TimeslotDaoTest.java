package no.kristiania.pgr200.database.dao;

import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class TimeslotDaoTest {


    @Test
    public void shouldListAllTimeslots() throws SQLException {
        TimeslotDao timeDao = new TimeslotDao(DataSourceTest.createTestDb());

        assertEquals(10, timeDao.getAll().size());
    }

}
