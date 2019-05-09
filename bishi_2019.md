# 华为2019校招笔试
### 1. 
+ 题目描述
```
旋转N阶方阵M次，输出方阵结果
```
+ 答案（100%）
```java

import java.util.*;

public class Main {

    public static void main(String[] args) {
        int n = 0;
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        int[][] array = new int[n][];
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            array[i] = new int[n];
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                array[i][j] = scanner.nextInt();
                list.add(array[i][j]);
            }
        }

        int m = scanner.nextInt();

        if (m % 4 != 0) {
            int index=0;
            int tmp = m % 4;
            if(tmp==1){
                for(int j=n-1;j>-1;j--){
                    for(int i=0;i<n;i++){
                        array[i][j] = list.get(index);
                        index++;
                    }
                }
            }else if(tmp==2){
                Collections.reverse(list);
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        array[i][j] = list.get(index);
                        index++;
                    }
                }
            }else if(tmp==3){
                for(int j=0;j<n;j++){
                    for(int i=n-1;i>-1;i--){
                        array[i][j]  = list.get(index);
                        index++;
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(array[i][j]);
                if (j < n - 1) {
                    System.out.print(" ");
                } else {
                    System.out.println();
                }
            }
        }

    }
}
```

### 2.
+ 题目描述
```
n个礼物分发给k个小朋友，多少种分法，并打印
```
+ 答案（100%）
```java

import java.util.*;

public class Main {

    public static void main(String[] args) {
        int n=0,k=0;
        List<String> result = new ArrayList<>();
        List<String> gitfs = new ArrayList<>();
        List<String> kids = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        k = scanner.nextInt();


        for(int i=0;i<n;i++){
            gitfs.add("*");
        }

        for(int j=0;j<k-1;j++){
            kids.add("|");
        }

        getResult(result,0,n,0,k-1,"");
        System.out.println(result.size());
        for(int i=0;i<result.size();i++){
            System.out.println(result.get(i));
        }

    }

    private static void getResult(List<String> result, int gitfs,int targetGitfs, int kids,int targetKids,String current) {
        if(gitfs==targetGitfs&&kids==targetKids){
            result.add(current);
        }

        if(gitfs<targetGitfs){
            getResult(result,gitfs+1,targetGitfs,kids,targetKids,current+"*");
        }
        if(kids<targetKids){
            getResult(result,gitfs,targetGitfs,kids+1,targetKids,current+"|");
        }

    }
}
```

### 3.
+ 题目描述
```
给定n行字符串，和n行修改后的字符串，求最小修改次数
```
+ 答案（16%）
```java

import java.util.*;

public class Main {

    public static void main(String[] args) {

        List<String> pre = new ArrayList<>();
        List<String> after = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        for(int i=0;i<n;i++){
            String tmp= scanner.nextLine();
            pre.add(tmp);
        }

        for(int i=0;i<n;i++){
            String tmp = scanner.nextLine();
            after.add(tmp);
        }

        int sum = 0;
        for(int i=0;i<n;i++){
            sum += compareString(pre.get(i),after.get(i));
        }
        System.out.println(sum);
    }

    private static int compareString(String s, String s1) {
        int result = 0;
        String tmp = "";
        for(int i=0;i<s.length();i++){
            int j=i;
            while(j<s.length()){
                tmp+=s.charAt(j);
                if(s1.contains(tmp)==false){
                    break;
                }
                j++;
            }
        }

        return result;
    }

}
```