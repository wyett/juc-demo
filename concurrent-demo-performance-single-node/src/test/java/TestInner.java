import com.wyett.order.TargetApplication;
import com.wyett.order.service.OrderService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

/**
 * @author : wyettLei
 * @date : Created in 2020/2/29 15:49
 * @description: TODO
 */

@SpringBootTest(classes = TargetApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TestInner {
    public static final int MAX_THREADS = 1000;

    @Autowired
    OrderService orderService;

    public void testInterface() {
        Map<String, Object> result = orderService.queryOrderInfo("123456");
        System.out.println(result);
    }

}
