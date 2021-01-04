package me.stevensheaves.data.model;

public class Report {
    private String reportName;
    private ReportType reportType;

    public Report(String reportName, ReportType reportType) {
        this.reportName = reportName;
        this.reportType = reportType;
    }

    public ReportType getReportType() {
        return reportType;
    }

    @Override
    public String toString() {
        return reportName;
    }
    public enum ReportType {
        APPOINTMENT_BY_TYPE,
        APPOINTMENT_BY_CUSTOMER,
        SCHEDULE_BY_CONTACT
    }
}
