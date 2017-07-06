package manning.chap09;

/**
 * Created by jbwang0106 on 2017/7/6.
 */
public class C implements A, B {
    
    public static void main(String [] args) {
        
        new C().hello();
         
    }
    
//    @Override
//    public void hello() {
//        System.out.println("hello() from C");
//    }
}
