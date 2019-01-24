package no.kristiania.pgr200.cli;


import no.kristiania.pgr200.database.DatabaseHandler.DatabaseHandler;
import no.kristiania.pgr200.database.entity.Talk;
import no.kristiania.pgr200.database.entity.Timeslot;
import no.kristiania.pgr200.database.entity.Topic;
import no.kristiania.pgr200.http.HttpRequest;
import no.kristiania.pgr200.http.HttpResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class Client {
    private DatabaseHandler dh;
    Map<String, String> talkParameters;
    Map<String, String> updateTalkParameters;
    HttpResponse response;

    public static void main(String[] args) throws IOException, SQLException {
        new Client();
    }

    private Client() throws IOException, SQLException {
        dh = new DatabaseHandler();
        int stopProgram = 0;
        int menu = 42;
        Scanner scanner = new Scanner(System.in);

        while (stopProgram < 1) {
            if (menu == 42) {
                System.out.println();
                System.out.println("<-----MENY----->");
                System.out.println("[42] Vis meny");
                System.out.println("[1] List alle Foredrag");
                System.out.println("[2] Legg til Foredrag");
                System.out.println("[3] Oppdater Foredrag");
                System.out.println("[4] Avslutt programm");
                System.out.print("Tast inn ditt valg: ");
            } else if (menu == 1) {
                //list All Talks
                System.out.println();
                System.out.println("----VISER ALLE FOREDRAG----");
                HttpResponse response = new HttpRequest("GET", "localhost", 12080, "db/talks").execute();
                System.out.println(response.getBody());
                System.out.println(("(trykk 42 for å se meny)"));


            } else if (menu == 2) {
                System.out.println();
                System.out.println("----Registrer foredrag----");

                //Insert Talk to database
                talkParameters = createTalkParameters();
                String path = HttpRequest.createPath("/db/talks", talkParameters);

                new HttpRequest("POST", "localhost", 12080, path).execute();

                System.out.println();
                System.out.println(("(trykk 42 for å se meny)"));
            } else if (menu == 3) {
                System.out.println();
                System.out.println("Hvilket foredrag vil du oppdatere?");
                response = new HttpRequest("GET", "localhost", 12080, "db/talks").execute();
                System.out.println();
                System.out.println(response.getBody());

                //Update Title of talk to database
                updateTalkParameters = updateTalkParameters();


                String pathTitle = HttpRequest.createPath("/db/talk/title", updateTalkParameters);
                String pathDesc = HttpRequest.createPath("/db/talk/desc", updateTalkParameters);
                String pathTopic = HttpRequest.createPath("/db/talk/topic", updateTalkParameters);
                //Update Desc of talk to database

                if (updateTalkParameters.containsKey("title")) {
                    new HttpRequest("PUT", "localhost", 12080, pathTitle).execute();
                }
                if (updateTalkParameters.containsKey("desc")) {
                    new HttpRequest("PUT", "localhost", 12080, pathDesc).execute();

                }
                if (updateTalkParameters.containsKey("topic")) {
                    new HttpRequest("PUT", "localhost", 12080, pathTopic).execute();

                }


            } else if (menu == 4) {
                System.out.println();
                System.out.println("----TAKK FOR NÅ!----");
                stopProgram = 1;
            }

            if (stopProgram != 1) {
                menu = scanner.nextInt();
            }
        }
    }

    public Map<String, String> updateTalkParameters() throws SQLException {
        Map<String, String> parameters = new HashMap<>();

        Scanner input = new Scanner(System.in);

        List<Talk> allAvailableTalks = dh.getAllAvailableTalks();

        System.out.print("Velg ID på konferanse du vil oppdatere: ");
        parameters.put("id", selectTalk(input, allAvailableTalks));
        System.out.println();
        System.out.println("Vil du oppdatere tittel, beskrivelse eller kategori?");
        System.out.print("Valg: ");

        String inputChoice = input.nextLine();

        if (inputChoice.equalsIgnoreCase("tittel")) {
            System.out.print("Ny tittel: ");
            String newTitle = input.nextLine();
            parameters.put("title", newTitle);
            System.out.println("Tittel oppdatert - trykk 42 for å se meny");

            return parameters;
        } else if (inputChoice.equalsIgnoreCase("beskrivelse")) {
            System.out.print("Ny beskrivelse: ");
            String newDesc = input.nextLine();
            parameters.put("desc", newDesc);
            System.out.println("Beskrivelse oppdatert - trykk 42 for å se meny");

            return parameters;

        } else if (inputChoice.equalsIgnoreCase("kategori")) {
            List<Topic> allAvailableTopics = dh.getAllAvailableTopics();


            parameters.put("topic", selectTopic(input, allAvailableTopics));
            System.out.println("Kategori oppdatert - trykk 42 for å se meny");

            return parameters;
        }
        return updateTalkParameters();
    }

    private String findTalkId(List<Talk> allAvailableTalks, String selectedTalkId) {
        for (Talk talk : allAvailableTalks) {
            if ((talk.getId() + "").equals(selectedTalkId)) {
                return talk.getId() + "";

            }
        }
        return null;
    }

    private Map<String, String> createTalkParameters() throws SQLException {
        Map<String, String> parameters = new HashMap<>();
        Scanner input = new Scanner(System.in);

        System.out.print("Legg til foredragtittel: ");
        parameters.put("title", input.nextLine());

        System.out.print("Legg til beskrivelse: ");
        parameters.put("description", input.nextLine());

        List<Timeslot> allAvailableTimeslots = dh.getAllAvailableTimeslots();
        parameters.put("time", selectTimeslot(input, allAvailableTimeslots));

        List<Topic> allAvailableTopics = dh.getAllAvailableTopics();
        parameters.put("topic", selectTopic(input, allAvailableTopics));
        return parameters;
    }

    private String selectTalk(Scanner input, List<Talk> allAvailableTalks) {

        String selectedTalk = input.nextLine();
        String talkId = findTalkId(allAvailableTalks, selectedTalk);
        if (talkId == null) {
            System.out.println("Fant ikke Foredrag, vennligst velg et foredrag fra listen");
            selectTalk(input, allAvailableTalks);
        }
        return talkId;
    }

    private String selectTopic(Scanner input, List<Topic> allAvailableTopics) {
        System.out.print("Skriv kategori på konferansen: ");
        System.out.println(allAvailableTopics.toString());
        System.out.println();
        System.out.println("Kategori:");

        String selectedTopic = input.nextLine();
        String topicId = findTopicId(allAvailableTopics, selectedTopic);
        if (topicId == null) {
            System.out.println("Fant ikke kategori, vennligst velg tilgjengelig kategori");
            selectTopic(input, allAvailableTopics);
        }
        return topicId;
    }

    /**
     * Metode for utvidelse - trengs for å liste ut foredrag etter kategorier - uimplementert...
     *
     * @param input              from user
     * @param allAvailableTopics
     * @return
     */

    private String listTopics(Scanner input, List<Topic> allAvailableTopics) {
        System.out.print("Skriv kategori du vil liste ut: ");
        System.out.println(allAvailableTopics.toString());

        String selectedTopic = input.nextLine();
        String topicId = findTopicId(allAvailableTopics, selectedTopic);
        if (topicId == null) {
            System.out.println("Fant ikke kategori, vennligst velg tilgjengelig kategori");
            selectTopic(input, allAvailableTopics);
        }
        return topicId;
    }

    private String findTopicId(List<Topic> allAvailableTopics, String selectedTopic) {
        for (Topic topic : allAvailableTopics) {
            if ((topic.getTopic().equalsIgnoreCase(selectedTopic))) {
                return topic.getId() + "";
            }
        }
        return null;
    }

    private String selectTimeslot(Scanner input, List<Timeslot> allAvailableTimeslots) {
        System.out.print("Legg til tidspunkt for foredrag: ");
        System.out.println(allAvailableTimeslots.toString());
        System.out.println();
        System.out.println("Tid: ");

        String selectedTimeSlot = input.nextLine();
        String timeslotId = findTimeslotId(allAvailableTimeslots, selectedTimeSlot);
        if (timeslotId == null) {
            System.out.println("Fant ikke tidspunkt, vennligst velg tilgjengelig tidspunkt.");
            selectTimeslot(input, allAvailableTimeslots);
        }
        return timeslotId;
    }

    private String findTimeslotId(List<Timeslot> allAvailableTimeslots, String selectedTimeSlot) {
        for (Timeslot timeslot : allAvailableTimeslots) {
            if ((timeslot.getTime() + "").equals(selectedTimeSlot)) {
                return timeslot.getId() + "";
            }
        }
        return null;
    }


}
