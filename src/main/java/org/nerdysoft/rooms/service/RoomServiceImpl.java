package org.nerdysoft.rooms.service;

import org.nerdysoft.rooms.model.Room;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class RoomServiceImpl implements RoomService {

    // rooms database
    private static final Map<Integer, Room> ROOM_REPOSITORY_MAP = new HashMap<>();

    // ID generator var
    private static final AtomicInteger ROOM_ID_HOLDER = new AtomicInteger();

    @Override
    public void create(Room room) {
        ROOM_REPOSITORY_MAP.put(ROOM_ID_HOLDER.incrementAndGet(), room);
    }

    @Override
    public List<Room> readAll() {
        return new ArrayList<>(ROOM_REPOSITORY_MAP.values());
    }

    @Override
    public Room read(int id) {
        return ROOM_REPOSITORY_MAP.get(id);
    }

    @Override
    public boolean update(Room room, int id) {
        if (ROOM_REPOSITORY_MAP.containsKey(id)) {
            ROOM_REPOSITORY_MAP.put(id, room);
            return true;
        }

        return false;
    }

    @Override
    public boolean delete(int id) {
        return ROOM_REPOSITORY_MAP.remove(id) != null;
    }
}
