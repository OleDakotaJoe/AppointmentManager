package me.stevensheaves.custom.utils;

import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

public class DateTimeCellFormatter<S, T> implements Callback<TableColumn<S, T>, TableCell<S, T>> {
    private final DateTimeFormatter format;

    public DateTimeCellFormatter(String format) {
        super();
        this.format = DateTimeFormatter.ofPattern(format);
    }

    @Override
    public TableCell<S, T> call(TableColumn<S, T> args) {
        return new TableCell<>() {
            @Override
            protected void updateItem(T item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {
                    setText(format.format((TemporalAccessor) item));
                }
            }
        };
    }
}
