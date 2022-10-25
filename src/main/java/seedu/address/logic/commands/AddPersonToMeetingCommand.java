package seedu.address.logic.commands;

import static java.lang.Integer.parseInt;
import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;

/**
 * Creates a meeting with a person in the address book
 */
public class AddPersonToMeetingCommand extends Command {

    public static final String COMMAND_WORD = "addpersontomeeting";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds the list of people into the specified meeting based on the index from the displayed meetings.\n"
            + "Parameters: Meeting index ; NAMES OF PEOPLE (from address book) YOU ARE MEETING, (split names by , )\n"
            + "Example: " + COMMAND_WORD + "1 ; Alex Yeoh, Anna Lim";

    public static final String MESSAGE_ADD_PEOPLE_TO_MEETING_SUCCESS = "Added the list of persons";

    private final String info;

    private Index meetingIndex;

    public AddPersonToMeetingCommand(String info) {
        this.info = info;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException, ParseException {
        requireNonNull(model);
        String[] newPeopleInformation = this.info.split(";");
        String[] newPeople = newPeopleInformation[1].strip().split(",");
        meetingIndex = ParserUtil.parseIndex(newPeopleInformation[0].strip());

        if (meetingIndex.getZeroBased() >= model.getFilteredMeetingList().size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
        }

        ArrayList<Person> arrayOfPeopleToMeet = Meeting.convertNameToPerson(model, newPeople);
        Meeting meetingToUpdate = model.getFilteredMeetingList().get(meetingIndex.getZeroBased());
        model.deleteMeeting(meetingToUpdate);
        meetingToUpdate.addPersons(arrayOfPeopleToMeet);
        model.addMeeting(meetingToUpdate);

        return new CommandResult(String.format(MESSAGE_ADD_PEOPLE_TO_MEETING_SUCCESS));
    }

}
