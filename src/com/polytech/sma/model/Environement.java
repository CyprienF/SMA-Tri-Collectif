package com.polytech.sma.model;

import java.util.ArrayList;

/**
 *
 * @author Stefano
 */
public class Environement {

    private Slot myGrid[][];
    private int sizeN;
    private int sizeM;

    private int numberOfAgents;
    private int numberOfObjects;
    private int AGENT_MOVE_INDEX;

    private ViewGrid view;
    private ArrayList<Agent> agentsList;

    public Environement(int sizeM, int sizeN, int numberOfAgents, int numberOfObject, int agentMoveIndex) {
        this.sizeM = sizeM;
        this.sizeN = sizeN;
        this.numberOfAgents = numberOfAgents;
        this.numberOfObjects = numberOfObject;
        this.AGENT_MOVE_INDEX =agentMoveIndex;
        this.agentsList = new ArrayList<>();
        myGrid = new Slot[sizeM][sizeN];
        for (int i = 0; i < sizeM; i++) {
            for (int j = 0; j < sizeN; j++) {
                myGrid[i][j] = new Slot(i, j);
            }
        }
        generateGrid();
    }

    public void generateGrid() {
        ArrayList<Slot> freeSlots = new ArrayList();

        for (int i = 0; i < sizeM; i++) {
            for (int j = 0; j < sizeN; j++) {
                if (myGrid[i][j].getValue() == 0) {
                    freeSlots.add(myGrid[i][j]);
                }
            }
        }

        //Generates all the agents
        for(int i = 1; i<= numberOfAgents ; i++){
            int alea = (int) (Math.random() * freeSlots.size());
            agentsList.add(new Agent(this.AGENT_MOVE_INDEX,0.1,0.3, this, 10));
            myGrid[freeSlots.get(alea).getX()][freeSlots.get(alea).getY()].setAgent(agentsList.get(agentsList.size()-1));
            freeSlots.remove(alea);
        }


        //Generate all objects
        for(int i = 1; i<= numberOfObjects ; i++){
            int alea = (int) (Math.random() * freeSlots.size());
            myGrid[freeSlots.get(alea).getX()][freeSlots.get(alea).getY()].setElements(new Element("A"));
            freeSlots.remove(alea);
            alea = (int) (Math.random() * freeSlots.size());
            myGrid[freeSlots.get(alea).getX()][freeSlots.get(alea).getY()].setElements(new Element("B"));
            freeSlots.remove(alea);
        }
    }


    public void setView(ViewGrid view) {
        this.view = view;
    }


    public int getSizeN() {
        return sizeN;
    }


    public int getSizeM() {
        return sizeM;
    }

    public int getNumberOfAgents() {
        return numberOfAgents;
    }

    public ArrayList<Agent> getAgentsList() {
        return agentsList;
    }


    public int getSlot(int i, int j) {
        return myGrid[i][j].getValue();
    }

    public int[] agentCoordinates(Agent agent){
        int[] coordinates = new int[2];
        for(int i = 0 ; i<this.myGrid.length; i++){
            for(int j = 0 ; j<this.myGrid[i].length; j++){
                if(myGrid[i][j].getAgent() == agent ){
                    coordinates[0]=i;
                    coordinates[1]=j;
                    break;
                }
            }
        }
        return coordinates;
    }

    public void moveAgents(Agent agent, Movement movement){
        int[] coordinates = agentCoordinates(agent);

        switch (movement){
            case UP:
               this.myGrid[coordinates[0]-agent.getMoveIndex()][coordinates[1]].setAgent(agent);
                break;
            case DOWN:
                this.myGrid[coordinates[0]+agent.getMoveIndex()][coordinates[1]].setAgent(agent);
                break;
            case RIGHT:
                this.myGrid[coordinates[0]][coordinates[1]+agent.getMoveIndex()].setAgent(agent);
                break;
            case LEFT:
                this.myGrid[coordinates[0]][coordinates[1]-agent.getMoveIndex()].setAgent(agent);
                break;
        }
        this.myGrid[coordinates[0]][coordinates[1]].setAgent(null);

    }

    public void agentPickUpElement(Agent agent, Slot pickupSlot){
        agent.setTakenElement(this.myGrid[pickupSlot.getX()][pickupSlot.getY()].getElement());
        this.myGrid[pickupSlot.getX()][pickupSlot.getY()].setElements(null);
    }

    public void dropElementFromAgent(Agent agent, Slot dropSlot){
        this.myGrid[dropSlot.getX()][dropSlot.getY()]
                .setElements(agent.getTakenElement());
        agent.setTakenElement(null);
    }

    public void notifyView(){
        this.view.repaint();
    }

    public void agentPerception(Agent agent){
        int[] coordinates = agentCoordinates(agent);

        //Possible mouvement
        int agentX = coordinates[0];
        int agentY = coordinates[1];

        ArrayList<Movement> autorizedMovements = new ArrayList<>();

        if((agentX-agent.getMoveIndex())>=0 && myGrid[agentX-agent.getMoveIndex()][agentY].isFree() ){
            autorizedMovements.add(Movement.UP);
        }
        if((agentY-agent.getMoveIndex())>=0 && myGrid[agentX][agentY-agent.getMoveIndex()].isFree()){
            autorizedMovements.add(Movement.LEFT);
        }
        if((agentX+agent.getMoveIndex())<this.sizeM && myGrid[agentX+agent.getMoveIndex()][agentY].isFree()){
            autorizedMovements.add(Movement.DOWN);
        }
        if((agentY+agent.getMoveIndex())<this.sizeN &&  myGrid[agentX][agentY+agent.getMoveIndex()].isFree()){
            autorizedMovements.add(Movement.RIGHT);
        }
        agent.setPossibleMovement(autorizedMovements);

        ArrayList<ArrayList<Slot>> nearBySlots = new ArrayList<>();
        int x0 = agentX-agent.getMoveIndex();
        int xf = agentX+agent.getMoveIndex();
        int y0 = agentY-agent.getMoveIndex();
        int yf = agentY+agent.getMoveIndex();

        if(x0<0){
            x0=0;
        }
        if(xf> this.sizeM){
            xf=this.sizeM;
        }
        if(y0<0){
            y0=0;
        }
        if(yf> this.sizeN){
            yf=this.sizeN;
        }
        for(int i = x0; i<xf;i++){
            ArrayList<Slot> slots = new ArrayList<>();

            for(int j = y0; j<yf;j++){
                slots.add(myGrid[i][j]);
            }
            nearBySlots.add( slots);
        }
        agent.setNearBySlots(nearBySlots);
    }


    @Override
    public String toString() {
        String s = new String();
        for (int i = 0; i < sizeM; i++) {
            for (int j = 0; j < sizeN; j++) {
                s += myGrid[i][j].getValue();
            }
            s += "\n";
        }
        return s;
    }
}
