package ua.nure.dorotenko.Practice6.part3;

import java.util.ArrayList;
import java.util.List;

public class Parking {
    List<Object> parking;

    public Parking(int count) {
        parking = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            parking.add(null);
        }
        System.out.println(parking);
    }

    public boolean park(Object car, int place) {
        if (parking.contains(car) || car == null) {
            return false;
        }
        for (int i = place - 1; place < parking.size(); place++) {
            if (parking.get(i) == null) {
                parking.set(i, car);
                return true;
            }
        }
        return false;
    }

    public boolean depark(Object car) {
        if (parking.contains(car)) {
            parking.set(parking.lastIndexOf(car), null);
            return true;
        }
        return false;
    }
}