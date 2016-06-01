package logicalmap;


import java.io.Serializable;
import java.util.*;

/*
 * The LogicalMap is the Model in the Model View Controller Design
 * it consists of a 2d array which is populated with block objects,
 * a separate reference to the Farrel
 * a queue of changes to the map to be polled by the controller
 * an x length
 * and a y length 
 */

public class LogicalMap implements Serializable{
	
	/**
	 * Length of X in the map
	 */
	private int xlen;
	/**
	 * Length of Y in the map
	 */
	private int ylen;
	
	private Block Objective;
	/**
	 * All Blocks that are not default (Blank)
	 */
	private Block[][] blocklist;
	
	private int[][] originalState;
	/**
	 * Changes to be sent
	 */
	private Queue<CoordNode> changes;
	
	/**
	 * Farrel on the map
	 */
	private Farrel farrel;
	
	private boolean objectiveReached;
	
	/**
	 * Includes the basic turn, move, and detect blocked path commands
	 */
	
	
	/**
	 * Includes Everything that the BasicPhysicalMode has plus movable items and the pushBox command
	 */
	
	
	/**
	 * Includes all the elements of the Basic Virtual Mode plus responsive blocks (Jump pads, enemies, checker jump command
	 * and the Danger Condition)
	 */
	
	
	private ArrayList<Block> Spikes;
	private ArrayList<Block> Chompers;
	
	/**
	 * Default constructor that takes in size map of x and y
	 * 
	 * @param xlength x length of map
	 * @param ylength y length of map
	 */
	public LogicalMap()
	{
		this.xlen = 12;
		this.ylen = 12;
		this.Spikes = new ArrayList<Block>();
		this.Chompers = new ArrayList<Block>();
		changes = new LinkedList<CoordNode>(); 
		this.farrel = new Farrel(0, 0, 1);
		blocklist = new Block[this.xlen][this.ylen];
		this.Objective = new Block(0,0, 601, false);
		this.objectiveReached = false;
		
		this.originalState = null;
		
	}
	
	public void passMap(int map[][])
	{
		this.originalState = map;
	}
	
	public int[][] getMapState()
	{
		return this.originalState;
	}
	
	public void resetMap() throws MapNotLoadedException
	{
		if(this.originalState == null)
		{
			throw new MapNotLoadedException();
		}
		this.changes.clear();
		if(this.Spikes.size() != 0)
		{
			Spikes.clear();
		}
		if(this.Chompers.size() != 0)
		{
			Chompers.clear();
		}
		this.farrel.Heal();
		boolean farrelSet = false;
		for(int i = 0; i < this.xlen; i++)
		{
			for(int j = 0; j < this.ylen; j++)
			{
				this.blocklist[i][j] = null;
			}
		}
		for(int i = 0; i < this.xlen; i++)
		{
			for(int j = 0; j < this.ylen; j++)
			{
				switch(this.originalState[i][j])
				{
					case 101:
						if(!farrelSet)
						{
							this.addFarrel(i, j);
							this.farrel.setDirection(1);
							farrelSet = true;
						}
						break;
						
					case 102:
						if(!farrelSet)
						{
							this.addFarrel(i, j);
							this.farrel.setDirection(2);
							farrelSet = true;
						}
						break;
					case 103:
						if(!farrelSet)
						{
							this.addFarrel(i, j);
							this.farrel.setDirection(3);
							farrelSet = true;
						}
						break;
						
					case 104:
						if(!farrelSet)
						{
							this.addFarrel(i, j);
							this.farrel.setDirection(4);
							farrelSet = true;
						}
						break;
						
					case 105:
						if(!farrelSet)
						{
							this.addFarrel(i, j);
							this.farrel.setDirection(5);
							farrelSet = true;
						}
						break;
						
					case 106:
						if(!farrelSet)
						{
							this.addFarrel(i, j);
							this.farrel.setDirection(6);
							farrelSet = true;
						}
						break;
					case 107:
						if(!farrelSet)
						{
							this.addFarrel(i, j);
							this.farrel.setDirection(7);
							farrelSet = true;
						}
						break;
						
					case 108:
						if(!farrelSet)
						{
							this.addFarrel(i, j);
							this.farrel.setDirection(8);
							farrelSet = true;
						}
						break;
					case 201:
						this.addBlock(i, j, 201, false);
						break;
						
					case 202:
						this.addBlock(i, j, 202, false);
						break;
						
					case 203:
						this.addBlock(i, j, 203, false);
						break;
						
					case 204:
						this.addBlock(i, j, 204, false);
						break;
					
					case 205:
						this.addBlock(i, j, 205, false);
						break;
						
					case 206:
						this.addBlock(i, j, 206, false);
						break;
						
					case 207:
						this.addBlock(i, j, 207, false);
						break;
						
					case 208:
						this.addBlock(i, j, 208, false);
						break;
						
					case 209:
						this.addBlock(i, j, 209, false);
						break;
						
					case 210:
						this.addBlock(i, j, 210, false);
						break;
						
					case 211:
						this.addBlock(i, j, 211, false);
						break;
						
					case 301:
						this.addBlock(i, j, 301, true);
						break;
						
					case 302:
						this.addBlock(i, j, 302, true);
						break;
						
					case 303:
						this.addBlock(i, j, 303, true);
						break;
						
					case 304:
						this.addBlock(i, j, 304, true);
						break;
						
					case 601:
						this.blocklist[i][j] = this.Objective;
						setObjective(i, j);
						break;
						
					case 701:
						this.AddSpike(i, j);
						break;
						
					case 702:
						this.AddChomper(i, j);
						break;
				}
			}
		}
		SetMapVisible();
	}
	
