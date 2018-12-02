class Solution {
    public int romanToInt(String s) {
        int result = 0;

        for(int i =0;i < s.length();i++){
            char tmp = s.charAt(i);
            if(tmp == 'M'){
                result += 1000;
            }else if(tmp == 'C' && (i+1) < s.length() && s.charAt(i+1) == 'M'){
                result += 900;
                i++;
                continue;
            }else if(tmp == 'D'){
                result += 500;
            }else if(tmp == 'C' && (i+1) < s.length() && s.charAt(i+1) == 'D'){
                result += 400;
                i++;
                continue;
            }else if(tmp == 'C'){
                result += 100;
            }else if(tmp == 'X' && (i+1) < s.length() && s.charAt(i+1) == 'C'){
                result += 90;
                i++;
                continue;
            }else if(tmp == 'L'){
                result += 50;
            }else if(tmp == 'X' && (i+1) < s.length() && s.charAt(i+1) == 'L'){
                result += 40;
                i++;
                continue;
            }else if(tmp == 'X'){
                result += 10;
            }else if(tmp == 'I'  && (i+1) < s.length() && s.charAt(i+1) == 'X'){
                result += 9;
                i++;
                continue;
            }else if(tmp == 'V'){
                result += 5;
            }else if(tmp == 'I' && (i+1) < s.length() && s.charAt(i+1) == 'V'){
                result += 4;
                i++;
                continue;
            }else if(tmp == 'I'){
                result += 1;
            }
        }

        return result;
    }
}
public class MainClass {
    public static void main(String[] args){
        int[] tmp = {1,8,6,2,5,4,8,3,7};
        int x = new Solution().romanToInt("MCMXCIV");
        System.out.println(x);
    }
}