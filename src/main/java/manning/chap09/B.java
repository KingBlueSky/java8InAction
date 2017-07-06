package manning.chap09;

/**
 * Created by jbwang0106 on 2017/7/6.
 */
public interface B extends A{
    
    default void hello() {
        System.out.println("hello() from B");
    }
}
