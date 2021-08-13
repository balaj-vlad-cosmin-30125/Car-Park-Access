package isp.lab8.carparkaccess;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class CarParkingController {
    private final int carParkCapacity = 3;
    List<Car> ParkedCarsList = new ArrayList<>();
    private List<Action> LogList = new ArrayList<>();
    private Map<String, Action> hash = new HashMap<>();


    public List<Car> getParkedCarsList() {
        return ParkedCarsList;
    }

    public boolean EnterCarPark(Car c) throws IOException {
        if (!CheckAvailableSpace(carParkCapacity)) {
            return false;
        }
        for (Car spot : ParkedCarsList)
            if (spot.getPlateNumber().equals(c.getPlateNumber())) {
                Action a = new Action(c, new Date(), 0, "The car is already parked ");
                LogList.add(a);
                hash.put(c.getPlateNumber(), a);
                return false;
            }


        ParkedCarsList.add(c);
        Action a = new Action(c, new Date(), 0, "The car with the plate number " + c.getPlateNumber() +" has entered the parking lot ");
        LogList.add(a);
        System.out.println("The car with the plate number " + c.getPlateNumber() +" has entered the parking lot ");
        System.out.println("\n");
        hash.put(c.getPlateNumber(), a);

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());


        return true;
    }

    public boolean CheckAvailableSpace(int carParkCapacity) {
        if (carParkCapacity > ReturnNumberOfParkedCars()) {
            return true;
        }
        return false;
    }

    public String ExitCarPark(Car c) throws IOException {

        ParkedCarsList.remove(c);

        long cost = CostCalculator(new Date(), c);
        Action a = new Action(c, new Date(), cost, "The car with the plate number "+ c.getPlateNumber() +"  left the parking lot");
        LogList.add(a);
        System.out.println("The car with the plate number "+ c.getPlateNumber() +"  left the parking lot");
        hash.put(c.getPlateNumber(), a);
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());


        writer.writeValue(Paths.get("car.json").toFile(), hash);

        return "The final cost is "+ cost;
    }

    public long CostCalculator(Date exitTime, Car c) {
        long cost = 0;
        Action a = LogList.get(0);
        for (Action acc : LogList)
            if (acc.getC().equals(c))
                a = acc;
        cost = (exitTime.getTime() - a.entryTime.getTime()) / 1000;
        return cost;
    }

    public Set<Car> UniquePastEntries() {
        Set<Car> UniqueCarsList = new HashSet<>();
        for (Action acc : LogList)
            UniqueCarsList.add(acc.c);
        return UniqueCarsList;
    }

    public int ReturnNumberOfParkedCars() {
        return ParkedCarsList.size();
    }

    public List<Car> ViewCurrentParkedCars() {
        return ParkedCarsList;

    }

    public List<Action> getLogList() {
        return LogList;
    }


}
