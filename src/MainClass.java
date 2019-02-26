import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution {

    public String countAndSay(int n) {

        String pre = "1";
        String result = "";

        if(1 == n){
            return pre;
        }else{
            for(int j=0;j<n;j++){
                int count = 0;
                for(int i=0;i<pre.length();i++){
                    count++;
                    if(i == pre.length()-1 || pre.charAt(i) != pre.charAt(i+1) ){
                        String tmp = count +""+pre.charAt(i);
                        result+=tmp;
                        count = 0;
                    }
                }
                pre = result;
                //System.out.println(pre);
                result = "";
            }
        }
        return pre;
    }
}

public class MainClass {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int n = Integer.parseInt(line);

            String ret = new Solution().countAndSay(n);

            String out = (ret);

            System.out.print(out);
        }
    }
}