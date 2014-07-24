import com.springapp.mvc.entity.Role;
import com.springapp.mvc.entity.User;
import com.springapp.mvc.repo.UserJDBCTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class MainDemo {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        UserJDBCTemplate userJDBCTemplate = (UserJDBCTemplate)context.getBean("userJDBCTemplate");

        Role role = new Role();
        System.out.println("=======Start=======");
        //userJDBCTemplate.create("Olaf", role);
       // userJDBCTemplate.deleteUser(2);
//        userJDBCTemplate.getUser(3);
//        userJDBCTemplate.updateName(1, "Pojo");
        List<User> users = userJDBCTemplate.getUsers();
        for (User user : users) {
            System.out.println(user.getId() + " " + user.getName());
        }
    }
}
