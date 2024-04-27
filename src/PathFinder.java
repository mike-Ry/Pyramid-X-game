import java.io.IOException;
/** 
 * CS 1027
 * @author chuanxi Wang
 * Assignment 3
 */



public class PathFinder {
    private Map pyramidMap;

    /**
     * constructor
     * @param fileName
     * @throws IOException
     */
    public PathFinder(String fileName) throws IOException {
        this.pyramidMap = new Map(fileName);
    }

    /**
     * This method finds a path from the entrance to all the treasure chambers
     * @return
     */
    public DLStack<Chamber> path() {
    	// create an empty stack 
        DLStack<Chamber> chamberDLStack = new DLStack<>();
        // Get the number of treasure rooms from pyramidMap
        int numTreasures = pyramidMap.getNumTreasures();
        // use getEntrance method to get chamber entrance from pyramidMap
        Chamber entrance = pyramidMap.getEntrance();
        // push the starting chameber into the stack
        chamberDLStack.push(entrance);
        // marks the chamber as Pushed and updates the chamber's color
        entrance.markPushed();

        // records for numbers of Treasures that have found
        int totalNumTreasures = 0;

        while (true) {
            // to check if the stack is not empty
            if (!chamberDLStack.isEmpty()) {
                // if chamberDLCStack is not empty, then check the top node of the stack
                Chamber peek = chamberDLStack.peek();
                if (peek.isTreasure() && totalNumTreasures == numTreasures) {
                	// if the current chamber is Treasure room and totalNumTreasures == numTreasures, then exit the while loop
                    break;
                }
                // Find the best neighboring chamber c to move to using method bestChamber from class PathFinder.
                Chamber bestChamber = this.bestChamber(peek);
                if (bestChamber != null) {
                	// if the bestChamber is not empty,push bestChamber into the stack, then mark it as pushed
                    chamberDLStack.push(bestChamber);
                    bestChamber.markPushed();
                    if (bestChamber.isTreasure()) {
                        // totalNumTreasures = totalNumTreasures +1
                        totalNumTreasures++;
                    }
                } else {
                	// otherwise pop the top chamber from the stack and mark it as popped
                    Chamber pop = chamberDLStack.pop();
                    pop.markPopped();
                }
            } else {
                // exit when the stack is not empty
                break;
            }
        }
        return chamberDLStack;
    }

    public Map getMap() {
        return pyramidMap;
    }

    /**
     * Returns true if currentChamber is dim and returns false otherwise
     * @param currentChamber
     * @return
     */
    public boolean isDim(Chamber currentChamber) {
        if (currentChamber == null || currentChamber.isSealed() || currentChamber.isLighted()) {
        	// if the currentChamber is null or Sealed or Lighted return false
            return false;
       }
        boolean flag = false; // declare flag to false
        for (int i = 0; i < 6; i++) {
            Chamber neighbour = currentChamber.getNeighbour(i);
            if (neighbour != null && neighbour.isLighted()) {
            	// if currentChameber is not empty and not sealedand not Lighted and one it's neighbour room is Lighted return true; currentChamber is dim
                // otherwise the currentChamber is not dim and return false
                flag = true;
            }
        }
        // if none of currenChamber's neighbour is light, return false 
        return flag;
    }

    /**
     * Selects the best chamber to move to from currentChamber
     * @param currentChamber
     * @return
     */
    public Chamber bestChamber(Chamber currentChamber) {
    	// Make an iteration over the current Chamber's six neighborhoods and return the one with the least index that meets the criterion.
        for (int i = 0; i < 6; i++) {
            Chamber neighbour = currentChamber.getNeighbour(i);
            if (neighbour != null && neighbour.isTreasure() && !neighbour.isMarked()) {
            	// if neighbour chamber is not marked, return that chamber
                return neighbour;
            }
        }
        // Make an iteration over the current Chamber's six neighborhoods and return the one with the least index that meets the criterion.
        for (int i = 0; i < 6; i++) {
            Chamber neighbour = currentChamber.getNeighbour(i);
            if (neighbour != null && neighbour.isLighted() && !neighbour.isMarked()) {
            	// if neighbour chamber is not marked and light, return that chamber
                return neighbour;
            }
        }
        // Make an iteration over the current Chamber's six neighborhoods and return the one with the least index that meets the criterion.
        for (int i = 0; i < 6; i++) {
            Chamber neighbour = currentChamber.getNeighbour(i);
            if (neighbour != null && this.isDim(neighbour) && !neighbour.isMarked()) {
            	// if neighbour chamber is not marked and dim, return that chamber
                return neighbour;
            }
        }
        // if there's not no marked, dim or lighted chamber, return null
        return null;
    }
}
