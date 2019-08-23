package chat;

import org.springframework.stereotype.Repository;
import org.springframework.web.socket.WebSocketSession;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class ChatRoomRepository {

    private final Map<String, ChatRoom> chatRoomMap;
    private final Collection<ChatRoom> chatRooms;

    public ChatRoomRepository() {

        chatRoomMap = Collections.unmodifiableMap(
                        Stream.of(ChatRoom.create("One Room"), ChatRoom.create("Two Room"), ChatRoom.create("Three Room"))
                              .collect(Collectors.toMap(ChatRoom::getId, Function.identity())));

        chatRooms = Collections.unmodifiableCollection(chatRoomMap.values());
    }

    public ChatRoom getChatRoom(String id) {
        return chatRoomMap.get(id);
    }

    public Collection<ChatRoom> getChatRooms() {
        return chatRoomMap.values();
    }

    public void remove(WebSocketSession session) {
        this.chatRooms.parallelStream().forEach(chatRoom -> chatRoom.remove(session));
    }

}
