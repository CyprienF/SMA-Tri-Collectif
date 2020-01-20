package com.polytech.sma.model;

import java.util.ArrayList;

public class SlotAgent extends Slot {
    int objectiveX;
    int objectiveY;

    public SlotAgent(int objectiveX, int objectiveY, int x, int y) {
        super(x, y);
    }

    public int getDistance(int x, int y){
        return Math.abs(x-this.getX())+Math.abs(y-this.getY());
    }

    public ArrayList<Movement> getPossibleMovement(Grid g){
        ArrayList<Movement> possibleMovement = new ArrayList();

        if(g.isMovementPossible(this, this.getX()-1, this.getY())){
            possibleMovement.add(Movement.UP);
        }
        if(g.isMovementPossible(this, this.getX()+1, this.getY())){
            possibleMovement.add(Movement.DOWN);
        }
        if(g.isMovementPossible(this, this.getX(), this.getY()-1)){
            possibleMovement.add(Movement.LEFT);
        }
        if(g.isMovementPossible(this, this.getX(), this.getY()+1)){
            possibleMovement.add(Movement.RIGHT);
        }
        return possibleMovement;
    }

    public ArrayList<Movement> getMovementFreeSlot(Grid g, ArrayList<Movement> possibleMovement) {
        final ArrayList<Movement> freeMovment = new ArrayList();

        possibleMovement.forEach(el->{
            switch (el){
                case UP:
                    if(g.isFree(this, this.getX()-1, this.getY()))
                        freeMovment.add(Movement.UP);
                    break;
                case DOWN:
                    if(g.isFree(this, this.getX()+1, this.getY()))
                        freeMovment.add(Movement.DOWN);
                    break;
                case LEFT:
                    if(g.isFree(this, this.getX(), this.getY()-1))
                        freeMovment.add(Movement.RIGHT);
                    break;
                case RIGHT:
                    if(g.isFree(this, this.getX(), this.getY()+1))
                        freeMovment.add(Movement.LEFT);
                    break;
            }
        });

        return freeMovment;
    }


}
