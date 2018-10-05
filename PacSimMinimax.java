import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import pacsim.*;

public class PacSimMinimax implements PacAction
{
	int depth;

	public PacSimMinimax(int depth, String fname, int te, int gran, int max)
	{
		// optional: intialize some variables
		this.depth = depth;
		PacSim sim = new PacSim(fname, te, gran, max);
		sim.init(this);
	}

	public static void main(String[] args)
	{
		String fname = args[0]; // board name
		int depth = Integer.parseInt(args[1]); // depth for minimax

		int te = Integer.MIN_VALUE; //
		int gr = Integer.MIN_VALUE; //
		int ml = Integer.MIN_VALUE; //

		if (args.length == 5)
		{
			te = Integer.parseInt(args[2]);
			gr = Integer.parseInt(args[3]);
			ml = Integer.parseInt(args[4]);
		}

		new PacSimMinimax(depth, fname, te, gr, ml);

		System.out.println("\nAdversarial Search using Minimax by Jonathan Cotto:");
		System.out.println("\n    Game Board    : " + fname);
		System.out.println("     Search depth : " + depth + "\n");

		if (te > 0)
		{
			System.out.println("    Preliminiary runs : " + te + "\n  Granularity      : " + gr
					+ "\n  Max move limit   : " + ml + "\n\nPreliminary run results :\n");
		}
	}

	@Override
	public PacFace action(Object state)
	{
		PacCell[][] grid = (PacCell[][]) state;
		PacFace newFace = null;
		
		//get pacman location
		PacmanCell currentPacmanCell = PacUtils.findPacman(grid);
		//get pacman location
		Point currentPacmanPoint = currentPacmanCell.getLoc();
		//get ghost locations
		List<Point> ghostPoints = PacUtils.findGhosts(grid);
		//create root node of game tree
		Node rootNode = new Node(currentPacmanPoint, ghostPoints.get(0), ghostPoints.get(1));
		//build game tree with proper depth + 1, or just depth
		Node.buildTree(this.depth, rootNode, grid);
	
		//alpha beta the tree
		
		//make decision on the next move based on what alpha beta returns

		return newFace;
	}

	@Override
	public void init()
	{
		// TODO Auto-generated method stub

	}
	
	

}
