package manning.chap03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**环绕执行模式
 * Created by jbwang0106 on 2017/6/28.
 */
public class ExecuteAround {

    public static void main(String [] args) throws IOException {

        String result = processFileLimited();
        System.out.println(result);
        
        System.out.println("---------------");

        //读取一行数据
        String oneline = processFile((BufferedReader br) -> br.readLine());
        System.out.println(oneline);
        System.out.println("---------------");

        //多去多行数据
        String moreline = processFile(br -> br.readLine() + br.readLine() + br.readLine());
        System.out.println(moreline);

    }

    /**
     * 传统的读取文件一行数据的方法
     * @return
     */
    public static String processFileLimited() throws IOException{
        //会自动关闭文件流
        try (BufferedReader br =
                     new BufferedReader(new FileReader("D:\\jbwang\\IdeaProjects\\java8InAction\\src\\main\\java\\manning\\chap03\\data.txt"))) {
            return br.readLine();
        }
    }

    /**
     * 使用lambda表达式，根据需求读取对应多行是数据
     * @param brp
     * @return
     * @throws IOException
     */
    public static String processFile(BufferedReaderProcess brp) throws IOException{
        try (BufferedReader br =
                     new BufferedReader(new FileReader("D:\\jbwang\\IdeaProjects\\java8InAction\\src\\main\\java\\manning\\chap03\\data.txt"))) {
            return brp.process(br);
        }
    }

    @FunctionalInterface
    public interface BufferedReaderProcess {
        public String process(BufferedReader br) throws IOException;

    }
}
