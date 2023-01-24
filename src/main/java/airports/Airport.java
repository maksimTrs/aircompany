package airports;

import models.MilitaryType;
import planes.ExperimentalPlane;
import planes.MilitaryPlane;
import planes.PassengerPlane;
import planes.Plane;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * version: 1.1
 * made by Vitali Shulha
 * 4-Jan-2019
 */


public class Airport {
    private List<? extends Plane> allPlainsInTheAirport;

    public Airport(List<? extends Plane> planes) {
        this.allPlainsInTheAirport = planes;
    }


    public List<PassengerPlane> getPassengerPlanes() {
        List<? extends Plane> listOfPassengerPlanes = this.allPlainsInTheAirport;
        List<PassengerPlane> passengerPlanes = new ArrayList<>();
        for (Plane plane : listOfPassengerPlanes) {
            if (plane instanceof PassengerPlane) {
                passengerPlanes.add((PassengerPlane) plane);
            }
        }
        return passengerPlanes;
    }

    public List<MilitaryPlane> getMilitaryPlanes() {
        List<MilitaryPlane> militaryPlanes = new ArrayList<>();
        for (Plane plane : allPlainsInTheAirport) {
            if (plane instanceof MilitaryPlane) {
                militaryPlanes.add((MilitaryPlane) plane);
            }
        }
        return militaryPlanes;
    }

    public PassengerPlane getPassengerPlaneWithMaxPassengersCapacity() {
        List<PassengerPlane> passengerPlanesList = getPassengerPlanes();
        PassengerPlane planeWithMaxPassengerCapacity = passengerPlanesList.get(0);
        for (int i = 0; i < passengerPlanesList.size(); i++) {
            if (passengerPlanesList.get(i).getPlanePassengersCapacity() > planeWithMaxPassengerCapacity.getPlanePassengersCapacity()) {
                planeWithMaxPassengerCapacity = passengerPlanesList.get(i);
            }
        }
        return planeWithMaxPassengerCapacity;
    }

    public List<MilitaryPlane> getTransportMilitaryPlanes() {
        List<MilitaryPlane> transportMilitaryPlanes = new ArrayList<>();
        for (int i = 0; i < getMilitaryPlanes().size(); i++) {
            MilitaryPlane plane = getMilitaryPlanes().get(i);
            if (plane.getType() == MilitaryType.MILITARY_TRANSPORT) {
                transportMilitaryPlanes.add(plane);
            }
        }
        return transportMilitaryPlanes;
    }

    public List<MilitaryPlane> getBomberMilitaryPlanes() {
        List<MilitaryPlane> bomberMilitaryPlanes = new ArrayList<>();
        for (MilitaryPlane plane : getMilitaryPlanes()) {
            if (plane.getType() == MilitaryType.BOMBER) {
                bomberMilitaryPlanes.add(plane);
            }
        }
        return bomberMilitaryPlanes;
    }

    public List<ExperimentalPlane> getExperimentalPlanes() {
        List<ExperimentalPlane> ExperimentalPlanes = new ArrayList<>();
        for (Plane plane : allPlainsInTheAirport) {
            if (plane instanceof ExperimentalPlane) {
                ExperimentalPlanes.add((ExperimentalPlane) plane);
            }
        }
        return ExperimentalPlanes;
    }

    public Airport sortByMaxDistance() {
        allPlainsInTheAirport.sort(Comparator.comparingInt(Plane::getPlaneMaxFlightDistance));
        return this;
    }


    public Airport sortByMaxSpeed() {
        allPlainsInTheAirport.sort(Comparator.comparingInt(Plane::getPlaneMaxSpeed));
        return this;
    }

    public Airport sortByMaxLoadCapacity() {
        allPlainsInTheAirport.sort(Comparator.comparingInt(Plane::getMaxPlaneLoadCapacity));
        return this;
    }

    public List<? extends Plane> getAllPlainsInTheAirport() {
        return allPlainsInTheAirport;
    }


    @Override
    public String toString() {
        return "airports.Airport{" +
                "Planes=" + allPlainsInTheAirport.toString() +
                '}';
    }

}
