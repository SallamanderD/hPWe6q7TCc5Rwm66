package ua.nure.dorotenko.Practice6.part3;

public class Part3 {
    public static void main(String[] args) {
        Parking p = new Parking(10);
        p.park("asd", 3);
        p.park("dsa", 4);
        p.park("dsa", 5);
        p.park(3, 7);
        System.out.println(p.parking);
        p.depark(3);
        System.out.println(p.parking);
    }
}
