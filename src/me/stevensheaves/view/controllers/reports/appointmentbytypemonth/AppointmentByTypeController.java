package me.stevensheaves.view.controllers.reports.appointmentbytypemonth;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import me.stevensheaves.data.model.Appointment;
import me.stevensheaves.database.utils.AppointmentDAO;

import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.util.Enumeration;
import java.util.Hashtable;

public class AppointmentByTypeController {
    private final String infoGather = "Information-Gathering";
    private int numberOfInfoGatherOccurrences = 0;
    private final String productDemo = "Product Demo";
    private int numberOfProductDemoOccurrences = 0;
    private final String followUp = "Follow-Up";
    private int numberOfFollowUpOccurrences = 0;
    private final String needsAssessment ="Needs-Assessment";
    private int numberOfNeedsAssessmentOccurrences = 0;
    private final String closeSale = "Closing Sale";
    private int numberOfCloseSaleOccurrences = 0;
    private final String other = "Other";
    private int numberOfOtherOccurrences = 0;

    @FXML
    private ScrollPane mainPane;

    /**
     * Initializes the view, and calls all necessary methods to build the report.
     */
    @FXML
    private void initialize() {
        VBox vBox = buildVBox();
        ObservableList<String> strings = buildAppointmentList(new AppointmentDAO().findAll());
        addSchedulesToVBox(strings, vBox);
        mainPane.setContent(vBox);
    }

    /**
     * Builds and returns a VBox with the appropriate configuration.
     * @return The configured VBox.
     */
    private VBox buildVBox() {
        VBox vBox = new VBox();
        Insets insets = new Insets(20,0,0,220);
        vBox.setSpacing(10);
        vBox.setPadding(insets);
        return vBox;
    }

    /**
     * Formats the lists of appointments neatly into a schedule.
     * @param appointments The list of appointments.
     * @return Returns an ObservableList of Strings that contains all formatted schedules.
     */
    private ObservableList<String> buildAppointmentList(ObservableList<Appointment> appointments) {
        ObservableList<String> strings = FXCollections.observableArrayList();
        for (Appointment appointment : appointments) {
            tallyNumberOfAppointmentsByType(appointment);
        }
        strings.add("Number of Appointments By Type:\n");
        strings.addAll(buildAppointmentByTypeList());
        strings.add("_______________________________________________________\n\n\nNumber of Appointments By Month:\n");
        strings.addAll(toListOfStrings(buildAndTallyMonthList(appointments)));
        return strings;
    }


