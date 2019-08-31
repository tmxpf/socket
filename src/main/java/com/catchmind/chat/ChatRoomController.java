package com.catchmind.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.atomic.AtomicInteger;

@Controller
@RequestMapping
public class ChatRoomController {

    private ChatRoomRepository repository;
    private AtomicInteger seq = new AtomicInteger(0);

    @Autowired
    public void rooms(ChatRoomRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/com/catchmind/chat/rooms")
    public String rooms(Model model) {
        model.addAttribute("rooms", repository.getChatRooms());

        return "chat1/room-list";
    }

    @GetMapping("/com/catchmind/chat/rooms/{id}")
    public String room(@PathVariable String id, Model model) {
        ChatRoom room = repository.getChatRoom(id);

        model.addAttribute("room", room);
        model.addAttribute("member", "member" + seq.incrementAndGet());

        return "chat1/room";
    }

}
