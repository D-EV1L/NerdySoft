package org.nerdysoft.rooms.controller;

import org.nerdysoft.rooms.model.InvalidRoom;
import org.nerdysoft.rooms.model.Room;
import org.nerdysoft.rooms.service.RoomService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
public class RoomController {

    private final RoomService roomService;
    private final RoomValidator roomValidator = new RoomValidator();

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping(value = "/addRoom")
    public ResponseEntity<?> create(@RequestBody String room) {
        if (roomValidator.check(room)) {
            roomService.create(roomValidator.getRoom());
            return new ResponseEntity<>(roomValidator.getRoom(), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(new InvalidRoom(roomValidator.getErrorMessage()), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping(value = "/validateRoom")
    public ResponseEntity<?> create(@RequestBody Room room) {
        if (roomValidator.check(room)) {
            roomService.create(room);
            return new ResponseEntity<>(room, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(new InvalidRoom(roomValidator.getErrorMessage()), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping(value = "/rooms")
    public ResponseEntity<List<Room>> read() {
        final List<Room> rooms = roomService.readAll();

        return rooms != null &&  !rooms.isEmpty()
                ? new ResponseEntity<>(rooms, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/rooms/{id}")
    public ResponseEntity<Room> read(@PathVariable(name = "id") int id) {
        final Room room = roomService.read(id);

        return room != null
                ? new ResponseEntity<>(room, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/rooms/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Room room) {
        if (roomValidator.check(room)) {
            if (roomService.update(room, id)) {
               return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
            }
        } else {
            return new ResponseEntity<>(new InvalidRoom(roomValidator.getErrorMessage()), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping(value = "/rooms/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = roomService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
