package com.driver.controllers;

import com.driver.model.Airport;
import com.driver.model.City;
import com.driver.model.Flight;
import com.driver.model.Passenger;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class AirportRepositry {

    HashMap<String,Airport> airportMap = new HashMap<>();
    HashMap<Integer, Flight> flightMap = new HashMap<>();
    HashMap<Integer,Passenger> passengerMap = new HashMap<>();
    HashMap<Integer, List<Integer>> ticketMap = new HashMap<>();

    public void addAirport(Airport airport){
        airportMap.put(airport.getAirportName(), airport);
    }
    public String getLargestAirportName(){
        int countTerminal = 0;
        for(Airport airport : airportMap.values()){
            if(airport.getNoOfTerminals() >= countTerminal){
                countTerminal = airport.getNoOfTerminals();
            }
        }
        List<String > airpostName = new ArrayList<>();
        for(Airport airport : airportMap.values()){
            if(airport.getNoOfTerminals() == countTerminal){
                airpostName.add(airport.getAirportName());
            }
        }
        Collections.sort(airpostName);
        return airpostName.get(0);
    }

    public double getShortestDurationOfPossibleBetweenTwoCities(City fromCity, City toCity){
        double time = Double.MAX_VALUE;
        for(Flight flight : flightMap.values()){
            if(flight.getFromCity() == fromCity && flight.getToCity() == toCity){
                time = Math.min(time, flight.getDuration());
            }
        }

        return time == Double.MAX_VALUE ? -1 : time;
    }

    public int getNumberOfPeopleOn(Date date , String airportName){
        int count = 0;
        if(airportMap.containsKey(airportName)){
            City city = airportMap.get(airportName).getCity();
            for(Integer flightId : ticketMap.keySet()){
                Flight flight = flightMap.get(flightId);
                if(flight.getFlightDate().equals(date) &&(flight.getFromCity().equals(city) || flight.getToCity().equals(city))){
                    count += ticketMap.get(flightId).size();
                }
            }
        }
        return count;
    }

    public int calculateFlightFare(Integer flightId){
        int noOfBookedFlight = ticketMap.get(flightId).size();
        return 3000 + (noOfBookedFlight*50);
    }

    public String bookATicket(Integer flightId, Integer passengerId){
        if(ticketMap.containsKey(flightId)){
            List<Integer> list = ticketMap.get(flightId);
            Flight flight = flightMap.get(flightId);
            if(list.size() == flight.getMaxCapacity()){
                return "FAILURE";
            }
            if(list.contains(passengerId)){
                return "FAILURE";
            }
            list.add(passengerId);
            ticketMap.put(flightId,list);
            return "SUCCESS";
        }else{
            List<Integer> list = new ArrayList<>();
            list.add(passengerId);
            ticketMap.put(flightId,list);
            return "SUCCESS";
        }
    }
    public String cancelATicket(Integer flightId, Integer passengerId){
        if(ticketMap.containsKey(flightId)){
            List<Integer> passenList = ticketMap.get(flightId);
            boolean remove = false;
            if(passenList == null){
                return "FAILURE";
            }
            if(passenList.contains(passengerId)){
                passenList.remove(passengerId);
                remove = true;
            }
            if (remove){
                ticketMap.put(flightId, passenList);
                return "SUCCESS";
            } //else return "FAILURE";
        }
        return "FAILURE";
    }
    public int countOfBookingsDoneByPassengerAllCombined(Integer passengerId){
        int count = 0;
        for(List<Integer> list : ticketMap.values()){
            for(Integer i : list){
                if(i == passengerId) count++;
            }
        }
        return count;
    }
    public void addFlight(Flight flight){
        flightMap.put(flight.getFlightId(), flight);
    }
    public String getAirportNameFromFlightId(Integer flightId){
        for(Flight flight : flightMap.values()){
            if(flight.getFlightId() == flightId){
                City city = flight.getFromCity();
                for(Airport airport : airportMap.values()){
                    if(airport.getCity().equals(city)){
                        return airport.getAirportName();
                    }
                }
            }
        }
        return null;
    }
    public int calculateRevenueOfAFlight(Integer flightId){
        int totRevenue = 0;
        if(ticketMap.containsKey(flightId)){
            int count = ticketMap.get(flightId).size();
            for(int i = 0; i < count; i++){
                totRevenue += 3000 + ( i * 50);
            }
            return totRevenue;
        }
        return 0;
    }
    public void addPassenger(Passenger passenger){
        passengerMap.put(passenger.getPassengerId(), passenger);
    }


}
