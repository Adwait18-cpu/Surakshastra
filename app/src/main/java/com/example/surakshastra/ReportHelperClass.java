package com.example.surakshastra;

public class ReportHelperClass {

    String reporterName, reporterPhoneNo, reporterEmail, reporterUserName, reportedName, reportedPhoneNo;

    public ReportHelperClass(){

    }


    public ReportHelperClass(String reporterName, String reporterPhoneNo, String reporterEmail, String reporterUserName, String reportedName, String reportedPhoneNo){

        this.reporterName = reporterName;
        this.reporterEmail = reporterEmail;
        this.reporterPhoneNo = reporterPhoneNo;
        this.reporterUserName = reporterUserName;
        this.reportedName = reportedName;
        this.reportedPhoneNo = reportedPhoneNo;

    }

    public String getReporterUserName() {
        return reporterUserName;
    }

    public void setReporterUserName(String reporterUserName) {
        this.reporterUserName = reporterUserName;
    }

    public String getReporterName() {
        return reporterName;
    }

    public void setReporterName(String reporterName) {
        this.reporterName = reporterName;
    }

    public String getReporterPhoneNo() {
        return reporterPhoneNo;
    }

    public void setReporterPhoneNo(String reporterPhoneNo) {
        this.reporterPhoneNo = reporterPhoneNo;
    }

    public String getReporterEmail() {
        return reporterEmail;
    }

    public void setReporterEmail(String reporterEmail) {
        this.reporterEmail = reporterEmail;
    }

    public String getReportedName() {
        return reportedName;
    }

    public void setReportedName(String reportedName) {
        this.reportedName = reportedName;
    }

    public String getReportedPhoneNo() {
        return reportedPhoneNo;
    }

    public void setReportedPhoneNo(String reportedPhoneNo) {
        this.reportedPhoneNo = reportedPhoneNo;
    }
}
