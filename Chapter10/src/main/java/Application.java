import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//通过这个链接访问生成的接口文档 http://localhost:8988/swagger-ui.html
//这是一个启动类
@SpringBootApplication
@ComponentScan("com.course")     //需要扫描的包
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
