package appointmentplanner;

import java.util.Objects;

/**
 * A class for a time span at a certain start time. Derived class is
 * Appointment.
 *
 * @author tani.d
 */
public class TimeGap {

    private final TimeSpan length;
    private Time start;
    private Time end;

    public TimeGap(TimeSpan length, Time start) {
        
        this.length=length; 
        this.start=start; 
        this.end= start.addTime(new Time(length.getHours(), length.getMinutes()));
    }

    public TimeGap(Time start, Time end) {
        this.start=start; 
        this.end=end;
        this.length=new TimeSpan(start, end); 
    }

    public Time getStart() {
        return start;
    }

    public Time getEnd() {
        return end;
    }

    public TimeSpan getLength() {
        return length;
    }

    @Override
    public String toString() {
        return "StartTime: "+ start +", EndTime: "+ end;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TimeGap other = (TimeGap) obj;
//        if (!Objects.equals(this.length, other.length)) {
//            return false;
//        }
        if (!Objects.equals(this.start, other.start)) {
            return false;
        }
        if (!Objects.equals(this.end, other.end)) {
            return false;
        }
        return true;
    }



    
}