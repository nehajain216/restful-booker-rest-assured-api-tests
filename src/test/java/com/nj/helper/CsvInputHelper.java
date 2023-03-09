package com.nj.helper;

import com.nj.models.Booking;
import com.nj.models.BookingDates;
import com.nj.models.UpdateBookingEntry;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import io.qameta.allure.Step;
import org.junit.jupiter.params.provider.Arguments;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class CsvInputHelper {

    public static Stream<Arguments> createBookingArguments(String filePath) throws IOException, CsvValidationException {
        try (FileReader filereader = new FileReader(filePath)) {
            CSVReader csvReader = new CSVReader(filereader);
            csvReader.readNext(); //read header
            List<Arguments> argumentsList = new ArrayList<>();
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                Booking booking = new Booking(
                        nextRecord[0],
                        nextRecord[1],
                        Integer.parseInt(nextRecord[2]),
                        Boolean.parseBoolean(nextRecord[3]),
                        new BookingDates(LocalDate.parse(nextRecord[4]), LocalDate.parse(nextRecord[5])),
                        nextRecord[6]);
                Arguments arguments = arguments(booking, Integer.parseInt(nextRecord[7]));
                argumentsList.add(arguments);
            }
            return argumentsList.stream();
        }
    }

    public static Stream<Arguments> updateBookingArguments(String filePath) throws IOException, CsvValidationException {
        try (FileReader filereader = new FileReader(filePath)) {
            CSVReader csvReader = new CSVReader(filereader);
            csvReader.readNext(); //read header
            List<Arguments> argumentsList = new ArrayList<>();
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                UpdateBookingEntry updateBookingEntry = new UpdateBookingEntry(
                        nextRecord[0],
                        nextRecord[1],
                        Integer.parseInt(nextRecord[2]),
                        Boolean.parseBoolean(nextRecord[3]),
                        new BookingDates(LocalDate.parse(nextRecord[4]), LocalDate.parse(nextRecord[5])),
                        nextRecord[6],

                        nextRecord[7],
                        nextRecord[8],
                        Integer.parseInt(nextRecord[9]),
                        Boolean.parseBoolean(nextRecord[10]),
                        new BookingDates(LocalDate.parse(nextRecord[11]), LocalDate.parse(nextRecord[12])),
                        nextRecord[13]
                );
                Arguments arguments = arguments(updateBookingEntry, Integer.parseInt(nextRecord[14]));

                argumentsList.add(arguments);
            }
            return argumentsList.stream();
        }

    }

}
