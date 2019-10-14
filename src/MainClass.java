
class A{
    public int a=0;
}

class B extends A{
    public int a = 1;
}

class C extends B{
    public int a = 2;
}

class D extends C{

}

class MainClass {
    public static void main(String[] args){

        System.out.println(new D().a);
        String s = "abc";
        System.out.println(s.substring(0,s.length()));
    }
}