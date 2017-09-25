package ua.nure.dorotenko.Practice2;



/**
 * Created by kingsdwarf on 08.07.17.
 */
public class Demo {
    public static void main(String[] args) {
        MyListImpl con = new MyListImpl();
        con.add(1);
        con.add(2);
        con.add(null);
        con.add(4);
        System.out.println(con.toString());
        con.remove(null);
        System.out.println(con.toString());


    }
}
