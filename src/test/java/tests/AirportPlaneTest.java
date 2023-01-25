package tests;

import airports.Airport;
import models.ClassificationLevel;
import models.MilitaryType;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import planes.ExperimentalPlane;
import planes.MilitaryPlane;
import planes.PassengerPlane;
import planes.Plane;
import testdata.TestDataKeeper;

import java.util.List;

public class AirportPlaneTest {

    private static Airport airport;

    @BeforeTest
    private void createAirport() {
        airport = new Airport(TestDataKeeper.setUpPlanesPark());
    }

    @Test
    public void getTransportMilitaryPlanesTest() {
        List<MilitaryPlane> transportMilitaryPlanes = airport.getTransportMilitaryPlanes();
        boolean flag = false;
        for (MilitaryPlane militaryPlane : transportMilitaryPlanes) {
            if ((militaryPlane.getType() == MilitaryType.MILITARY_TRANSPORT)) {
                flag = true;
                break;
            }
        }
        Assert.assertTrue(flag);
    }

    @Test
    public void getPassengerPlaneWithMaxCapacityTest() {
        PassengerPlane expectedPlaneWithMaxPassengersCapacity = airport.getPassengerPlaneWithMaxPassengersCapacity();
        Assert.assertEquals(TestDataKeeper.getExpectedResultPlaneWithMaxPassengerCapacity(), expectedPlaneWithMaxPassengersCapacity);
    }

    @Test
    public void checkNextPlaneMaxLoadCapacityIsHigherTest() {
        airport.sortByMaxLoadCapacity();
        List<? extends Plane> planesSortedByMaxLoadCapacity = airport.getAllPlainsInTheAirport();

        boolean nextPlaneMaxLoadCapacityIsHigherThanCurrent = true;
        for (int i = 0; i < planesSortedByMaxLoadCapacity.size() - 1; i++) {
            Plane currentPlane = planesSortedByMaxLoadCapacity.get(i);
            Plane nextPlane = planesSortedByMaxLoadCapacity.get(i + 1);
            if (currentPlane.getMaxPlaneLoadCapacity() > nextPlane.getMaxPlaneLoadCapacity()) {
                nextPlaneMaxLoadCapacityIsHigherThanCurrent = false;
                break;
            }
        }
        Assert.assertTrue(nextPlaneMaxLoadCapacityIsHigherThanCurrent);
    }

    @Test
    public void militaryPlanesHasAtLeastOneBomberTest() {
        List<MilitaryPlane> bomberMilitaryPlanes = airport.getBomberMilitaryPlanes();
        for (MilitaryPlane militaryPlane : bomberMilitaryPlanes) {
            if (!(militaryPlane.getType() == MilitaryType.BOMBER)) {
                Assert.fail("Test failed!");
            }
        }
    }

    @Test
    public void experimentalPlanesHasClassificationLevelHigherThanUnclassifiedTest() {
        List<ExperimentalPlane> ExperimentalPlanes = airport.getExperimentalPlanes();
        boolean hasUnclassifiedPlanes = false;
        for (ExperimentalPlane experimentalPlane : ExperimentalPlanes) {
            if (experimentalPlane.getClassificationLevel() == ClassificationLevel.UNCLASSIFIED) {
                hasUnclassifiedPlanes = true;
                break;
            }
        }
        Assert.assertFalse(hasUnclassifiedPlanes);
    }
}
