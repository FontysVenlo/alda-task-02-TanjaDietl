package appointmentplanner;

//TODO

import appointmentplanner.util.Priority;


/**
 *
 * @author tanja
 */
public final class Appointment implements Comparable{

    private final String description;
    private final TimeSpan duration;
    private Time start;
    private Time end;
    private Priority priority;

    
    /**
     * Sets the priority for this appointment.
     *
     * @param priority The priority  for this appointment.
     */
    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    
    /**
     * @return The priority of this appointment.
     */
    public Priority getPriority() {
        return priority;
    }

    /**
     * Constructs a new appointment with given description and duration. The
     * 
     *
     * @param description The description of the appointment.
     * @param duration The time span needed for this appointment.
     */
    public Appointment(String description, TimeSpan duration) {
        this.description = description;
        this.duration = duration;
        this.priority = Priority.LOW;
    }
    
    /**
     * Constructs a new appointment with given description and duration. The
     * 
     *
     * @param description The description of the appointment.
     * @param duration The time span needed for this appointment.
     * @param priority The priority of this appointment
     */
    public Appointment(String description, TimeSpan duration, Priority priority) {
        this.description = description;
        this.duration = duration;
        this.priority = priority;
    }

    /**
     * @return The description of this appointment.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the start time for this appointment.
     *
     * @param start The start time for this appointment.
     */
    public void setStart(Time start) {
        this.start = start;
    }

    /**
     * Returns the duration of this appointment.
     *
     * @return The duration of this appointment.
     */
    public TimeSpan getDuration() {
        return duration;
    }

    /**
     * Returns the end time of this appointment. Returns null if the start time
     * has not been set.
     *
     * @return Time when this appointment is done
     */
    public Time getEnd() {
        Time durationToAdd = new Time(duration.getHours(), duration.getMinutes());
        this.end = start.addTime(durationToAdd);
        return end;
    }

    /**
     * Returns the start time of this appointment.
     *
     * @return start The start time of this appointment.
     */
    public Time getStart() {
        return start;
    }
    
    
    @Override
    public String toString() {
        return "Appointment: "+ description +", Start Time: "+ start+", End Time: "+ end+", "+ duration;
    }

    @Override
    public int compareTo(Object o) {
        
        Priority priority = (Priority)o;
        
        if(this.getPriority().ordinal() > priority.ordinal()){
            return -1;
        }
        if(this.getPriority().ordinal() < priority.ordinal()){
            return 1;
        } 
        
        return 0;
    }
}