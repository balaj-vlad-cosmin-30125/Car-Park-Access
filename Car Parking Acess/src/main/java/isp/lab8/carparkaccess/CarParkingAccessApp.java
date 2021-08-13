package isp.lab8.carparkaccess;


import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class CarParkingAccessApp {
    public static void main(String[] args) throws InterruptedException, IOException {
        System.out.println("It works!");
        ObjectMapper objectMapper = new ObjectMapper();
        CarParkingController control = new CarParkingController();
        Car c1 = new Car("CJ-01-ABC");
        Car c2 = new Car("CJ-01-BCD");
        Car c3 = new Car("CJ-01-ABF");
        Car c4 = new Car("CJ-02-ADG");

        List<Car> list = control.getParkedCarsList();
        System.out.println(control.EnterCarPark(c1));
        System.out.println(control.EnterCarPark(c2));
        System.out.println(control.EnterCarPark(c3));

        TimeUnit.SECONDS.sleep(5);
        System.out.println( control.ExitCarPark(c2));
        System.out.println("\n");

        List<Action> List1 = control.getLogList();
        for (Action a : List1) {
            System.out.println(a.c.getPlateNumber()+"\n"+a.message+"\n"+a.entryTime+"\n"+a.periodInSeconds);
        }

        control.getParkedCarsList();
        System.out.println("\n");
        TimeUnit.SECONDS.sleep(3);

        System.out.println(control.ExitCarPark(c3));
        System.out.println("\n");
                List<Action> List2 = control.getLogList();
        for (Action a : List2) {
            System.out.println(a.c.getPlateNumber()+"\n"+a.message+"\n"+a.entryTime+"\n"+a.periodInSeconds);
        }



    }
}
