
CREATE TABLE IF NOT EXISTS ROOM (
  ID serial NOT NULL,
  ROOM VARCHAR(50),
  PRIMARY KEY(ID)
);
CREATE TABLE IF NOT EXISTS TOPIC (
  ID serial NOT NULL,
  TOPIC VARCHAR(100),
  PRIMARY KEY(ID)
);

CREATE TABLE IF NOT EXISTS TIMESLOT(
  ID serial NOT NULL,
  TIME int,
  ROOM_ID int,
  PRIMARY KEY(ID),
  FOREIGN KEY (ROOM_ID) REFERENCES ROOM(ID)
);
CREATE TABLE IF NOT EXISTS TALK (
  ID serial NOT NULL,
  TITLE VARCHAR(100),
  DESCRIPTION text,
  TIME_ID int,
  ROOM_ID int,
  TOPIC_ID int,
  PRIMARY KEY(ID),
  FOREIGN KEY(ROOM_ID) REFERENCES ROOM(ID),
  FOREIGN KEY(TOPIC_ID) REFERENCES TOPIC(ID),
  FOREIGN KEY(TIME_ID) REFERENCES TIMESLOT(ID)
);

INSERT INTO ROOM (room) VALUES ('Auditorium');
INSERT INTO ROOM (room) VALUES ('Room A');
INSERT INTO ROOM (room) VALUES ('Room B');
INSERT INTO ROOM (room) VALUES ('Open Mic');

INSERT INTO TIMESLOT (time) VALUES (1000);
INSERT INTO TIMESLOT (time) VALUES (1100);
INSERT INTO TIMESLOT (time) VALUES (1200);
INSERT INTO TIMESLOT (time) VALUES (1300);
INSERT INTO TIMESLOT (time) VALUES (1400);
INSERT INTO TIMESLOT (time) VALUES (1500);
INSERT INTO TIMESLOT (time) VALUES (1600);
INSERT INTO TIMESLOT (time) VALUES (1700);
INSERT INTO TIMESLOT (time) VALUES (1730);
INSERT INTO TIMESLOT (time) VALUES (1800);


INSERT INTO TOPIC (topic) VALUES ('Java');
INSERT INTO TOPIC (topic) VALUES ('Javascript');
INSERT INTO TOPIC (topic) VALUES ('Web development');
INSERT INTO TOPIC (topic) VALUES ('SQL');
INSERT INTO TOPIC (topic) VALUES ('Kotlin');
INSERT INTO TOPIC (topic) VALUES ('Python');


INSERT INTO TALK (title, description, time_id, room_id, topic_id)
VALUES ('Python for programmers' , 'Know the essentials of the Python language!', 1, 1, 6);
INSERT INTO TALK (title, description, time_id, room_id, topic_id)
VALUES ('Kubernetes workshop for Java developers', 'Learn about the various Kubernetes primitives (pods, services, replication controllers, ...)', 1, 2, 1);
INSERT INTO TALK (title, description, time_id, room_id, topic_id)
VALUES ('The Istio Workshop', 'In this workshop, we will give you a taste of Envoy and Istio', 2, 1, 1);
INSERT INTO TALK (title, description, time_id, room_id, topic_id)
VALUES ('Get started with React in 2 hours', 'In this workshop, you will learn how to create modern frontend applications with React', 2, 2, 3);
INSERT INTO TALK (title, description, time_id, room_id, topic_id)
VALUES ('The state of Jakarta EE', 'Attend this session to get the latest update on Jakarta EE', 3, 3, 1);
INSERT INTO TALK (title, description, time_id, room_id, topic_id)
VALUES ('Modern SQL: Evolution of a dinosaur', 'A lot has happened since SQL-92!', 4, 3, 4);


