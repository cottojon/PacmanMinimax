import pacsim.*;

public class PacSimMinimax implements PacAction
{

	public PacSimMinimax(int depth, String fname, int te, int gran, int max)
	{
		// optional: intialize some variables
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

		//build game tree with depth
		
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