	public boolean isPushableBlockInFront()
	{
		int fnx = this.farrel.getNextX();
		int fny = this.farrel.getNextY();
		if(this.testBounds(fnx, fny) && this.blocklist[fnx][fny] != null)
		{
			if(this.blocklist[fnx][fny].IsMovable())
			{
				return true;
			}
		}
		return false;
	}
	
	public boolean isPushableBlockAround()
	{
		boolean pblock = false;
		int fnx, fny = 0;
		for(int i = 1; i < 9; i ++)
		{
			fnx = this.farrel.getNextX(i);
			fny = this.farrel.getNextY(i);
			if(this.testBounds(fnx, fny) && this.blocklist[fnx][fny] != null)
			{
				if(this.blocklist[fnx][fny].IsMovable())
				{
					pblock = true;
				}
			}
		}
		return pblock;
	}
	
	public boolean isDanger()
	{
		boolean danger = false;
		int fnx, fny = 0;
		for(int i = 1; i < 9; i ++)
		{
			fnx = this.farrel.getNextX(i);
			fny = this.farrel.getNextY(i);
			if(this.testBounds(fnx, fny) && this.blocklist[fnx][fny] != null)
			{
				if(this.blocklist[fnx][fny].getType() == 701 || this.blocklist[fnx][fny].getType() == 702)
				{
					danger = true;
				}
			}
		}
		return danger;
	}
	
	public boolean isEnemyInFront()
	{
		int fnx = this.farrel.getNextX();
		int fny = this.farrel.getNextY();
		if(this.testBounds(fnx, fny) && this.blocklist[fnx][fny] != null)
		{
			if(this.blocklist[fnx][fny].getType() == 701 || this.blocklist[fnx][fny].getType() == 702)
			{
				return true;
			}
		}
		return false;
	}
	
	public boolean isChomperInFront()
	{
		int fnx = this.farrel.getNextX();
		int fny = this.farrel.getNextY();
		if(this.testBounds(fnx, fny) && this.blocklist[fnx][fny] != null)
		{
			if(this.blocklist[fnx][fny].getType() == 702)
			{
				return true;
			}
		}
		return false;
	}
	
	public boolean isSpikeInFront()
	{
		int fnx = this.farrel.getNextX();
		int fny = this.farrel.getNextY();
		if(this.testBounds(fnx, fny) && this.blocklist[fnx][fny] != null)
		{
			if(this.blocklist[fnx][fny].getType() == 701)
			{
				return true;
			}
		}
		return false;
	}
	
	
	
	
	
	public void setObjective(int x, int y)
	{
		this.Objective.setLocation(x, y);
		this.objectiveReached = false;
	}
	
	public boolean reachedObjective()
	{
		return this.objectiveReached;
	}
	
	/**
	 * Helper Method
	 */
	private void AddChomper(int x, int y)
	{
		Block chomper = new Block(x, y, 702, false);
		this.blocklist[x][y] = chomper;
		this.Chompers.add(chomper);
	}
	
