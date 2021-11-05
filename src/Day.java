package appointmentplanner;

import appointmentplanner.util.Priority;
import appointmentplanner.util.Queue;
import appointmentplanner.util.Stack;
import java.util.*;
//TODO

/**
 *
 * @author tanja
 */
public final class Day {

    public final static String[] DAYS = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    public final static Time DAY_START = new Time(8, 30);
    public final static Time DAY_END = new Time(17, 30);
    public final static Time LUNCH_TIME = new Time(12, 30);
    public final static TimeSpan DAY_PART = new TimeSpan(4, 0);
    public final static Appointment LUNCH_BREAK = new Appointment("lunch break", new TimeSpan(1, 0));

    private Node<Appointment> lunchBreakNode;
    private Node<Appointment> dummyHead;
    private Node<Appointment> dummyTail;
    private Node<Appointment> lastNode;
    private Node<Appointment> runner;
    public Node<Appointment> testNode;
    private final int nr;
    private boolean weekend = false;

    /**
     * The number of appointments added by the user.
     */
    private int nrOfAppointments = 0;

    private static class Node<E> {

        E item;
        Node<E> next;

        Node(E item) {
            this.item = item;
            next = null;
        }
    }

    /**
     * Creates a Day object with no appointments. A week does not contain a
     * weekend: no appointments on Saturdays or Sundays. Days of the week are
     * numbered as follows: Monday = 1 .. Friday = 5.
     *
     * @param nr day of the week: Monday = 1 .. Sunday = 7.
     */
    public Day(int nr) {
        dummyHead = new Node<>(null);
        dummyTail = new Node<>(null);
        testNode = new Node(null);

        LUNCH_BREAK.setStart(LUNCH_TIME);
        this.nr = nr;

        if (nr == 6 || nr == 7) {
            dummyHead.next = dummyTail;
            weekend = true;
            this.nrOfAppointments = 0;
        } else {
            lunchBreakNode = new Node<>(LUNCH_BREAK);

            dummyHead.next = lunchBreakNode;
            lunchBreakNode.next = dummyTail;
            this.nrOfAppointments = 1;
        }

    }

    /**
     * Returns the number of appointments on this day, including the lunch break
     * which is always present.
     *
     * @return Number of appointments on this day!
     */
    public int getNrOfAppointments() {
        return this.nrOfAppointments;
    }

    // METHODS INTRODUCED IN WEEK 3 OF APPOINTMENT PROJECT
    /**
     * Returns the name of the day in English. So if number of this day is 1,
     * the return value is "Monday" etc.
     *
     * @return String holding name of this day.
     */
    public String getNameOfTheDay() {
        return DAYS[nr - 1];
    }

    /**
     * Checks if an appointment of given duration can be made. Returns true if
     * an appointment can be made, false otherwise.
     *
     * @param duration time length of appointment
     * @return true if there is a gap of at least the size of duration in the
     * planning.
     */
    public boolean canAddAppointmentOfDuration(TimeSpan duration) {
        boolean run = true;
        runner = dummyHead;
        TimeSpan possibleTimeSpanBetweenNodes = null;
        TimeSpan neededTimeSpanBetweenNodes = duration;
        boolean initialized = false;

        while (run) {

            //If a Node with an appointment in it is present.
            if (runner.item != null) {
                if (runner.item.getStart() == null) {
                } else {
                    // This Appointment EndTime and The NextAppointment StartTime is given
                    if (runner.next.item != null) {
                        if (runner.next.item.getStart() != null) {
                            possibleTimeSpanBetweenNodes = new TimeSpan(runner.item.getEnd(), runner.next.item.getStart());
                            if (possibleTimeSpanBetweenNodes.isSmallerThan(neededTimeSpanBetweenNodes)) {
                                initialized = false;
                            } else {
                                initialized = true;
                            }

                        }
                    }
                    // This Appointment EndTime is given and The NextAppointment StartTime is not given
                    // startTime of next will be the EndOfTheDay
                    if (runner.next.item == null) {
                        possibleTimeSpanBetweenNodes = new TimeSpan(runner.item.getEnd(), DAY_END);
                        initialized = true;
                    }
                }

            }
            // This can only Happen if The current Node is the DummyHead
            if (runner.item == null) {
                //This can only happen if lunch appointment will be deleted manualy
                //And there is no Appointment more in between the two dummys
                if (runner.next.item == null) {
                    possibleTimeSpanBetweenNodes = new TimeSpan(DAY_START, DAY_END);
                    initialized = true;
                    break;
                }
                if (runner.next.item.getStart() != null) {
                    if (runner.item == null) {
                        if (runner.next.item.getStart() != DAY_START) {
                            possibleTimeSpanBetweenNodes = new TimeSpan(DAY_START, runner.next.item.getStart());
                            if (possibleTimeSpanBetweenNodes.isSmallerThan(neededTimeSpanBetweenNodes)) {
                                initialized = false;
                            } else if (possibleTimeSpanBetweenNodes.equals(neededTimeSpanBetweenNodes)) {
                                initialized = false;

                            } else {
                                initialized = true;
                            }
                        } else {
                            initialized = false;
                        }

                    }

                }

            }

            if (initialized) {
                break;
            }

            runner = runner.next;
        }

        //After initialzing the possibleTimeSpanBetweenNodes, the Result Compare take place here
        if (initialized) {
            if (neededTimeSpanBetweenNodes.isSmallerThan(possibleTimeSpanBetweenNodes)) {
                return true;
            }
        }
        return false;

    }

