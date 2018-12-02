class Solution {

    private String add(String tmp,int count){
        if(count <= 3){
            for(int i = 0;i < count;i++){
                tmp +="I";
            }
        }else if(count == 4){
            tmp +="IV";
        }else if(count < 9 && count >4){
            tmp +="V";
            for(int i = 0;i < count-5;i++){
                tmp +="I";
            }
        }else {
            tmp += "IX";
        }
        return tmp;
    }

    private String add10(String tmp,int count){
        if(count <= 3){
            for(int i = 0; i < count ;i++){
                tmp +="X";
            }
        }else if(count == 4){
            tmp += "XL";
        }else if(count < 9 && count >4){
            tmp +="L";
            for(int i = 0;i < count-5;i++){
                tmp +="X";
            }
        }else{
            tmp += "XC";
        }
        return tmp;
    }
    private String add100(String tmp,int count){
        if(count <= 3){
            for(int i = 0; i < count ;i++){
                tmp +="C";
            }

        }else if(count == 4){
            tmp += "CD";
        }else if(count < 9 && count >4){
            tmp +="D";
            for(int i = 0;i < count-5;i++){
                tmp +="C";
            }
        }else{
            tmp += "CM";
        }
        return tmp;
    }

    private String add1000(String tmp,int count){
        if(count <= 3){
            for(int i = 0; i < count ;i++){
                tmp +="M";
            }
        }else {
            return "error";
        }
        return tmp;
    }

    public String intToRoman(int num) {

        String result = "";
        String numS = (num+"");

        if(num >= 1000){
            result = add1000(result,numS.charAt(0)-'0');
          //  System.out.println(result);
            result = add100(result,numS.charAt(1)-'0');
          //  System.out.println(result);

            result = add10(result,numS.charAt(2)-'0');
          //  System.out.println(result);

            result = add(result,numS.charAt(3)-'0');
            //System.out.println(result);

        }else if(num <1000 && num>=100){
            result = add100(result,numS.charAt(0)-'0');
            result = add10(result,numS.charAt(1)-'0');
            result = add(result,numS.charAt(2)-'0');
        }else if(num<100 && num>=10){
            result = add10(result,numS.charAt(0)-'0');
            result = add(result,numS.charAt(1)-'0');
        }else{
            result = add(result,numS.charAt(0)-'0');
        }
        return result;
    }

}
public class MainClass {
    public static void main(String[] args){
        int[] tmp = {1,8,6,2,5,4,8,3,7};
        String x = new Solution().intToRoman(58);
        System.out.println(x);
    }
}