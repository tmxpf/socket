package com.catchmind.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(StompChatMessage message) throws Exception{
        Thread.sleep(100);
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

    @MessageMapping("/chat")
    @SendTo("/topic/chat")
    public StompChat chat(StompChat chat) throws Exception{
        Thread.sleep(100);
        return new StompChat(chat.getName(), chat.getMessage());
    }

}
