package com.polytech.sma.model;

import java.lang.reflect.Array;
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


    public Agent(int moveIndex, double kPos, double kNeg, Environement environement, int memorySize) {
        this.moveIndex = moveIndex;
        this.kPos = kPos;
        this.kNeg = kNeg;
        this.environement = environement;
        this.memory= new ArrayList<>();
        for(int i =0 ; i<memorySize; i++){
            this.memory.add("0");
        }
        this.nearBySlots = new ArrayList<>();
        this.possibleMovement = new ArrayList<>();
        this.takenElement= null;
    }

    public void setTakenElement(Element takenElement) {
        this.takenElement = takenElement;
    }

    public void action(){

        if(this.takenElement != null){
            double dropProba = calculateProbaDrop(nearbySlotPercentage());
            if(Math.random()<dropProba){
                //drop algo
                ArrayList<Slot> freeSlots = getFreeSlots();
                if(freeSlots.size()>0){
                    Slot slotToDrop = freeSlots.get((int) (Math.random() * freeSlots.size()));
                    environement.dropElementFromAgent(this, slotToDrop);
                    return;
                }
            }
        }else{
            double pickUpAProba = getObjectFrequencyInMemory("A");
            double pickUpBProba = getObjectFrequencyInMemory("B");
            double pickUpProba;
            String letter;
            if(pickUpAProba > pickUpBProba){
                letter = "A";
                pickUpProba = calculateProbaPick(pickUpAProba);
            }else{
                letter = "B";
                pickUpProba = calculateProbaPick(pickUpBProba);
            }
            if(pickUpAProba == pickUpBProba){
                letter = null;
                pickUpProba = calculateProbaPick(pickUpAProba);
            }
            if(Math.random()<pickUpProba){
                //drop algo
                ArrayList<Slot> occupiedSlotsByElements = getElementsCoordinates(letter);
                if(occupiedSlotsByElements.size()>0){
                    Slot slotWithElement = occupiedSlotsByElements.get((int) (Math.random() * occupiedSlotsByElements.size()));
                    this.addElmentmentMemory(slotWithElement.getElement().getLetter());
                    environement.agentPickUpElement(this, slotWithElement);
                    return;
                }

            }
        }

        if(possibleMovement.size()==0){
            return;
        }

        int alea = (int) (Math.random() * possibleMovement.size());
        Movement selectedMove = possibleMovement.get(alea);
        environement.moveAgents(this, selectedMove);
        this.addElmentmentMemory("0");
        return;
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


    public double calculateProbaPick(double f) {
        return Math.pow((this.kPos / (this.kPos + f)), 2);
    }

    public double calculateProbaDrop(double f) {
        return Math.pow((f / (this.kNeg + f)), 2);
    }

    public double getObjectFrequencyInMemory(String letter){
        int occurences = 0;
        for(int i = 0; i < this.memory.size(); i++){
                if(memory.get(i).compareTo(letter) == 0){
                    occurences++;
                }
        }
        return  occurences/this.memory.size();
    }

    public double nearbySlotPercentage(){
        int numberOfSlots = 0;
        int numberOfSameElements = 0;
        for(int i = 0; i<this.nearBySlots.size(); i++){
            for(int j = 0; j<this.nearBySlots.get(i).size(); j++){
                if(this.nearBySlots.get(i).get(j).getElement()!=null && this.nearBySlots.get(i).get(j).getElement().getLetter().compareTo(this.takenElement.getLetter())==0){
                    numberOfSameElements++;
                }
                numberOfSlots++;
            }
        }
        return (double) numberOfSameElements/numberOfSlots;
    }

    private ArrayList<Slot> getFreeSlots(){
        ArrayList<Slot> freeSlots = new ArrayList<>();
        for(int i = 0; i<this.nearBySlots.size(); i++){
            for(int j = 0; j<this.nearBySlots.get(i).size(); j++){
                if(this.nearBySlots.get(i).get(j).isFree()){
                    freeSlots.add(this.nearBySlots.get(i).get(j));
                }
            }
        }
        return freeSlots;
    }

    private ArrayList<Slot> getElementsCoordinates(String letter){
        ArrayList<Slot> elementsCordinates = new ArrayList<>();
        for(int i = 0; i<this.nearBySlots.size(); i++){
            for(int j = 0; j<this.nearBySlots.get(i).size(); j++){
                if(this.nearBySlots.get(i).get(j).getElement()!=null){
                    if(letter == null){
                        elementsCordinates.add(this.nearBySlots.get(i).get(j));
                    }else{
                        if(this.nearBySlots.get(i).get(j).getElement().getLetter().compareTo(letter)==0){
                            elementsCordinates.add(this.nearBySlots.get(i).get(j));
                        }
                    }
                }
            }
        }
        return elementsCordinates;
    }

    public void addElmentmentMemory(String visualized){
        if(memory.size() == 10){
            memory.remove(0);
        }
        memory.add(visualized);
    };

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
