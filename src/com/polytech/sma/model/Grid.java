package com.polytech.sma.model;

import java.util.ArrayList;

/**
 *
 * @author Stefano
 */
public class Grid {

    private Slot myGrid[][];
    private int size;
    private int numberFreeSlots;
    
    public int getSize() {
        return size;
    }

    public int getSlot(int i, int j) {
        return myGrid[i][j].getValue();
    }

    public Grid(int size, int numberFreeSlots) {
        this.size = size;
        this.numberFreeSlots = numberFreeSlots;
        myGrid = new Slot[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                myGrid[i][j] = new Slot(i, j);
            }
        }
        generateTaquin();
    }

    /**
     * This function will check if the movement is possibile in the grid
     * @param s Moving Agent
     * @param x Desiered x cordinate
     * @param y Desiered y cordinate
     * @return
     */
    public boolean isMovementPossible(Slot s, int x, int y){
        boolean isPossible = true;
        if(x<0 || y<0 || x>=this.size || y >= this.size) {
            isPossible = false;
        }
        return isPossible;
    }

    /**
     * This function will check if there is not an agent on the wanted slot
     * @param s Moving Agent
     * @param x Desiered x cordinate
     * @param y Desiered y cordinate
     * @return
     */
    public boolean isFree(Slot s, int x, int y){
        boolean isPossible = true;
        if(this.getSlot(x,y) != 0) {
            isPossible = false;
        }
        return isPossible;
    }
    // public void addRandomValue() {
    //     ArrayList<Slot> libre = new ArrayList();
    //     for (int i = 0; i < size; i++) {
    //         for (int j = 0; j < size; j++) {
    //             if (myGrid[i][j].getValue() == 0) {
    //                 libre.add(myGrid[i][j]);
    //             }
    //         }
    //     }

    //     int alea = (int) (Math.random() * libre.size());

    //     if (Math.random() > 0.25) {
    //         myGrid[libre.get(alea).getX()][libre.get(alea).getY()].setValue(2);
    //     } else {
    //         myGrid[libre.get(alea).getX()][libre.get(alea).getY()].setValue(4);
    //     }

    // }
    public void generateTaquin() {
        ArrayList<Slot> freeSlots = new ArrayList();
        int numberOfEntities = this.size*this.size - this.numberFreeSlots;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (myGrid[i][j].getValue() == 0) {
                    freeSlots.add(myGrid[i][j]);
                }
            }
        }

        for(int i = 1; i<= numberOfEntities ; i++){
            int alea = (int) (Math.random() * freeSlots.size());
            myGrid[freeSlots.get(alea).getX()][freeSlots.get(alea).getY()].setValue(i);
            freeSlots.remove(alea);
        }
    }


    public void reset(int size, int numberFreeSlots) {
        this.size = size;
        this.numberFreeSlots = numberFreeSlots;
        myGrid = new Slot[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                myGrid[i][j] = new Slot(i, j);
            }
        }
        generateTaquin();
    }

    public int[] getColumnValues(int colonne) {
        int t[] = new int[myGrid.length];
        int cpt = 0;
        for (int i = 0; i < myGrid.length; i++) {
            if (myGrid[i][colonne].getValue() != 0) {
                t[cpt] = myGrid[i][colonne].getValue();
                cpt++;
            }
        }
        for (; cpt < myGrid.length; cpt++) {
            t[cpt] = 0;
        }
        return t;
    }

    public void pushUp() {
        int i, j, cpt;
        for (j = 0; j < myGrid.length; j++) {
            cpt = 0;//a modifier
            i = 0;//a modifier
            int t[] = getColumnValues(j);
            while (cpt < t.length && t[cpt] != 0) {
                if (cpt + 1 < t.length && t[cpt] == t[cpt + 1]) {
                    myGrid[i][j].setValue(t[cpt] * 2);
                    i++;
                    cpt += 2;
                } else {
                    myGrid[i][j].setValue(t[cpt]);
                    i++;
                    cpt++;
                }
            }
            while (i < t.length) {
                myGrid[i][j].setValue(0);
                i++;
            }
        }
    }

    public void pushDown() {
        boolean modif = false;
        int i, j, cpt;
        for (j = 0; j < myGrid.length; j++) {
            cpt = 0;//a modifier
            i = 0;//a modifier
            int t[] = getColumnValues(j);
            int tmp[] = new int[myGrid.length];
            while (cpt < t.length && t[cpt] != 0) {
                if (cpt + 1 < t.length && t[cpt] == t[cpt + 1]) {
                    myGrid[i][j].setValue(t[cpt] * 2);
                    i++;
                    cpt += 2;
                } else {
                    myGrid[i][j].setValue(t[cpt]);
                    i++;
                    cpt++;
                }
            }
            while (i < t.length) {
                myGrid[i][j].setValue(0);
                i++;
                modif = true;
            }
            if (modif == true) {
                cpt = 0;

                while (cpt < myGrid.length && myGrid[cpt][j].getValue() != 0) {
                    cpt++;
                }
                i = cpt;
                while (i < myGrid.length) {
                    i++;
                }
                cpt--;
                i--;
                for (; cpt >= 0; cpt--) {
                    try {
                        myGrid[i][j].setValue(myGrid[cpt][j].getValue());
                    } catch (Exception e) {

                    }
                    i--;
                }
                for (; i >= 0; i--) {
                    try {
                        myGrid[i][j].setValue(0);
                    } catch (Exception e) {

                    }
                }

            }
        }
    }

    public int[] getRow(int row) {
        int t[] = new int[myGrid.length];
        int cpt = 0;
        for (int j = 0; j < myGrid.length; j++) {
            if (myGrid[row][j].getValue() != 0) {
                t[cpt] = myGrid[row][j].getValue();
                cpt++;
            }
        }
        for (; cpt < myGrid.length; cpt++) {
            t[cpt] = 0;
        }
        return t;
    }

    public void pushLeft() {
        int i, j, cpt;
        for (i = 0; i < myGrid.length; i++) {
            cpt = 0;//a modifier
            j = 0;//a modifier
            int t[] = getRow(i);
            while (cpt < t.length && t[cpt] != 0) {
                if (cpt + 1 < t.length && t[cpt] == t[cpt + 1]) {
                    myGrid[i][j].setValue(t[cpt] * 2);
                    j++;
                    cpt += 2;
                } else {
                    myGrid[i][j].setValue(t[cpt]);
                    j++;
                    cpt++;
                }
            }
            while (j < t.length) {
                myGrid[i][j].setValue(0);
                j++;
            }
        }
    }

    public void pushRight() {
        boolean modif = false;
        int i, j, cpt;
        for (i = 0; i < myGrid.length; i++) {
            cpt = 0;//a modifier
            j = 0;//a modifier
            int t[] = getRow(i);
            int tmp[] = new int[myGrid.length];
            while (cpt < t.length && t[cpt] != 0) {
                if (cpt + 1 < t.length && t[cpt] == t[cpt + 1]) {
                    myGrid[i][j].setValue(t[cpt] * 2);
                    j++;
                    cpt += 2;
                } else {
                    myGrid[i][j].setValue(t[cpt]);
                    j++;
                    cpt++;
                }
            }
            while (j < t.length) {
                myGrid[i][j].setValue(0);
                j++;
                modif = true;
            }
            if (modif == true) {
                cpt = 0;

                while (cpt < myGrid.length && myGrid[i][cpt].getValue() != 0) {
                    cpt++;
                }
                j = cpt;
                while (j < myGrid.length) {
                    j++;
                }
                cpt--;
                j--;
                for (; cpt >= 0; cpt--) {
                    try {
                        myGrid[i][j].setValue(myGrid[i][cpt].getValue());
                    } catch (Exception e) {

                    }
                    j--;
                }
                for (; j >= 0; j--) {
                    try {
                        myGrid[i][j].setValue(0);
                    } catch (Exception e) {

                    }
                }

            }
        }
    }

    public boolean gagne2048() {
        for (int i = 0; i < myGrid.length; i++) {
            for (int j = 0; j < myGrid.length; j++) {
                if (myGrid[i][j].getValue() == 2048) {
                    return true;
                }
            }
        }
        return false;
    }
    // public void controle0(){
    //     boolean zero=false;
    //     for (int i = 0; i < (myGrid.length ); i++) {
    //         for (int j = 0; j < (myGrid.length); j++) {
    //             if (myGrid[i][j].getValue()==0){
    //                 zero= true;
    //                 break;
    //             }
    //         }
    //     }

    //     if(zero){
    //         this.addRandomValue();
    //     }
    // }

    // public boolean pasdecoup() {
    //     for (int i = 0; i < (myGrid.length ); i++) {
    //         for (int j = 0; j < (myGrid.length); j++) {
    //             if (myGrid[i][j].getValue()==0){
    //                 return false;
    //             }
    //         }
    //     }
    //     for (int i = 0; i < (myGrid.length - 1); i++) {
    //         for (int j = 0; j < (myGrid.length - 1); j++) {
    //             if (myGrid[i][j].getValue() == myGrid[i + 1][j].getValue()
    //                     || myGrid[i][j].getValue() == myGrid[i][j + 1].getValue()
    //                     || myGrid[i+1][j].getValue() == myGrid[i+1][j + 1].getValue()
    //                     ||myGrid[i][j+1].getValue() == myGrid[i+1][j + 1].getValue()) {
    //                 return false;
    //             }
    //         }
    //     }
    //     return true;
    // }

    @Override
    public String toString() {
        String s = new String();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                s += myGrid[i][j].getValue();
            }
            s += "\n";
        }
        return s;
    }
}
