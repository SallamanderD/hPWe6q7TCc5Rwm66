package ua.nure.dorotenko.Practice3;


public class Part5 {
    private static String[] romanic = {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C"};
    private static int[] decimal = {1, 4, 5, 9, 10, 40, 50, 90, 100};
    public static void main(String[] args) {
        for(int i = 1; i <= 100; i++){
            System.out.println(i + " ====> " + decimal2Roman(i) + " ====> " + roman2Decimal(decimal2Roman(i)));
        }
    }

    public static String decimal2Roman(int number){
        int localNumber = number;
        StringBuilder result = new StringBuilder();
        while (localNumber > 0) {
            for(int i = decimal.length - 1; i >= 0; i--){
                if (localNumber >= decimal[i]) {
                    localNumber -= decimal[i];
                    result.append(romanic[i]);
                    break;
                }
            }
        }
        return result.toString();
    }

    public static int roman2Decimal(String number){
        int result = 0;
        int before = -1;
        for(int i = number.length() - 1; i >= 0; i--){
            for(int index = 0; index < romanic.length; index++){
                if(romanic[index].equals(String.valueOf(number.charAt(i)))){
                    if(decimal[index] >= before){
                        result += decimal[index];
                    } else{
                        result -= decimal[index];
                    }
                    before = decimal[index];
                    break;
                }
            }
        }
        return result;
    }
}
