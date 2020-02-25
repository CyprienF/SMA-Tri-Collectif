package com.polytech.sma.model;

/**
 *
 * @author Stefano
 */
public class Slot {
    private int value;
    private int x,y;
    private Agent agent;
    private Element element;

    public int getValue() {
        if(agent != null){
            return 64;
        }
        if(element != null){
            if(element.getLetter().equals("A")){
                return 2;
            }else{
                return 8;
            }
        }
        return 0;
    }


    public int getX() {
        return x;
    }


    public int getY() {
        return y;
    }


    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        if(agent == null){
            this.value = 0;
            this.agent = null;
            return;
        }
        this.value = 64;
        this.agent = agent;
    }

    public Element getElement() {
        return element;
    }

    public  boolean isFree(){
        return (this.agent == null && this.element==null);
    }
    public void setElements(Element element) {

        if(element == null){
            this.value = 0;
            this.element = null;
            return;
        }

        if(element.getLetter().equals("A")){
            this.value= 2;
        }else{
            this.value = 8;
        }
        this.element = element;
    }

    public Slot(int x, int y) {
        this.value = 0;
        this.x = x;
        this.y = y;
    }
}