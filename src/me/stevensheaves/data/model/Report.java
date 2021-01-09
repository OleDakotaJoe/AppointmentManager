package me.stevensheaves.data.model;

/**
 * Class for holding the name and type of <code>Report</code>.
 * This class is used by the <code>ReportsController</code> for populating a <code>ComboBox</code> and determining which report to display.
 */
public class Report {
    private String reportName;
    private ReportType reportType;

    /**
     * Constructor for the <code>Report</code> class
     * @param reportName <code>String</code> value to be set as the <code>reportName</code>
     * @param reportType <code>ReportType</code> to be set as the <code>reportType</code>
     */
    public Report(String reportName, ReportType reportType) {
        this.reportName = reportName;
        this.reportType = reportType;
    }
    /**
     * Getter for the <code>divisionName</code> field.
     * @return Returns the String value of the <code>divisionName</code> field.
     */
    public ReportType getReportType() {
        return reportType;
    }
    /**
     * Overrides the toString() method for more readable printing.
     * !!!---THIS CANNOT BE CHANGED.---!!!
     * @return returns the <code>reportName</code> of the Contact.
     */
    @Override
    public String toString() {
        return reportName;
    }

    /**
     * This <code>enum</code> is used in determining which report to display in the <code>reports.fxml</code> view.
     */
    public enum ReportType {
        APPOINTMENT_BY_TYPE,
        APPOINTMENT_BY_CUSTOMER,
        SCHEDULE_BY_CONTACT
    }
}
