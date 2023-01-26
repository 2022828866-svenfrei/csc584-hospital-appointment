/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.sql.Date;

/**
 *
 * @author frei-
 */
public class AbsenceBean {
    private long absenceId;
    private long accountIdFK;
    private Date fromDate;
    private Date toDate;
    
    public AbsenceBean() {}

    public AbsenceBean(long absenceId, long accountIdFK, Date fromDate, Date toDate) {
        this.absenceId = absenceId;
        this.accountIdFK = accountIdFK;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public long getAbsenceId() {
        return absenceId;
    }

    public void setAbsenceId(long absenceId) {
        this.absenceId = absenceId;
    }

    public long getAccountIdFK() {
        return accountIdFK;
    }

    public void setAccountIdFK(long accountIdFK) {
        this.accountIdFK = accountIdFK;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }
}
