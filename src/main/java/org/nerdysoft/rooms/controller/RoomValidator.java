package org.nerdysoft.rooms.controller;

import org.nerdysoft.rooms.model.Point;
import org.nerdysoft.rooms.model.Room;

import java.util.ArrayList;
import java.util.List;

public class RoomValidator {

    private Room room;
    private int[][] points;
    private int[][] vectors;
    private String errorMessage;

    /* Checking whether the coordinates are integers */
    public boolean check(String arrayOfPoints) {
        List<Point> points = new ArrayList<>();

        String[] coords = arrayOfPoints.trim().split(",");
        for (int i = 0; i < coords.length; i++) {
            int x, y;
            try {
                x = Integer.parseInt(coords[i].trim().replaceAll("^\\(?", ""));
                y = Integer.parseInt(coords[++i].trim().replaceAll("\\)?$", ""));
            } catch (NumberFormatException e) {
                errorMessage = "Some coordinates are not integers.";
                return false;
            }
            points.add(new Point(x, y));
        }
        Room room = new Room();
        room.setRoom(points);
        return check(room);
    }

    public boolean check(Room room) {
        this.room = room;
        init(room.getRoom());

        if (points.length < 4) {
            errorMessage = "It only has " + points.length + " corner" + (points.length == 1 ? "." : "s.");
            return false;
        } else if (points.length % 2 != 0) {
            errorMessage = "It has non-right angles.";
            return false;
        }

        int turnRight = 0;
        for (int i = 0; i < vectors.length; i++) {
            if (vectors[i][0] != 0 && vectors[i][1] != 0) {
                errorMessage = "Wall going diagonally.";
                return false;
            }

            int j = i != vectors.length - 1 ? i + 1 : 0;
            int cp = vectors[i][0] * vectors[j][0] + vectors[i][1] * vectors[j][1];
            if (cp != 0) {
                errorMessage = "It has non-right angles.";
                return false;
            }

            if (vectors[i][0] == 0 && vectors[i][1] * vectors[j][0] > 0) turnRight++;
            if (vectors[i][1] == 0 && vectors[i][0] * vectors[j][1] < 0) turnRight++;
        }

        if (turnRight < vectors.length / 2) {
            errorMessage = "It is infinite room.";
            return false;
        } else if (turnRight == vectors.length / 2) {
            errorMessage = "Some walls intersect.";
            return false;
        }

        return true;
    }

    private void init(List<Point> listOfPoints) {

        points = new int[listOfPoints.size()][2];
        for (int i = 0; i < points.length; i++) {
            int x = listOfPoints.get(i).getX();
            int y = listOfPoints.get(i).getY();
            points[i] = new int[]{x, y};
        }

        vectors = new int[listOfPoints.size()][2];
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points[i].length; j++) {
                vectors[i][j] = points[i != points.length - 1 ? i + 1 : 0][j] - points[i][j];
            }
        }
    }

    public Room getRoom() {
        return room;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
