/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author frei-
 */
public class AppointmentBean implements java.io.Serializable
{
    private long appointmentId;
    private long accountDoctorIdFK;
    private long accountPatientIdFK;
    private Date date;
    private Time startTime;
    private Time duration;
    
    public AppointmentBean() {}

    public AppointmentBean(long appointmentId, long accountDoctorIdFK, long accountPatientIdFK, Date date, Time startTime, Time duration) {
        this.appointmentId = appointmentId;
        this.accountDoctorIdFK = accountDoctorIdFK;
        this.accountPatientIdFK = accountPatientIdFK;
        this.date = date;
        this.startTime = startTime;
        this.duration = duration;
    }

    public long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public long getAccountDoctorIdFK() {
        return accountDoctorIdFK;
    }

    public void setAccountDoctorIdFK(long accountDoctorIdFK) {
        this.accountDoctorIdFK = accountDoctorIdFK;
    }

    public long getAccountPatientIdFK() {
        return accountPatientIdFK;
    }

    public void setAccountPatientIdFK(long accountPatientIdFK) {
        this.accountPatientIdFK = accountPatientIdFK;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }
}
