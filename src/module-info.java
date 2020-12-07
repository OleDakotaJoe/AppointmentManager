module AppointmentSchedulerApp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens me.stevensheaves;
    opens me.stevensheaves.view.controllers.appointment;
    opens me.stevensheaves.view.controllers.contacts;
    opens me.stevensheaves.view.controllers.customers;
    opens me.stevensheaves.view.controllers.dashboard;
    opens me.stevensheaves.view.controllers.login;
    opens me.stevensheaves.view.controllers.mainscreen;
    opens me.stevensheaves.view.controllers.reports;


    exports me.stevensheaves.database.utils;
    exports me.stevensheaves.view.controllers.utils;
    exports me.stevensheaves.data.utils;
    exports me.stevensheaves.data.security;
    exports me.stevensheaves.custom.controls;
}