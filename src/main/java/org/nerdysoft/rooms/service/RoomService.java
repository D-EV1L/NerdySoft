package org.nerdysoft.rooms.service;

import org.nerdysoft.rooms.model.Room;

import java.util.List;

public interface RoomService {

    void create(Room room);

    List<Room> readAll();

    Room read(int id);

    boolean update(Room room, int id);

    boolean delete(int id);
}
