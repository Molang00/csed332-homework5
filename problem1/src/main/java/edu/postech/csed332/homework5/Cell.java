package edu.postech.csed332.homework5;

import edu.postech.csed332.homework5.events.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.BitSet;
import java.util.HashSet;
import java.util.Optional;
import java.util.List;
import java.util.LinkedList;

/**
 * A cell that has a number and a set of possibilities. Even cells can contain only even numbers, and odd cells can
 * contain only odd numbers. A cell may have a number of observers, and notifies events to the observers.
 */
public class Cell extends Subject {
    enum Type {EVEN, ODD}
    Integer number;
    Type type;
    HashSet<Integer> possibility;
    List<Group> groups;

    /**
     * Creates an empty cell with a given type. Initially, no number is assigned.
     *
     * @param type EVEN or ODD
     */
    public Cell(@NotNull Type type) {
        this.number = null;
        this.type = type;
        this.possibility = new HashSet<Integer>();
        this.groups = new LinkedList<Group>();

        if (type == Type.ODD){
            for(int i = 1; i <= 9; i += 2){
                possibility.add(i);
            }
        }
        else{
            for(int i = 2; i <= 8; i += 2){
                possibility.add(i);
            }
        }
    }

    /**
     * Returns the type of this cell.
     *
     * @return the type
     */
    @NotNull
    public Type getType() {
        return this.type;
    }

    /**
     * Returns the number of this cell.
     *
     * @return the number; Optional.empty() if no number assigned
     */
    @NotNull
    public Optional<Integer> getNumber() {
        if (this.number == null){
            return Optional.empty();
        }
        return Optional.of(this.number);
    }

    /**
     * Sets a number of this cell and notifies a SetNumberEvent, provided that the cell has previously no number
     * and the given number to be set is in the set of possibilities.
     *
     * @param number the number
     */
    public void setNumber(int number) {
        if (possibility.contains(number)){
            this.number = number;

            Event e = new SetNumberEvent(number);
            notifyObservers(e);
        }
    }

    /**
     * Removes the number of this cell and notifies an UnsetNumberEvent, provided that the cell has a number.
     */
    public void unsetNumber() {
        if (this.number != null){
            Event e = new UnsetNumberEvent(this.number);
            this.number = null;
            notifyObservers(e);
        }
    }

    /**
     * Adds a group for this cell. This methods should also call addObserver(group).
     *
     * @param group
     */
    public void addGroup(@NotNull Group group) {
        addObserver(group);

        this.groups.add(group);
    }

    /**
     * Returns true if a given number is in the set of possibilities
     *
     * @param n a number
     * @return true if n is in the set of possibilities
     */
    @NotNull
    public Boolean containsPossibility(int n) {
        return this.possibility.contains(n);
    }

    /**
     * Returns true if the cell has no possibility
     *
     * @return true if the set of possibilities is empty
     */
    @NotNull
    public Boolean emptyPossibility() {
        return this.possibility.isEmpty();
    }

    /**
     * Adds the possibility of a given number, if possible, and notifies an EnabledEvent if the set of possibilities,
     * previously empty, becomes non-empty. Even (resp., odd) cells have only even (resp., odd) possibilities. Also,
     * if this number is already used by another cell in the same group with this cell, the number cannot be added to
     * the set of possibilities.
     *
     * @param number the number
     */
    public void addPossibility(int number) {
        boolean possible = true;

        int temp = number % 2;
        Type number_type = (temp == 1) ? type.ODD : type.EVEN;

        possible &= (number_type == this.type);
        for(Group g : groups){
            possible &= g.isAvailable(number);
        }

        if (possible){
            if(this.possibility.isEmpty()){
                Event e = new EnabledEvent();
                notifyObservers(e);
            }
            this.possibility.add(number);
        }
    }

    /*
     * Removes the possibility of a given number. Notifies a DisabledEvent if the set of possibilities becomes empty.
     * Note that even (resp., odd) cells have only even (resp., odd) possibilities.
     *
     * @param number the number
     */
    public void removePossibility(int number) {
        boolean possible = this.possibility.contains(number);

        if (possible){
            this.possibility.remove(number);
            if(this.possibility.isEmpty()){
                Event e = new DisabledEvent();
                notifyObservers(e);
            }

        }
        
    }
}
