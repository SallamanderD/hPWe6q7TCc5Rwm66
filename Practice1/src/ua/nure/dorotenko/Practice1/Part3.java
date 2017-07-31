package ua.nure.dorotenko.Practice1;


public class Part3 {
    public static void main(String[] args) {
        System.out.println(gcd(Integer.valueOf(args[0]), Integer.valueOf(args[1])));
    }

    public static int gcd(int a, int b){
        return b == 0 ? a : gcd(b, a % b);
    }
}
