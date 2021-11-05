package appointmentplanner;

import java.text.DecimalFormat;

/**
 * A 24 hour Time class
 *
 * @author tanja
 */
public final class Time {

    private final int hours;
    private final int minutes;

    /**
     * Constructor with two arguments: hours and minutes. No mercy for those who
     * do not follow the pre-conditions! Pre conditions are: - hours should be
     * in range 0..23 - minutes should be in range 0..59
     *
     * But: A static method is available that returns a Time object with
     * parameters that need not fulfill these conditions.
     *
     * @param hours the number of hours
     * @param minutes the number of minutes
     */
    public Time(int hours, int minutes) {
        this.hours = hours;
        this.minutes = minutes;
    }

    /**
     * Getter for hours of this Time.
     *
     * @return hours of this Time object
     */
    public int getHours() {
        return hours;
    }

    /**
     * Getter for minutes of this Time.
     *
     * @return minutes of this Time object
     */
    public int getMinutes() {
        return minutes;
    }

    /**
     * A given time is added to this time. The result is returned as a new Time
     * object.
     *
     * @param t The time that will be added to this time.
     * @return The Time object as a result of adding t to this time.
     */
    public Time addTime(Time t) {
        int resultOfAddedHours = this.hours + t.hours;
        int resultOfAddedMinutes = this.minutes + t.minutes;

        Time newTime = Time.getProperTime(resultOfAddedHours, resultOfAddedMinutes);
        return new Time(newTime.hours, newTime.minutes);
    }

    /**
     * This method actually tests if this time is before the given other time.
     *
     * @param otherTime The Time object to compare with.
     * @return true if the this time is before other.
     */
    public boolean isBefore(Time otherTime) {

        if (this.hours < otherTime.hours) {
            return true;
        }
        if (this.hours == otherTime.hours) {
            if (this.minutes < otherTime.minutes) {
                return true;
            }

        } else {
            return false;
        }
        return false;
    }

    /**
     * Get your Time object here if you are in doubt if parameters are not in
     * the correct (proper) range: 0 &lt;= hrs &lt; 24 &amp; 0 &lt;= mts &lt;
     * 60. Negative hour values are 'normalized' to zero, negative minute values
     * are subtracted from the hours. Hour values that are too big are
     * normalized to values in range (e.g. 25 hours becomes 2 hours), an
     * overflow of minutes is added to the hours.
     *
     * @param hrs hour of the time object
     * @param mts minutes of the time object
     * @return a new Time object.
     */
    public static Time getProperTime(int hrs, int mts) {
        int hours = hrs;
        int minutes = mts;
        int hourToMins;
        int result;

        if (hours < 0) {
            hourToMins = hours * 60;
            result = hourToMins + minutes;

            if (result < 0) {
                hours = 0;
                minutes = 0;

            }else if (result > 0){
                minutes = result % 60;
                hours = (result / 60) % 24;
            }

        } else if (minutes < 0 || hours >= 0) {
            hourToMins = hrs * 60;
            result = hourToMins + minutes;

            if (result <= 0) {
                result = 0;
            }

            minutes = result % 60;
            hours = (result / 60) % 24;

        }

        return new Time(hours, minutes);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Time)) {
            return false;
        } else {
            Time t = (Time) o;
            return t.hours == this.hours && t.minutes == this.minutes;
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + this.hours;
        hash = 11 * hash + this.minutes;
        return hash;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("00");
        return df.format(hours) + ":" + df.format(minutes);
    }
}