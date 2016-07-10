

/**
 * Make disjoint sets and implement find-union.
 * @author JuliaMa
 *
 */
public class DisjointSet {
    
    //TODO - write all the methods of this class
    
    /**
     * make a set out of the cells in the maze
     * @param maze
     */
    public void makeSet(MazeCell[][] maze) {
        for (int i = 0; i < maze.length; i++) {
        	for (int j = 0; j < maze[0].length; j++) {
        		MazeCell curr = maze[i][j];
        		curr.setParent(curr);
        	}
        }
    }

    /**
     * Create the union of the sets that contain cell1 and cell2.
     * If the two cells are in the same set, nothing changes,
     * else create the new set as a union. 
     * Please see the union-find algorithm.
     * @param cell1
     * @param cell2
     */
    public void union(MazeCell cell1, MazeCell cell2){
    	MazeCell cell1Set = find(cell1);
        MazeCell cell2Set = find(cell2);
        //in the same set
        if (cell1Set == cell2Set) {
        	return;
        }
        if (cell1Set.getRank() > cell2Set.getRank()) {
        	cell2Set.setParent(cell1Set);
        } else if (cell1Set.getRank() < cell2Set.getRank()) {
        	cell1Set.setParent(cell2Set);
        } else {
        	cell2Set.setParent(cell1Set);
        	cell1Set.setRank(cell1Set.getRank() + 1);
        }     
    }

    /**
     * Find the set that the cell is a part of.
     * While finding the set, do the path compression as well.
     * @param cell
     * @return
     */
    public MazeCell find(MazeCell cell){
    	if (cell != cell.getParent()){
    		cell.setParent(find(cell.getParent()));
    	}
    	return cell.getParent();
    }
}