	private void AddSpike(int x, int y)
	{
		Block spike = new Block(x, y, 701, false);
		this.blocklist[x][y] = spike;
		this.Spikes.add(spike);
	}
	/*
	 * This Method Adds a boundary of walls to the current size map
	 */
	
	private boolean addBlock(int x, int y, int type, boolean mov)
	{
		if(this.testBounds(x, y))
		{
			Block temp = new Block(x, y, type, mov);
			this.blocklist[x][y] = temp;
			return true;
		}
		else
		{
			return false;
		}
	}
	
	private void removeEnemy(int x, int y)
	{
		Block temp = this.blocklist[x][y];
		if(temp != null)
		{
			this.blocklist[x][y] = null;
			this.changes.offer(new CoordNode(x, y, 0));
			if(temp.getType() == 701)
			{
				for(int i = 0; i < this.Spikes.size(); i++)
				{
					Block sp = Spikes.get(i);
					if(sp.getX() == x && sp.getY() == y)
					{
						Spikes.remove(i);
					}
				}
			}
			else
			{
				for(int i = 0; i < this.Chompers.size(); i++)
				{
					Block ch = Chompers.get(i);
					if(ch.getX() == x && ch.getY() == y)
					{
						Chompers.remove(i);
					}
				}
			}
		}
	}
	
	public void checkerJump()
	{
		int fx = this.farrel.getX();
		int fy = this.farrel.getY();
		int fnx = this.farrel.getNextX();
		int fny = this.farrel.getNextY();
		if(testBounds(fnx, fny))
		{
			Block temp = this.blocklist[fnx][fny];
			if(temp != null)
			{
				if(temp.getType() == 701 || temp.getType() == 702)
				{
					int enx = temp.getNextX(farrel.getDirection());
					int eny = temp.getNextY(farrel.getDirection());
					if(testBounds(enx, eny) && this.blocklist[enx][eny] == null)
					{
						this.blocklist[fx][fy] = null;
						this.changes.offer(new CoordNode(fx, fy, 0));
						this.blocklist[enx][eny] = this.farrel;
						this.changes.offer(new CoordNode(enx, eny, this.farrel.getType()));
						removeEnemy(fnx, fny);
						this.farrel.setLocation(enx, eny);
					}
				}
			}
		}
	}
	
	/*
	 * This loads all of the current blocks present in the map into the changes queue
	 */
	
	private void MoveEnemies()
	{
		int fx = this.farrel.getX();
		int fy = this.farrel.getY();
		if(this.Spikes.size() != 0)
		{
			for(int i = 0; i < this.Spikes.size(); i++)
			{
				Block spike = this.Spikes.get(i);
				MoveSpike(fx, fy, spike);
			}
		}
		if(this.Chompers.size() != 0)
		{
			for(int i = 0; i < this.Chompers.size(); i++)
			{
				Block chomper = this.Chompers.get(i);
				MoveChomper(fx, fy, chomper);
			}
		}
	}
	
	private void MoveSpike(int fx, int fy, Block spike)
	{
		int sx = spike.getX();
		int sy = spike.getY();
		int dir = 0;
		int nsx, nsy;
		if(fx >= sx && fy < sy)
		{
			dir = 2;
		}
		else if(fx > sx && fy >= sy)
		{
			dir = 4;
		}
		else if(fx <= sx && fy > sy)
		{
			dir = 6;
		}
		else
		{
			dir = 8;
		}
		
		nsx = spike.getNextX(dir);
		nsy = spike.getNextY(dir);
		if(nsx == fx && nsy == fy)
		{
			this.changes.offer(new CoordNode(fx, fy, 109));
			this.farrel.Destroy();
		}
		else
		{
			if(!testBounds(nsx, nsy) || blocklist[nsx][nsy] != null)
			{
				if(dir == 2)
				{
					dir = 8;
				}
				else if(dir == 4)
				{
					dir = 2;
				}
				else if(dir == 6)
				{
					dir = 4;
				}
				else
				{
					dir = 6;
				}
				nsx = spike.getNextX(dir);
				nsy = spike.getNextY(dir);
				if(!testBounds(nsx, nsy) || blocklist[nsx][nsy] != null)
				{
					dir = -1;
				}
			}
			if(dir != -1)
			{
				if(nsx == fx && nsy == fy)
				{
					this.changes.offer(new CoordNode(fx, fy, 109));
					this.farrel.Destroy();
				}
				else
				{
					this.blocklist[nsx][nsy] = spike;
					this.blocklist[sx][sy] = null;
					this.changes.offer(new CoordNode(sx, sy, 0));
					this.changes.offer(new CoordNode(nsx, nsy, 701));
				}
				spike.setLocation(nsx, nsy);
			}
		}
		
	}
	
