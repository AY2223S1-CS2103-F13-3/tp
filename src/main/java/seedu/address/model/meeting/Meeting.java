package seedu.address.model.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.CreateMeetingCommand;
import seedu.address.model.Model;
import seedu.address.model.meeting.exceptions.ImpreciseMatchException;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.util.DateTimeConverter;

/**
 * Class for a new Meeting
 */
public class Meeting implements Comparable<Meeting> {

    private final ArrayList<Person> peopleToMeetArray;
    private final UniquePersonList peopleToMeetList = new UniquePersonList();
    private String meetingDescription;
    private String processedMeetingDateAndTime;
    private String meetingLocation;

    /**
     * Constructor for a new Meeting
     *
     * @param peopleToMeetArray the people whom the user is meeting with
     * @param meetingTitle the description/ title of the meeting
     * @param processedMeetingDateAndTime the date and time of meeting
     * @param meetingLocation the location of the meeting
     */
    public Meeting(ArrayList<Person> peopleToMeetArray, String meetingTitle,
                   String processedMeetingDateAndTime, String meetingLocation) {
        this.peopleToMeetArray = peopleToMeetArray;
        this.peopleToMeetList.setPersons(peopleToMeetArray);
        this.meetingDescription = meetingTitle;
        this.processedMeetingDateAndTime = processedMeetingDateAndTime;
        this.meetingLocation = meetingLocation;
    }

    /**
     * converts string array of the names of people to meet to array of Person objects
     *
     * @param model the model to implement
     * @param peopleToMeet the array of people names
     */
    public static ArrayList<Person> convertNameToPerson(Model model, String[] peopleToMeet)
            throws PersonNotFoundException {

        if (Objects.equals(peopleToMeet[0], "")) {
            throw new PersonNotFoundException();
        }

        ArrayList<Person> output = new ArrayList<>();
        // Takes in the name of the address book contact, split by words in the name
        for (String personName: peopleToMeet) {
            String[] nameKeywords = personName.strip().split("\\s+");
            NameContainsKeywordsPredicate personNamePredicate =
                new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords));

            // updates the list of persons in address book based on predicate
            model.updateFilteredPersonList(personNamePredicate);
            ObservableList<Person> listOfPeople = model.getFilteredPersonList();

            if (listOfPeople.isEmpty()) {
                throw new PersonNotFoundException();
            } else if (listOfPeople.size() > 1) {
                throw new ImpreciseMatchException();
            } else { // get the first person in the address book whose name matches
                output.add(listOfPeople.get(0));
            }

            // resets the list of persons after every search
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        }
        return output;
    }

    /**
     * modifies the location of the meeting
     *
     * @param location of the meeting
     */
    public void setMeetingLocation(String location) {
        this.meetingLocation = location;
    }

    /**
     * Adds the array of persons to the unique persons list
     * @param people the array list of people to be added to the meeting
     */
    public void addPersons(ArrayList<Person> people) {
        for (int i = 0; i < people.size(); i++) {
            this.peopleToMeetList.add(people.get(i));
        }
    }

    /**
     * Deletes the array of persons from the unique persons list
     * @param people
     */
    public void deletePersons(ArrayList<Person> people) {
        for (int i = 0; i < people.size(); i++) {
            this.peopleToMeetList.remove(people.get(i));
        }
    }

    public int getNumPersons() {
        return peopleToMeetList.getSize();
    }

    public ArrayList<Person> getArrayListPersonToMeet() {
        ArrayList<Person> a = new ArrayList<>();
        this.peopleToMeetList.iterator().forEachRemaining(a::add);
        return a;
    }

    public UniquePersonList getPersonToMeet() {
        return this.peopleToMeetList;
    }

    public String getDateAndTime() {
        return this.processedMeetingDateAndTime;
    }

    public String getNonProcessedDateAndTime() {
        return DateTimeConverter.processFullDateToLocalDatetime(this.processedMeetingDateAndTime);
    }

    public String getDescription() {
        return this.meetingDescription;
    }

    public String getLocation() {
        return this.meetingLocation;
    }

    public String getPeopleToMeetAsString() {
        String res = "";
        for (Person p : peopleToMeetList) {
            res = res + p.getName().fullName + ", ";
        }
        return res.substring(0, res.length() - 2);
    }

    /**
     * Returns true if both meetings include the same person to meet
     * and are at the same time.
     * This defines a weaker notion of equality between two meetings.
     */
    public boolean isSameMeeting(Meeting otherMeeting) {
        if (otherMeeting == this) {
            return true;
        }

        return otherMeeting != null
            && (otherMeeting.getPersonToMeet().equals(getPersonToMeet()))
            && (otherMeeting.getDateAndTime().equals(getDateAndTime()));
    }

    /**
     * Returns true if both meetings have the same data fields.
     * This defines a stronger notion of equality between two meetings.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Meeting)) {
            return false;
        }

        Meeting otherMeeting = (Meeting) other;
        return otherMeeting.getPersonToMeet().equals(getPersonToMeet())
            && otherMeeting.getDescription().equals(getDescription())
            && otherMeeting.getDateAndTime().equals(getDateAndTime())
            && otherMeeting.getLocation().equals(getLocation());
    }

    @Override
    public String toString() {
        return String.format("%1$s", CreateMeetingCommand.peopleToNameAndTagList(this.peopleToMeetArray))
            + "For: " + this.meetingDescription + "\n"
            + "On: " + this.processedMeetingDateAndTime + "\n"
            + "At: " + this.meetingLocation + "\n";
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * @param m the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     *     is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(Meeting m) {
        requireNonNull(m);
        LocalDateTime currObj = DateTimeConverter
            .processedStringToLocalDatetime(this.processedMeetingDateAndTime);
        LocalDateTime compObj = DateTimeConverter
            .processedStringToLocalDatetime(m.getDateAndTime());
        return currObj.compareTo(compObj);
    }
}
