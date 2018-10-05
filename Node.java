import java.awt.Point;
import java.util.*;

import pacsim.PacCell;
import pacsim.PacFace;
import pacsim.PacUtils;
import pacsim.PacmanCell;
import pacsim.WallCell;

public class Node 
{
	private Node parent = null;
	private List<Node> children = new ArrayList<Node>();
	private PacFace pacmanPacFace = null; // pacface for pacman to arrive at pacmanpoint
	private Point pacmanPoint;
	private Point ghost1Point;
	private Point ghost2Point;

	// constructor with pacface represents non root nodes
	// root node won't know its pacface since root node is current location
	public Node(Point pacmanPoint, Point ghost1Point, Point ghost2Point, Node parent, PacFace pacface) 
	{
		this.pacmanPoint = pacmanPoint;
		this.ghost1Point = ghost1Point;
		this.ghost2Point = ghost2Point;
		this.pacmanPacFace = pacface;
		this.parent = parent;
	}

	// constructor for root node
	// has no pacface
	//has no parent node
	public Node(Point pacmanPoint, Point ghost1Point, Point ghost2Point) 
	{
		this.pacmanPoint = pacmanPoint;
		this.ghost1Point = ghost1Point;
		this.ghost2Point = ghost2Point;
	}

	public List<Node> getChildren() 
	{
		return children;
	}

	public void setChildren(List<Node> children) 
	{
		this.children = children;
	}

	public boolean isRoot() 
	{
		return (this.parent == null);
	}

	public boolean isLeaf() 
	{
		return this.children.size() == 0;
	}

	public void removeParent() 
	{
		this.parent = null;
	}

	public Node getParent() 
	{
		return parent;
	}

	public void setParent(Node parent) 
	{
		this.parent = parent;
	}

	public Point getPacmanPoint() 
	{
		return pacmanPoint;
	}

	public void setPacmanPoint(Point pacmanPoint) 
	{
		this.pacmanPoint = pacmanPoint;
	}

	public Point getGhost1Point() 
	{
		return ghost1Point;
	}

	public void setGhost1Point(Point ghost1Point) 
	{
		this.ghost1Point = ghost1Point;
	}

	public Point getGhost2Point() 
	{
		return ghost2Point;
	}

	public void setGhost2Point(Point ghost2Point) 
	{
		this.ghost2Point = ghost2Point;
	}

	// compute the child nodes of a passed in node
	// we might be able to get rid of point paramenters since Node parent has them
	public static List<Node> computeChildNodes(PacCell[][] grid, Node parent) 
	{
		// list of child nodes
		List<Node> children = new ArrayList<Node>();
		// Pacface enum
		PacFace[] enumConstants = PacFace.values();

		// find valid pacman, ghost1, and ghost2 moves and add them as a node to
		// children
		// outer most for loop represents pacman moves
		// 2nd for loop represents ghost1
		// inner most for loop represents ghost2
		for (int i = 0; i < enumConstants.length; i++) 
		{
			// get cell next to pacman
			PacCell nextPacmanCell = PacUtils.neighbor(enumConstants[i], parent.getPacmanPoint(), grid);
			// determine if paccell if a valid cell for pacman to move to
			// pacman can't move to a wall cell, or a pacmancell since it is already there
			if (nextPacmanCell instanceof WallCell || nextPacmanCell instanceof PacmanCell) 
			{
				continue; // skip, not a valid move
			}

			for (int j = 0; j < enumConstants.length; j++) 
			{
				// get cell next to ghost1
				PacCell nextGhost1Cell = PacUtils.neighbor(enumConstants[j], parent.getGhost1Point(), grid);
				// determine if paccell is a valid cell for ghost1 to move to
				// ghost can't move to wall cell
				if (nextGhost1Cell instanceof WallCell) 
				{
					continue; // skip, not a valid move for ghost1
				}

				for (int k = 0; k < enumConstants.length; j++) 
				{
					// get cell next to ghost1
					PacCell nextGhost2Cell = PacUtils.neighbor(enumConstants[k], parent.getGhost2Point(), grid);
					// determine if paccell is a valid cell for ghost2 to move to
					// ghost can't move to wall cell
					if (nextGhost2Cell instanceof WallCell) 
					{
						continue; // skip, not a valid move for ghost1
					}

					// create node for a valid move
					Node currentChildNode = new Node(nextPacmanCell.getLoc(), nextGhost1Cell.getLoc(),
							nextGhost2Cell.getLoc(), parent, enumConstants[i]);

					// add node to children list
					children.add(currentChildNode);

				} // inner most for loop
			} // 2nd for loop
		} // outer most for loop

		return children;

	}
	
	
	public static void buildTree(int depth, Node currentNode, PacCell[][] grid)
	{
		//stop building tree
		if(depth == 0)
			return;
		
		//get children of node
		currentNode.setChildren(computeChildNodes(grid, currentNode));
		
		//get the children node children using recursion
		for(int i = 0; i < currentNode.children.size(); i++)
		{
			//pass in depth - 1, child node, and grid
			buildTree(depth - 1, currentNode.children.get(i), grid);
		}
		
		return;
	}

}