	private void MoveChomper(int fx, int fy, Block chomper)
	{
		int cx = chomper.getX();
		int cy = chomper.getY();
		
		int dir = 0;
		if(fx <= cx && fy < cy)
		{
			dir = 1;
		}
		else if(fx > cx && fy <= cy)
		{
			dir = 3;
		}
		else if(fx >= cx && fy > cy)
		{
			dir = 5;
		}
		else
		{
			dir = 7;
		}
		
		int ncx = chomper.getNextX(dir);
		int ncy = chomper.getNextY(dir);
		
		if(ncx == fx && ncy == fy)
		{
			this.farrel.Destroy();
			this.changes.offer(new CoordNode(fx, fy, 109));
		}
		else
		{
		
			if(blocklist[ncx][ncy] != null)
			{
				if(dir == 1)
				{
					dir = 7;
				}
				else if(dir == 3)
				{
					dir = 1;
				}
				else if(dir == 5)
				{
					dir = 3;
				}
				else
				{
					dir = 5;
				}
				ncx = chomper.getNextX(dir);
				ncy = chomper.getNextY(dir); 
				if(!testBounds(ncx, ncy))
				{
					dir = -1;
				}
			}
			if(dir != -1)
			{
				if(ncx == fx && ncy == fy)
				{
					this.farrel.Destroy();
					this.changes.offer(new CoordNode(fx, fy, 109));
				}
				else
				{
					this.blocklist[ncx][ncy] = chomper;
					this.blocklist[cx][cy] = null;
					this.changes.offer(new CoordNode(cx, cy, 0));
					this.changes.offer(new CoordNode(ncx, ncy, 702));
					chomper.setLocation(ncx, ncy);
				}
			}
		}
	}
	private void SetMapVisible()
	{
		int type, x, y;
		CoordNode tempchange;
		
		for(int i = 0; i < this.xlen; i ++)
		{
			for(int j = 0; j < this.ylen; j++)
			{
				if(this.blocklist[i][j] != null)
				{
					type = this.blocklist[i][j].getType();
					x = this.blocklist[i][j].getX();
					y = this.blocklist[i][j].getY();
					tempchange = new CoordNode(x, y, type);
					this.changes.offer(tempchange);
				}
			}
		}
	}
	
	/*
	 * This is the method that the controller acesses to poll the map for changes
	 */
	
	public CoordNode getNextChange()
	{
		try
		{
			return this.changes.remove();
		}
		catch(NoSuchElementException e)
		{
			return null;
		}
	}
	
		
	/**
	 * Places Farrel in the designed location
	 * 
	 * @param x x cord
	 * @param y y cord
	 * @return if worked (valid)
	 */
	private void addFarrel(int x, int y)
	{
		this.blocklist[x][y] = this.farrel;
		this.farrel.setLocation(x, y);
	}
	
	
	
	/*
	 * Moves Farrel forward on the logical map if its path isnt blocked by another object and if its next move is within the boundaries of the map
	 */
	public boolean moveFarrelForward()
	{
		
		int fX = this.farrel.getX();
		int fY = this.farrel.getY();
		int nx = this.farrel.getNextX();
		int ny = this.farrel.getNextY();
		CoordNode emptyspace = new CoordNode(fX, fY, 0);
		CoordNode farl;
		
		
		
		if(!this.testBounds(nx, ny))
		{
			MoveEnemies();
			return false;
		}
		
		if(this.farrel.isDestroyed())
		{
			return false;
		}
		
		
		
		if(this.blocklist[nx][ny] == null || this.blocklist[nx][ny].getType() == 601)
		{
			
			if(this.Objective.getX() == nx && this.Objective.getY() == ny)
			{
				this.objectiveReached = true;
			}
			this.blocklist[nx][ny] = this.farrel;
			this.farrel.setLocation(nx, ny);
			
			this.blocklist[fX][fY] = null;
			farl = new CoordNode(nx, ny, this.farrel.getType());
			this.changes.offer(emptyspace);
			this.changes.offer(farl);
			MoveEnemies();
			return true;
			
		}
		else
		{
			System.out.println("Else Move");
			MoveEnemies();
		}
		
		return false;
		
	}
	
