package org.eu.appsick.utils;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.eu.appsick.visit.Visit;

import java.util.ArrayList;

import java.util.List;

public class VisitArray {
    private final int startHour = 8;
    private final int endtHour = 16;
    private ArrayList<Integer> emptyDay = new ArrayList<>((endtHour - startHour) + 1);
    private final JSONArray availableSlots = new JSONArray();

    public VisitArray(List<Visit> visits) {
        initializeDay();
        removeTakenSlots(visits);
        fillAvailableSlots(emptyDay);
    }

    private void fillAvailableSlots(ArrayList<Integer> slots) {
        for (Integer slot : slots) {
            JSONObject item = new JSONObject();
            item.put("time", slot);
            availableSlots.add(item);
        }
    }

    private void removeTakenSlots(List<Visit> visits) {
        for (Visit visit : visits) {
            emptyDay.remove((Integer) visit.getDate()
                                           .getHour());
        }
    }

    private void initializeDay() {
        for (int i = startHour; i < endtHour; i++) {
            emptyDay.add(i);
        }
    }

    public JSONArray getAvailableSlots() {
        return availableSlots;
    }
}
