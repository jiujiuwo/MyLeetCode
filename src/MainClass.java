import java.util.ArrayList;
import java.util.List;

class MainClass {
    public static void main(String[] args){
        RandomListNode randomListNode = new RandomListNode(1);
        List<RandomListNode> test = new ArrayList<>();
        test.add(randomListNode);
        System.out.println(randomListNode==test.get(0));//true
        System.out.println(randomListNode.equals(test.get(0)));//true
    }
}