	/*
	 * If there is a movable block directly in front of the finch and the space behind the block is within bounds 
	 * and unoccupied pushes the block forward one space.
	 */
	
	public boolean pushBox()
	{
		
		int nx = this.farrel.getNextX();
		int ny = this.farrel.getNextY();
		int dir = this.farrel.getDirection();
		Block b;
		int nnx, nny;
		CoordNode emptyspace = new CoordNode(nx, ny, 0);
		CoordNode blck;
		
		System.out.println("Push is called");
		
		if(this.farrel.isDestroyed())
		{
			return false;
		}
		
		MoveEnemies();
		
		if((b = this.blocklist[nx][ny]) != null)
		{
			if(!b.IsMovable())
			{
				System.out.println("B becomes immovable");
				return false;
			}
			nnx = b.getNextX(dir);
			nny = b.getNextY(dir);
			System.out.println("We are in here");
			if(testBounds(nnx, nny) && this.blocklist[nnx][nny] == null)
			{
				this.blocklist[nnx][nny] = b;
				this.blocklist[nx][ny] = null;
				b.setLocation(nnx, nny);
				blck = new CoordNode(nnx, nny, b.getType());
				this.changes.offer(emptyspace);
				this.changes.offer(blck);
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
		
		return true;
	}
	
	/*
	 * Indicates whether the space with the given X and Y coordinates is within the bounds of the map
	 */
	
	private boolean testBounds(int x, int y)
	{
		if(x < 0 || x >= this.xlen || y < 0 || y >= this.ylen)
		{
			return false;
		}
		return true;
	}
	
	/*
	 * Indicates whether the space directly in front of the Farrel is empty and within bounds
	 */
	
	public boolean IsFarrelPathFree()
	{
		int nx = this.farrel.getNextX();
		int ny = this.farrel.getNextY();
		
		if(!testBounds(nx, ny))
		{
			return false;
		}
		
		if(this.blocklist[nx][ny] == null)
		{
			return true;
		}
		
		return false;
	}
	
	/*
	 * Turns the Farrel Clockwise 45 degrees
	 */
	
	public void SmallTurnClockwise()
	{
		CoordNode temp;
		if(!this.farrel.isDestroyed())
		{
			this.farrel.SmallTurnClockwise();
			temp = new CoordNode(this.farrel.getX(), this.farrel.getY(), this.farrel.getType());
			this.changes.offer(temp);
		}
	}
	
	/*
	 * Turns the Farrel Clockwise 90 degrees
	 */
	
	public void BigTurnClockwise()
	{
		CoordNode temp;
		if(!this.farrel.isDestroyed())
		{
			this.farrel.BigTurnClockwise();
			temp = new CoordNode(this.farrel.getX(), this.farrel.getY(), this.farrel.getType());
			this.changes.offer(temp);
		}
	}
	
	/*
	 * Turns the Farrel Counter Clockwise 45 degrees
	 */
	
	public void SmallTurnCounterClockwise()
	{
		CoordNode temp;
		if(!this.farrel.isDestroyed())
		{
			this.farrel.SmallTurnCounterClockwise();
			temp = new CoordNode(this.farrel.getX(), this.farrel.getY(), this.farrel.getType());
			this.changes.offer(temp);
		}
	}
	
	/*
	 * Turns the Farrel Counter Clockwise 90 degrees
	 */
	
	public void BigTurnCounterClockwise()
	{
		CoordNode temp;
		if(!this.farrel.isDestroyed())
		{
			this.farrel.BigTurnCounterClockwise();
			temp = new CoordNode(this.farrel.getX(), this.farrel.getY(), this.farrel.getType());
			this.changes.offer(temp);
		}
	}
	
	public void printMap()
	{
		for(int j = 0; j < this.ylen; j ++)
		{
			for(int i = 0; i < this.xlen; i ++)
			{
				Block temp = this.blocklist[i][j];
				if(temp == null)
				{
					System.out.print("000 ");
				}
				else
				{
					System.out.print(temp.getType() + " ");
				}
			}
			System.out.print("\n");
		}
	}
	
	
	

}
