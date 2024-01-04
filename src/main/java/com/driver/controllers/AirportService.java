package com.driver.controllers;

import com.driver.model.Airport;
import com.driver.model.City;
import com.driver.model.Flight;
import com.driver.model.Passenger;
import io.swagger.models.auth.In;
import org.springframework.beans.CachedIntrospectionResults;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.imageio.ImageIO;
import java.util.Date;

@Service
public class AirportService {

    AirportRepositry repositry = new AirportRepositry();

    public void addAirport(Airport airport){
        repositry.addAirport(airport);
    }
    public String getLargestAirportName(){
        return repositry.getLargestAirportName();
    }
    public double getShortestDurationOfPossibleBetweenTwoCities(City fromCity, City toCity){
        return repositry.getShortestDurationOfPossibleBetweenTwoCities(fromCity, toCity);
    }
    public int getNumberOfPeopleOn(Date date , String airportName){
        return repositry.getNumberOfPeopleOn(date, airportName);
    }
    public int calculateFlightFare(Integer flightId){
        return repositry.calculateRevenueOfAFlight(flightId);
    }
    public String bookATicket(Integer flightId, Integer passengerId){
        return repositry.bookATicket(flightId, passengerId);
    }
    public String cancelATicket(Integer flightId , Integer passengerId){
        return repositry.cancelATicket(flightId, passengerId);
    }
    public int countOfBookingsDoneByPassengerAllCombined(Integer passengerId){
        return repositry.countOfBookingsDoneByPassengerAllCombined(passengerId);
    }
    public void addFlight(@RequestBody Flight flight){
        repositry.addFlight(flight);
    }
    public String getAirportNameFromFlightId(Integer flightId){
        return repositry.getAirportNameFromFlightId(flightId);
    }
    public int calculateRevenueOfAFlight(Integer flightId){
        return repositry.calculateRevenueOfAFlight(flightId);
    }
    public void addPassenger(Passenger passenger){
        repositry.addPassenger(passenger);
    }
}
