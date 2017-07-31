package ua.nure.dorotenko.Practice1;

public class Part4 {
    public static void main(String[] args) {
        int x = Integer.valueOf(args[0]);
        int sum = 0;
        while(x != 0){
            sum += x % 10;
            x /= 10;
        }
        System.out.println(sum);
    }
}