    /**
     * Adds a new appointment to this day. The description of the appointment
     * should be unique for this day. Objective is to insert an appointment in
     * such a way that the list keeps all of its appointments ordered in time.
     * <pre>
     * pre:  The start time of the appointment has been set.<br>
     *       The start time lies outside the time spans of other appointments.
     *       The appointment does not overlap with existing appointments on this day.
     * post: The appointment has been added to this day.
     * </pre> In case the pre-conditions are not fulfilled, the appointment will
     * not be added to this day.
     *
     * @param appointment start time of the appointment has been set in advance
     */
    public void addAppointmentWithStartTimeSet(Appointment appointment
    ) {
        if (appointment.getStart() == null) {
            System.out.println("ERROR: No StartTime Set for the given Appointment [EXIT]");

        } else {
            addAppointment(appointment);

        }
    }

    /**
     * Adds a new appointment to this day. The method searches a time gap for an
     * appointment of given duration. This will be done according to the first
     * fit approach. The description of the appointment should be unique for
     * this day. The start time will be set by this method, and the appointment
     * is added to this day. The result is that the list of appointments in this
     * class is ordered in time.
     *
     * @param appointment The appointment that will be added to this day.
     */
    public void addAppointment(Appointment appointment) {
        Node<Appointment> appToInsert = new Node<>(appointment);
        if (!weekend) {
            if (!containsAppointmentWithDescription(appToInsert.item.getDescription())) {
                if (canAddAppointmentOfDuration(appointment.getDuration())) {
                    if (appointment.getStart() == null) {
                        if (runner.next != null) {
                            if (runner.item == null) {
                                appToInsert.item.setStart(DAY_START);
                                appToInsert.next = runner.next;
                                runner.next = appToInsert;
                                nrOfAppointments++;
                                System.out.println("Appointment with no StartTime "
                                        + "added: " + this.getNrOfAppointments() + " /" + appToInsert.item.getDescription());
                            } else {
                                appToInsert.item.setStart(runner.item.getEnd());
                                appToInsert.next = runner.next;
                                runner.next = appToInsert;
                                nrOfAppointments++;
                                System.out.println("Appointment with no StartTime "
                                        + "added: " + this.getNrOfAppointments() + " = " + appToInsert.item.getDescription());
                            }

                        } 
                    } else {
                        if (!checkOverlap(appointment)) {
                            appToInsert.next = runner.next;
                            runner.next = appToInsert;
                            nrOfAppointments++;
                            System.out.println("Appointment with no StartTime added: " + this.getNrOfAppointments() + " + " + appToInsert.item.getDescription());
                        } else {
                            System.out.println("Appointment: " + appToInsert.item.getDescription() + " was not added");
                        }
                    }
                } else {
                    System.out.println("Appointment: " + appToInsert.item.getDescription() + " Cant Add Appointment of This Durration on this Day");
                }
            } else {
                System.out.println("Appointment: " + appToInsert.item.getDescription() + " Appointment Description is not Unique Appointment will not be added");

            }
        }
    }

