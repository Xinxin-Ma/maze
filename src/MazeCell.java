

import java.util.ArrayList;
import java.util.Random;

/**
 *  The <code>MazeCell</code> class stores information about each individual cell
 *  in the maze.  The boolean values <code>north</code>, <code>east</code>,
 *  <code>west</code>, and <code>south</code> store which walls are up; e.g., if
 *  <code>north</code> is <code>true</code>, then the north wall is up.
 *  
 *  Each cell also has pointer to its four <code>MazeCell</code> neighbors,
 *  <code>neighborN</code>, <code>neighborE</code>, <code>neighborS</code>, and
 *  <code>neighborW</code>.  If any of these values are null, it means this cell
 *  is on the border of the maze.  
 *  @author JuliaMa
 *
 *
 */
public class MazeCell {
    
    private boolean north, east, south, west;
    private boolean visited, examined;
    private MazeCell neighborN, neighborE, neighborS, neighborW;
    private Random generator;
	private MazeCell parent;
	private int rank;


    /** 
     *  Sets all the walls to <code>true</code> and initializes
     *  the random number generator.
     */
    public MazeCell() {
        north = true;
        east  = true;
        south = true;
        west  = true;
        generator = new Random();
        visited = false;
        examined = false;
        parent = null;
        rank = 0;
    }

    /**
     *  Sets the visited flag to <code>true</code>.
     */
    public void visit() {
        visited = true;
    }

    /**
     *  Returns whether or not this cell has been visited.
     *  @return <code>true</code> if the cell has been visited.
     */
    public boolean visited() {
        return visited;
    }

    /**
     *  Sets the examined flag to <code>true</code>.
     */
    public void examine() {
        examined = true;
    }

    /**
     *  Returns whether or not this cell has been examined.
     *  @return <code>true</code> if the cell has been examined.
     */
    public boolean examined() {
        return examined;
    }
    
    /**
     *  Sets the pointers to the neighbors of this cell.  If a pointer 
     *  is null, that means this cell is along the border of the maze.
     *  @param n  The north neighbor of this cell.
     *  @param e  The east neighbor of this cell.
     *  @param s  The south neighbor of this cell.
     *  @param w  The west neighbor of this cell.
     */
    public void setNeighbors(MazeCell n, MazeCell e, MazeCell s, MazeCell w) {
        neighborN = n;
        neighborE = e;
        neighborS = s;
        neighborW = w;
    }

    /**
     *  Sets whether or not there are walls up to the north, east, south, and 
     *  west of this cell.
     *  @param north <code>true</code> if there's a wall on the north side of the cell.
     *  @param east <code>true</code> if there's a wall on the east side of the cell.
     *  @param south <code>true</code> if there's a wall on the south side of the cell.     
     *  @param west <code>true</code> if there's a wall on the west side of the cell.
     */
    public void setWalls(boolean north, boolean east, boolean south, boolean west) {
        this.north = north;
        this.east = east;
        this.south = south;
        this.west = west;
    }
    
    /**
     *  Returns whether or not this cell has all its walls up.
     *  @return <code>true</code> if all walls are up.
     */
    public boolean hasAllWalls() {
        //TODO - fix this
    	if (!north || !south || !east || !west) {
    		return false;
    	}
        return true;
    }

    /**
     *  Returns whether or not this cell has its north wall up.
     *  @return <code>true</code> if the cell's north wall is up.
     */
    public boolean north() {
     //TODO - fix this
        return north;
    }

    /**
     *  Returns whether or not this cell has its south wall up.
     *  @return <code>true</code> if the cell's south wall is up.
     */
    public boolean south() {
        //TODO - fix this.
        return south;
    }

    /**
     *  Returns whether or not this cell has its east wall up.
     *  @return <code>true</code> if the cell's east wall is up.
     */
    public boolean east() {
        //TODO - fix this.
        return east;
    }

    /**
     *  Returns whether or not this cell has its west wall up.
     *  @return <code>true</code> if the cell's west wall is up.
     */
    public boolean west() {
     //TODO - fix this
        return west;
    }

