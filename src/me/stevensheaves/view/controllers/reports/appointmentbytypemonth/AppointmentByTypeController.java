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

    @FXML
    private void initialize() {
        VBox vBox = buildVBox();
        ObservableList<String> strings = buildAppointmentList(new AppointmentDAO().findAll());
        addSchedulesToVBox(strings, vBox);
        mainPane.setContent(vBox);
    }


    private VBox buildVBox() {
        VBox vBox = new VBox();
        Insets insets = new Insets(20,0,0,220);
        vBox.setSpacing(10);
        vBox.setPadding(insets);
        return vBox;
    }

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

    private <T> ObservableList<String> toListOfStrings(ObservableList<T> observableList) {
        ObservableList<String> strings = FXCollections.observableArrayList();
        for (T item: observableList) {
            strings.add(item.toString());
        }
        return strings;
    }

    private void addSchedulesToVBox(ObservableList<String> listOfAppointments, VBox vBox) {
        for (String string : listOfAppointments) {
            Text textNode =  new Text(string);
            vBox.getChildren().add(textNode);
        }
    }

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