    /**
     * Removes the appointment with the given description.
     *
     * @param description The description of the appointment for which a search
     * has to be done.
     */
    public void removeAppointment(String description
    ) {
        runner = dummyHead;

        while (true) {

            if (runner.next == null && runner.item == null) {
                break;
            }
            /*
                Search for matching string of description
                Then we going to delete the actual runner Object
                lastNode - this.runner - lastNode.next.next
             */
            if (runner.item != null) {
                if (description.equals(runner.item.getDescription())) {
                    lastNode.next = lastNode.next.next;
                    nrOfAppointments--;
                    break;
                } else {
                    System.out.println("ERROR: No Appointment with given Name found");
                }

            }
            lastNode = runner;
            runner = runner.next;

        }
    }

    /**
     * Checks if an appointment with given description exists.
     *
     * @param description The description of the appointment for which this
     * method finds out if it exists. Each description is unique for
     * appointments on the same day.
     *
     * @return Returns true if an appointment with given description exists.
     */
    public boolean containsAppointmentWithDescription(String description) {

        runner = dummyHead.next;

        while (true) {

            if (runner.item != null) {
                if (description == runner.item.getDescription()) {
                    System.out.println("Found: " + description);

                    return true;
                }

            }
            if (runner.next == null) {
                break;
            }

            runner = runner.next;

        }

        return false;

    }

    /**
     * This method finds all start times that are available on this day for an
     * appointment of given duration.
     *
     * @param duration the requested duration for an appointment
     * @return an array of start times on which an appointment can be scheduled
     */
    public Time[] getAvailableStartTimesForAppointmentsOfDuration(TimeSpan duration
    ) {
        TimeGap[] availibleTimeGaps = this.getAvailableTimeGaps();
        Time[] availibleStartTimesTemp = new Time[availibleTimeGaps.length];

        int sizeCountOfNewArray = 0;

        for (TimeGap atg : availibleTimeGaps) {
            if (duration.isSmallerThan(atg.getLength())) {
                availibleStartTimesTemp[sizeCountOfNewArray] = atg.getStart();
                System.out.println("possible start: " + atg.getStart());
                sizeCountOfNewArray++;

            }
        }
        Time[] resultArrayOfAvailibleStartTimes = new Time[sizeCountOfNewArray];
        System.arraycopy(availibleStartTimesTemp, 0, resultArrayOfAvailibleStartTimes, 0, sizeCountOfNewArray);
        System.out.println("sizeCountOfNewArray " + sizeCountOfNewArray);

        return resultArrayOfAvailibleStartTimes;

    }

    // METHODS INTRODUCED IN WEEK 4 OF APPOINTMENT PROJECT
    /**
     * This method gives a list of available time gaps on this day. Each time
     * gap holds the available time between appointments, and possibly at the
     * begin and at the end of the day.
     *
     * @return an array of TimeGaps containing all available time slots that are
     * available on this day.
     */
    public TimeGap[] getAvailableTimeGaps() {
        TimeGap[] timeGapArray = new TimeGap[100];

        int position = 0;

        runner = dummyHead;
        boolean checking = true;
        while (checking) {

            //First Node
            if (runner.item == null) {
                if (runner.next != null) {
                    if (runner.next.item != null) {

                        if (runner.next.item.getStart().equals(DAY_START)) {
                            // APP START TIME IS EQUAL TO DAY START TIME WE DO NOTHING HERE
                            // BECAUSE IF WE WOULD ADD AN ARRAY ENTRY ON THE NEXT LOOP 
                            // THEY WOULD TAKE THIS NODE AGAIN AND WE WOULD HAVE THIS DUPED

                        } else {
                            System.out.println("DAY START");
                            TimeGap tgFirst = new TimeGap(DAY_START, runner.next.item.getStart());
                            System.out.println("First Node TimeGap: " + tgFirst.toString());
                            timeGapArray[position] = tgFirst;
                            position++;
                        }

                    }
                }

                //break operation and return
                if (runner.next == null) {
                    TimeGap[] resultArray = new TimeGap[position];
                    System.arraycopy(timeGapArray, 0, resultArray, 0, position);

                    System.out.println("resultArray of Gaps: " + resultArray.length);

                    return resultArray;

                }
            }

            //Inside Operations
            if (runner.item != null) {
                if (runner.next.item != null) {
                    TimeGap tg = new TimeGap(runner.item.getEnd(), runner.next.item.getStart());
                    System.out.println("Inside TimeGap: " + tg.toString());
                    timeGapArray[position] = tg;
                    position++;

                }
                //Last Node
                if (runner.next.item == null) {
                    TimeGap tgLast = new TimeGap(runner.item.getEnd(), DAY_END);
                    System.out.println("Last TimeGap: " + tgLast.toString());
                    timeGapArray[position] = tgLast;
                    position++;

                }

            }

            runner = runner.next;

        }
        return null;

    }

