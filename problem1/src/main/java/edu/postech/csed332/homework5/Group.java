package edu.postech.csed332.homework5;

import edu.postech.csed332.homework5.events.Event;
import edu.postech.csed332.homework5.events.SetNumberEvent;
import edu.postech.csed332.homework5.events.UnsetNumberEvent;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A group that observes a set of cells, and maintains the invariant: if one of the members has a particular value,
 * none of its other members can have the value as a possibility.
 */
public class Group implements Observer {
    //TODO: add private member variables for Board
    HashSet<Cell> cells;

    /**
     * Creates an empty group.
     */
    Group() {
        //TODO: implement this
        cells = new HashSet<Cell>();
    }

    /**
     * Adds a cell to this group. Use cell.addGroup to register this group.
     *
     * @param cell a cell to be added
     */
    void addCell(Cell cell) {
        //TODO: implement this
    }

    /**
     * Returns true if a given cell is belong to this group
     *
     * @param cell a cell
     * @return true if this group contains cell
     */
    @NotNull
    Boolean contains(@NotNull Cell cell) {
        //TODO: implement this
        return null;
    }

    /**
     * Returns true if a given number is available in the group
     *
     * @param number a number
     * @return true if no cell in the group has a given number.
     */
    @NotNull
    public Boolean isAvailable(int number) {
        //TODO: implement this

        for(Cell c: cells){
            // if (c.containsPossibility(number)){
            //     return true;
            // }
        }
        return false;
    }

    /**
     * Whenever a cell is changed, this function is called. Two kinds of events, SetNumberEvent and UnsetNumberEvent,
     * should be handled here.
     *
     * @param caller the subject
     * @param arg    an argument
     */
    @Override
    public void update(Subject caller, Event arg) {
        //TODO: implement this

        // 얘가 갖고 있는 cell 들의 possibility 다 없애주고,
        // 빨간색 해줘야 할수도 있고
        // unsetnumberevent 면 빨간색 없애줘야 할 수도 잇고
        
    }
}
