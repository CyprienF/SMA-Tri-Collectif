package com.polytech.sma.model;

import java.util.ArrayList;
import java.util.Objects;

public class Agent{

    private int moveIndex;
    private ArrayList<String> memory;
    private double kPos;
    private double kNeg;
    private Environement environement;

    private Element takenElement;
    private ArrayList<ArrayList<Slot>> nearBySlots;

    private ArrayList<Movement> possibleMovement;


    public Agent(int moveIndex, double kPos, double kNeg, Environement environement) {
        this.moveIndex = moveIndex;
        this.kPos = kPos;
        this.kNeg = kNeg;
        this.environement = environement;
        this.memory= new ArrayList<>();
        this.nearBySlots = new ArrayList<>();
        this.possibleMovement = new ArrayList<>();
        this.takenElement= null;
    }

    public void action(){

        //if agant has element drop

            //return
        //else element pickup
            //see nearby elements

            // return
        //

        // Agent decide to move
        if(possibleMovement.size()==0){
            return;
        }
        int alea = (int) (Math.random() * possibleMovement.size());
        Movement selectedMove = possibleMovement.get(alea);
        environement.moveAgents(this, selectedMove);
    }

    public void perception(){
        environement.agentPerception(this);
    }

    public int getMoveIndex() {
        return moveIndex;
    }

    public ArrayList<String> getMemory() {
        return memory;
    }


    public void addElmentmentMemory(String visualized){
        if(memory.size() == 10){
            memory.remove(0);
        }
        memory.add(visualized);
    };

    public void setMemory(ArrayList<String> memory) {
        this.memory = memory;
    }

    public double getkPos() {
        return kPos;
    }

    public double getkNeg() {
        return kNeg;
    }

    public Element getTakenElement() {
        return takenElement;
    }


    public ArrayList<Movement> getPossibleMovement() {
        return possibleMovement;
    }

    public ArrayList<ArrayList<Slot>> getNearBySlots() {
        return nearBySlots;
    }

    public void setNearBySlots(ArrayList<ArrayList<Slot>> nearBySlots) {
        this.nearBySlots = nearBySlots;
    }

    public void setPossibleMovement(ArrayList<Movement> possibleMovement) {
        this.possibleMovement = possibleMovement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Agent)) return false;
        Agent agent = (Agent) o;
        return getMoveIndex() == agent.getMoveIndex() &&
                Double.compare(agent.getkPos(), getkPos()) == 0 &&
                Double.compare(agent.getkNeg(), getkNeg()) == 0 &&
                Objects.equals(getMemory(), agent.getMemory()) &&
                Objects.equals(environement, agent.environement) &&
                Objects.equals(getTakenElement(), agent.getTakenElement()) &&
                Objects.equals(getNearBySlots(), agent.getNearBySlots()) &&
                Objects.equals(getPossibleMovement(), agent.getPossibleMovement());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMoveIndex(), getMemory(), getkPos(), getkNeg(), environement, getTakenElement(), getNearBySlots(), getPossibleMovement());
    }

}