    /**
     * This Method is checking if there is a Overlap of a given appointment and
     * the settet appointments.
     *
     * @return false if no overlap is given, true otherwise.
     */
    public boolean checkOverlap(Appointment appointment) {
        this.runner = this.dummyHead;

        while (true) {
            if (runner.item == null) {
                if (runner.next != null) {
                    if (DAY_START.equals(appointment.getStart())) {
                        if (appointment.getEnd().isBefore(LUNCH_TIME)) {
                            return false;

                        }
                    }
                    if (DAY_START.isBefore(appointment.getStart())) {
                        if (appointment.getEnd().isBefore(LUNCH_TIME)) {
                            return false;

                        }
                    }
                }
            }

            if (runner.item != null) {
                //Check if Appointment is Before DayStart
                if (appointment.getStart().isBefore(DAY_START)) {
                    System.out.println("Appointment is before Day Start");
                    return true;
                }
                //Check if Appointment is After DayEnd
                if (DAY_END.isBefore(appointment.getStart())) {
                    System.out.println("Appointment is after Day End");
                    return true;
                }
                //Check if Appointment has the Same startTime as an Existing Appointment
                if (appointment.getStart().equals(runner.item.getStart())) {
                    System.out.println("Same Time, EXIT");
                    return true;
                }

                //Itterrate Thru the LinkedList of Nodes and search for a Overlapping Appointment
                if (runner.next.item != null) {
                    if (runner.item.getEnd().isBefore(appointment.getStart())) {
                        if (appointment.getEnd().isBefore(runner.next.item.getStart())) {
                            return false;
                        }
                    } else {
                        System.out.println("runnerapp: " + runner.item.getDescription());
                        System.out.println("Appointment: " + appointment.getDescription() + ", is colliding with : " + runner.item.getDescription());
                        return true;
                    }
                }

                //if There are no next Node to compare
                if (runner.next.item == null) {
                    if (!runner.item.getEnd().isBefore(appointment.getStart())) {
                        return true;
                    }
                }
            }

            if (runner.next.item == null) {
                break;
            }
            runner = runner.next;
        }
        //IF NO EXCEPTION HAPPENS STANDART RETURN IS TRUE WICH INDICATES A APPOINTMENT ADD.
        return false;

    }

    /**
     * This Method is creating a Queue of all Appointments
     *
     * @return a Queue of Appointments
     */
    public Queue<Appointment> getAppointments() {
        Queue<Appointment> queue = new Queue();
        runner = dummyHead;

        while (true) {
            if (runner.next == null) {
                break;
            }
            if (runner.item != null) {
                queue.put(runner.item);
            }

            runner = runner.next;
        }
        return queue;

    }

    /**
     * This Method is creating a Stack of all Appointments with a certain
     * priority
     *
     * @param priority The priority what is asked for
     * @return a Stack with only the given prioritys
     */
    public Stack getAppointmentsOfPriority(Priority priority) {
        Appointment[] appArray = new Appointment[nrOfAppointments + 1];
        runner = dummyHead;
        int stackSize = 0;

        while (true) {
            if (runner.item != null) {
                System.out.println("1");
                if(runner.item.compareTo(priority)== 0){
                    appArray[stackSize] = runner.item;
                    stackSize++;
                }      
            }
            
            if (runner.next == null) {
                System.out.println("2");
                break;
            }
            runner = runner.next;
        }

        Stack<Appointment> resultStack = new Stack();
        
        //Revert the Array
        for (int i = stackSize; i > 0; i--) {
            resultStack.push(appArray[i-1]);
        }

        return resultStack;
    }

}