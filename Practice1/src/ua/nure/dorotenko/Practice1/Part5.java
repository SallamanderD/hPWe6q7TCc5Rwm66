package ua.nure.dorotenko.Practice1;

public class Part5 {
    public static void main(String[] args) {
        String[] input = {"A", "B", "Z", "AA", "AZ", "BA", "ZZ", "AAA"};
        for(String s : input){
            System.out.println(s + " ==> " + str2int(s) + " ==> " + int2str(str2int(s)));
        }

    }
    public static int str2int(String number){
        int res = 0;
        for(int i = 0; i < number.length(); i++){
            res += getNumberFromChar(number.charAt(number.length() - 1 - i)) * (int)Math.pow(26, i);
        }
        return res;
    }

    private static int getNumberFromChar(char ch){
        return ch - 64;
    }

    private static char getCharacterFromNumber(int number){
        return (char)(number + 64);
    }

    public static String int2str(int number){
        StringBuilder result = new StringBuilder();
        int number2 = number;
        while(number2 != 0){
            if(number2 % 26 == 0){
                result.append(getCharacterFromNumber(26));
                number2 = (number2 / 26) - 1;
            } else {
                result.append(getCharacterFromNumber(number2 % 26));
                number2 /= 26;
            }
        }
        return result.reverse().toString();
    }

    public static String rightColumn(String number){
        return int2str(str2int(number) + 1);
    }
}
