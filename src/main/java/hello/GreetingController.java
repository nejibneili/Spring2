package hello;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Controller
public class GreetingController {
	@Autowired
	private SimpMessagingTemplate template;


    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
    	System.out.println(" hello fr ... ");
        Thread.sleep(1000); // simulated delay ok updated
        return new Greeting("Hello, " + message.getName() + "!");
    }
    
    @Scheduled(fixedDelay = 2000)
    public void pingUsers() {
    	System.out.println(" hello pingUsers ... ");
    	List<Greeting> activeUsers = new ArrayList<Greeting>() ;
    	activeUsers.add(new Greeting("El 1 ok"));
    	activeUsers.add(new Greeting("El 2 ok"));
    	activeUsers.add(new Greeting("El 3 ok"));
    	
      template.convertAndSend("/topic/greetings", activeUsers);
    }

}
