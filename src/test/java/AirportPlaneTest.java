import models.ClassificationLevel;
import models.MilitaryType;
import org.testng.Assert;
import org.testng.annotations.Test;
import planes.ExperimentalPlane;
import planes.MilitaryPlane;
import planes.PassengerPlane;
import planes.Plane;

import java.util.List;

public class AirportPlaneTest {

    @Test
    public void getTransportMilitaryPlanesTest() {
        Airport airport = new Airport(BaseTest.setUpPlanesPark());
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
        //System.out.println("TEST testGetPassengerPlaneWithMaxCapacity started!");
        Airport airport = new Airport(BaseTest.setUpPlanesPark());
        PassengerPlane expectedPlaneWithMaxPassengersCapacity = airport.getPassengerPlaneWithMaxPassengersCapacity();
        Assert.assertEquals(BaseTest.GetPlaneWithMaxPassengerCapacity(), expectedPlaneWithMaxPassengersCapacity);
    }

    @Test
    public void checkNextPlaneMaxLoadCapacityIsHigherTest() {
        Airport airport = new Airport(BaseTest.setUpPlanesPark());
        airport.sortByMaxLoadCapacity();
        List<? extends Plane> planesSortedByMaxLoadCapacity = airport.getPlanes();

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
        Airport airport = new Airport(BaseTest.setUpPlanesPark());
        List<MilitaryPlane> bomberMilitaryPlanes = airport.getBomberMilitaryPlanes();
        for (MilitaryPlane militaryPlane : bomberMilitaryPlanes) {
            if (!(militaryPlane.getType() == MilitaryType.BOMBER)) {
                Assert.fail("Test failed!");
            }
        }
    }

    @Test
    public void experimentalPlanesHasClassificationLevelHigherThanUnclassifiedTest() {
        Airport airport = new Airport(BaseTest.setUpPlanesPark());
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