    /**
     * Determines whihc type of appointment is being passed to it, increments it's corresponding counter.
     * @param appointment The appointment to be tallied.
     */
    private void tallyNumberOfAppointmentsByType(Appointment appointment) {
            switch (appointment.getType()) {
                case infoGather:
                    numberOfInfoGatherOccurrences++;
                    break;
                case needsAssessment:
                    numberOfNeedsAssessmentOccurrences++;
                    break;
                case productDemo:
                    numberOfProductDemoOccurrences++;
                    break;
                case closeSale:
                    numberOfCloseSaleOccurrences++;
                    break;
                case followUp:
                    numberOfFollowUpOccurrences++;
                    break;
                case other:
                    numberOfOtherOccurrences++;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + appointment.getType());
            }
    }

    /**
     * Builds and returns a ObservableList of String representations of the Appointments, by type and quantity.
     * @return Returns the ObservableList of strings, that is displayed as a report.
     */
    private ObservableList<String> buildAppointmentByTypeList() {
        ObservableList<String> strings = FXCollections.observableArrayList();
        strings.add(infoGather + " : " + numberOfInfoGatherOccurrences);
        strings.add(needsAssessment + " : " + numberOfNeedsAssessmentOccurrences);
        strings.add(productDemo + " : " + numberOfProductDemoOccurrences);
        strings.add(closeSale + " : " + numberOfCloseSaleOccurrences);
        strings.add(followUp + " : " + numberOfFollowUpOccurrences);
        strings.add(other + " : " + numberOfOtherOccurrences);

        return strings;
    }

    /**
     * Builds a list of MonthQuantity objects.
     * @param appointments A list of all appointments.
     * @return A list of all Months, and the number of appointments within them, displayed as an ObservableList of MonthQuantity objects.
     */
    private ObservableList<MonthQuantity> buildAndTallyMonthList(ObservableList<Appointment> appointments) {
        ObservableList<MonthQuantity> monthList = FXCollections.observableArrayList();
        for (Appointment appointment : appointments) {
            Month month = appointment.getStartDateTime().getMonth();
            Year year = Year.of(appointment.getStartDateTime().getYear());
            YearMonth currentAppointmentMonth = YearMonth.of(year.getValue(),month.getValue());
            monthList.add(new MonthQuantity(currentAppointmentMonth));
        }

        Hashtable<YearMonth, Integer> hashtable = generateMonthHashTable(monthList);
        ObservableList<MonthQuantity> resultMonthList = tallyMonthList(hashtable);
        return resultMonthList;
    }


    /**
     * Utility method for tallying the total number of appointments, in lists of months.
     * Enumerates through the hashtable, then generates a list of Months and the total number of appointments withing them.
     * @param hashtable A hashtable which holds the YearMonth corresponding to an appointment, and an integer corresponding to the sum of
     * @return
     */
    private ObservableList<MonthQuantity> tallyMonthList(Hashtable<YearMonth, Integer> hashtable) {
        ObservableList<MonthQuantity> monthList = FXCollections.observableArrayList();
        Enumeration<YearMonth> months = hashtable.keys();
        while(months.hasMoreElements()) {
            YearMonth currentYearMonth = months.nextElement();
            int numOfAppointments = hashtable.get(currentYearMonth);
            monthList.add(new MonthQuantity(currentYearMonth,numOfAppointments));
        }

        return monthList;
    }

    /**
     * Generates a Hashtable to represent all YearMonths in the monthList, in addition to their corresponding sum of appointments.
     * @param monthList List of months to be tallied.
     * @return Returns a HashTable with YearMonths as the keys, and the Integer value of the sum of appointments as the values.
     */
    private Hashtable<YearMonth, Integer> generateMonthHashTable(ObservableList<MonthQuantity> monthList) {
        Hashtable<YearMonth, Integer> hashTable = new Hashtable<>();
        for (MonthQuantity monthQuantity : monthList) {
            if(hashTable.containsKey(monthQuantity.yearMonth)) {
                int currentValue = hashTable.get(monthQuantity.yearMonth);
                hashTable.put(monthQuantity.yearMonth, currentValue + 1 );
            } else {
                hashTable.put(monthQuantity.yearMonth,1);
            }
        }
        return hashTable;
    }

    /**
     * Converts an observable list of any type to a list of strings.
     * @param observableList the list to be converted.
     * @param <T> Type parameter. Can be any Type, which can be added to a ObservableList
     * @return Returns the ObservableList of strings.
     */
    private <T> ObservableList<String> toListOfStrings(ObservableList<T> observableList) {
        ObservableList<String> strings = FXCollections.observableArrayList();
        for (T item: observableList) {
            strings.add(item.toString());
        }
        return strings;
    }

    /**
     * Generates text nodes for each string and adds them to the appropriate VBox
     * @param listOfAppointments the list of strings to add to the VBox
     * @param vBox the VBox to add the list of strings to.
     */
    private void addSchedulesToVBox(ObservableList<String> listOfAppointments, VBox vBox) {
        for (String string : listOfAppointments) {
            Text textNode =  new Text(string);
            vBox.getChildren().add(textNode);
        }
    }

    /**
     * A private inner class, used for holding data about a month, as well as athe sum of how many appointments are within that month.
     */
    private class MonthQuantity {
        private Month month;
        private Year year;
        private final YearMonth yearMonth;
        private int num;

        public MonthQuantity(YearMonth yearMonth) {
            this.yearMonth = yearMonth;
        }

        public MonthQuantity(YearMonth yearMonth, int num) {
            this.yearMonth = yearMonth;
            this.num = num;
            this.year = Year.of(yearMonth.getYear());
            this.month = yearMonth.getMonth();
        }

        @Override
        public String toString() {
            return month.name() + ", " + year +" : "+ num;
        }
    }
    
}
