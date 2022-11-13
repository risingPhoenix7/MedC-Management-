public class Appointment
{
    String BITS_ID;
    Doctor DoctorObj;
    DateTime Timestamp;
    DateTime Appointment_Date;
    DateTime Appointment_Time;
    void setBITS_ID(String s)
    {
        BITS_ID=s;
    }
    void setDoctor(Doctor d)
    {
        DoctorObj=d;
    }
    void setTimestamp(DateTime d)
    {
        Timestamp=d;
    }
    void setAppointment_Date(DateTime d)
    {
        Appointment_Date=d;
    }
    void setAppointment_Time(DateTime d)
    {
        Appointment_Time=d;
    }
    String getBITS_ID()
    {
        return BITS_ID;
    }
    Doctor getDoctor()
    {
        return DoctorObj;
    }
    DateTime getTimestamp()
    {
        return Timestamp;
    }
    DateTime getAppointment_Date()
    {
        return Appointment_Date;
    }
    DateTime getAppointment_Time()
    {
        return Appointment_Time;
    }
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