    /**
     *  Gets the neighbors of this cell.
     *  Returns an array of those neighbors.  The length of the array
     *  is the number of neighbors this cell has.
     *  @return  An array with the neighbors contained within it.
     */
    public MazeCell[] getNeighbors() {
        //TODO - fix this.
    	ArrayList<MazeCell> neighborsList = new ArrayList<MazeCell>();
    	if (!north) {
    		neighborsList.add(neighborN);
    	}
    	if (!east) {
    		neighborsList.add(neighborE);
    	}
    	if (!south) {
    		neighborsList.add(neighborS);
    	}
    	if (!west) {
    		neighborsList.add(neighborW);
    	}
    	MazeCell[] neighborsArray = new MazeCell[neighborsList.size()];
    	for (int i = 0; i < neighborsList.size(); i++) {
    		neighborsArray[i] = neighborsList.get(i);
    	}	
        return neighborsArray;
    }

    /**
     *  Knocks down the wall between this cell and the neighbor cell.
     *  The neighbor cell must actually be a neighbor of this cell.
     *  This method is used in conjunction with <code>neighborWithWalls</code>.
     *  @param neighbor  The neighboring cell; must be one of the neighbors
     *                   set in <code>setNeighbors</code>.
     */
    public void knockDownWall(MazeCell neighbor) {
        //TODO - fix this. Remember that any wall that is knocked down
        // will require you to change values for both this and neighbor.
    	if (neighbor == neighborN) {
    		north = false;
    		neighbor.setWalls(neighbor.north(), neighbor.east(), false, neighbor.west());
    	} else if (neighbor == neighborE) {
    		east = false;
    		neighbor.setWalls(neighbor.north(), neighbor.east(), neighbor.south(), false);
    	} else if (neighbor == neighborS) {
    		south = false;
    		neighbor.setWalls(false, neighbor.east(), neighbor.south(), neighbor.west());
    	} else if (neighbor == neighborW) {
    		west = false;
    		neighbor.setWalls(neighbor.north(), false, neighbor.south(), neighbor.west());
    	}
    	
    }
    
    /**
     * Use this function whenever you want to randomly pick one of the neighbours
     * @return - random choice of one of the neighbours.
     */
    public MazeCell getRandomNeighbor() {
        //TODO - fix this
    	MazeCell[] neighbors = getNeighbors();
    	if (neighbors.length == 0) {
    		return null;
    	}
        return neighbors[generator.nextInt(neighbors.length)];
    }
    
	/**
	 * This function checks if there is a wall between the current cell and the neighbor.
	 * If there is then return this neighbor, otherwise return null.
	 * @return a random neighbor which is behind the wall with the current node.
	 * 
	 */
	public MazeCell getNeighborBehindTheWall() {
		ArrayList<MazeCell> neighbors = new ArrayList<MazeCell>();
		if (neighborN != null && neighborN.south() && north) {
			neighbors.add(neighborN);
		}
		if (neighborE != null && neighborE.west() && east) {
			neighbors.add(neighborE);
		}
		if (neighborS != null && neighborS.north() && south) {
			neighbors.add(neighborS);
		}
		if (neighborW != null && neighborW.east() && west) {
			neighbors.add(neighborW);
		}
		if (neighbors.size() == 0) {
			return null;
		}
		return neighbors.get(generator.nextInt(neighbors.size()));
	}

    /**
     *  Returns a neighbor that has all its walls intact.
     *  @return Neighbor with all its walls intact.
     */
    public MazeCell neighborWithWalls() {
       //TODO - correct this.
    	ArrayList<MazeCell> neighbors = new ArrayList<MazeCell>();
    	if (neighborN != null && neighborN.hasAllWalls()) {
    		neighbors.add(neighborN);
    	}
    	if (neighborE != null && neighborE.hasAllWalls()) {
    		neighbors.add(neighborE);
    	}
    	if (neighborS != null && neighborS.hasAllWalls()) {
    		neighbors.add(neighborS);
    	}
    	if (neighborW != null && neighborW.hasAllWalls()) {
    		neighbors.add(neighborW);
    	}
    	if (neighbors.size() == 0) {
    		return null;
    	}
        return neighbors.get(generator.nextInt(neighbors.size()));
    }
    
    /**
     * Returns the parent of this MazeCell.
     * @return parent
     */
	public MazeCell getParent() {
		return parent;
	}
	
	/**
	 * Sets the parent of this MazeCell to a given parent.
	 * @param parent
	 */
	public void setParent(MazeCell parent) {
		this.parent = parent;
	}
	
	/**
     * Returns the rank of this MazeCell.
     * @return rank
     */
	public int getRank() {
//		System.out.println(rank);
		return rank;
	}
	
	/**
	 * Sets the rank of this MazeCell to a given rank.
	 * @param rank
	 */
	public void setRank(int rank) {
		this.rank = rank;
	}  

}
