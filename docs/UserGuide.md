---
layout: page
title: User Guide
---

Yellow Pages (YP) is a desktop app for managing contacts, optimised for use via a Graphical User Interface (GUI). If you can type fast, YP can get your contacts and scheduling done faster than traditional GUI apps.

* Quick Start
* Features
  * Viewing help
  * Adding a contact
  * Listing all contacts
  * Editing a contact
  * Locating contact by name
  * Locating contact by tag
  * Deleting a contact
  * Clearing all contacts
  * Adding a meeting
  * Add contact to meeting
  * Delete contact from meeting
  * Edit meeting details
  * Listing all meetings
  * Finding meetings
  * Filtering meetings
  * Sorting meetings
  * Exiting the program
  * Saving the data
* FAQ
* Command Summary

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `.jar` from [here](https://github.com/AY2223S1-CS2103-F13-3/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/UiUpdate.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

    * **`list`** : Lists all contacts.

    * **`add`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.

    * **`delete`**`3` : Deletes the 3rd contact shown in the current list.

    * **`clear`** : Deletes all contacts.

    * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a person: `add`

Adds a person to the address book.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

### Listing all persons : `list`

Shows a list of all persons in the address book.

Format: `list`

### Editing a person : `edit`

Edits an existing person in the address book.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
  specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Locating persons by name: `find`

Finds persons whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Locating contact by tag: `findtag`

Find persons whose tags contain the corresponding tag.

Format: `findtag [MORE_TAG]`

* The search is case-insensitive. e.g `Friend` will match `friend`
* Only the Tag is searched.
* Only full words will be matched e.g. `Friend` will not match `Friends`

### Deleting a person : `delete`

Deletes the specified person from the address book.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Adding a meeting : `addmeeting`

Adds a meeting to the meeting list.

Format: `addmeeting MEETING_TITLE /at MEETING_DATE [/description MEETING_DESCRIPTION] [/contact INDEX]`

* `index` must be a positive integer corresponding to a contact

### Add contacts to meeting: `addpersontomeeting`
Adds the list of contacts to the specified meeting.

Format: `addpersontomeeting MEETING_INDEX; [NAMES]`

* The index refers to the index number shown in the displayed meeting list.
* The index **must be a positive integer** 1, 2, 3, …​

### Delete contacts from meeting: `deletepersonfrommeeting`
Deletes the list of contacts from the specified meeting.

Format: `deletepersonfrommeeting MEETING_INDEX; [NAMES]`

* Will not be able to delete person from meeting if only 1 person left in the meeting.
* The index refers to the index number shown in the displayed meeting list.
* The index **must be a positive integer** 1, 2, 3, …​

### Editing a meeting : `editmeeting`

Edits an existing meeting in the address book.

Format: `editmeeting INDEX [d/DESCRIPTION] [dd/DATE] [l/LOCATION]`

* Edits the meeting at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

Examples:
*  `editmeeting 1 d/test 1 l/nus` Edits the description and location of the 1st meeting to be `test 1` and `nus` respectively.
*  `editmeeting 2 dd/23-09-2022 2359` Edits the date of the 2nd meeting to be `23-09-2022 2359`.

### Listing all meetings : `listmeeting`

Shows a list of all meetings in the address book and console.

### Searching a meeting: `findmeeting`

Finds a meeting by the meeting's description, location or people 

Three Formats: 
- Find by description: `findmeeting /named MEETING_TITLE` 
- Find by people: `findmeeting /with PERSON_NAME` 
- Find by location: `findmeeting /at LOCATION`

For all three formats:
* Only **one** format can be used at a time e.g. they **cannot** be chained like: `findmeeting /named abc /at abc`
* The search is case-insensitive. e.g. `project` will match `Project`
* Only full words will be matched e.g. `Project` will not match `Projects`
* The order of the keywords does not matter. e.g. `Project CS2103` will match `CS2103 Project`
* Only the field specified by the format (description/people/location) is searched.
* Meeting fields matching at least one keyword will be returned (i.e. `OR` search). 
  e.g. `CS2100 CS2103` will return meetings with `CS2100 Project`, `CS2103 Project` and
  `Project` will also return meetings with  `CS2100 Project`, `CS2103 Project`

Examples: 
* `findmeeting /at UTown Com1`
* `findmeeting /with Jane John Simon`
* `findmeeting /named CS2103`

### Filtering Meetings: `filtermeetingsbetween`

Format: `filtermeetingsbetween AFTER_DATE ;;; BEFORE_DATE`

**Note** - all date formats must follow the `dd-MM-yyyy HHmm` format 

Can be split into three cases:
1. `AFTER_DATE` < `BEFORE_DATE` - Lists out all meetings with dates between `AFTER_DATE` and `BEFORE_DATE` not inclusive 
2. `AFTER_DATE` = `BEFORE_DATE` - Lists out all meetings with dates **matching** `AFTER_DATE` which equals to `BEFORE_DATE` 
3. `AFTER_DATE` > `BEFORE_DATE` - Error

Examples: 
* `findmeetingsbetween 10-10-2022 1050 ;;; 12-10-2022 1200`
* `findmeetingsbetween 10-11-2022 0000 ;;; 12-11-2022 0000`
* `findmeetingsbetween 10-11-2022 1000 ;;; 10-11-2022 1000`

### Sort Meetings: `sortmeetings`

Format: `sortmeetings asc` or `sortmeetings desc`

- Sorts the list of meetings by ascending or descending date order 
- Has two parameter types `asc` and `desc` which sorts the list by ascending order or descending order
- Is case-insensitive, parameters can be `ASC` or `aSc` etc...

Examples:
- `sortmeetings ASC`
- `sortmeetings DESC`
- `sortmeetings asc`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

AddressBook data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action             | Format, Examples                                                                                                                                                      |
|--------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**            | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague` |
| **Clear**          | `clear`                                                                                                                                                               |
| **Delete**         | `delete INDEX`<br> e.g., `delete 3`                                                                                                                                   |
| **Edit**           | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`                                           |
| **Find**           | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`                                                                                                            |
| **Find Tag**       | `find TAG [MORE_TAGS]`<br> e.g., `find friend CS2100`                                                                                                                 |
| **Add Meeting**    | `addmeeting MEETING_TITLE /at MEETING_DATE [/description MEETING_DESCRIPTION] [/contact INDEX]` <br> e.g., `addmeeting Project Meeting /at 29 Jan 2022`               |
| **Find Meeting**   | `findmeeting /named MEETING_TITLE` <br> e.g., findmeeting /named CS2103                                                                                               |
| **Filter Meeting** | `filtermeetingsbetween AFTER_DATE ;;; BEFORE_DATE` <br> e.g., filtermeetingsbetween 10-10-2022 1010 ;;; 11-10-2022 1010                                               |
| **Sort Meetings**  | `sortmeetings asc` or `sortmeetings desc`                                                                                                                             |
| **List**           | `list`                                                                                                                                                                |
| **Help**           | `help`                                                                                                                                                                |

