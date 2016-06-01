package movement;
public class FinchGrid{
private int[][] MainGrid;
private int xlength;
private int ylength;
private int finchx;
private int finchy;
private int finchDirection;

	public FinchGrid(int xsize, int ysize)
	{
		
		this.xlength = xsize;
		this.ylength = ysize;
		this.MainGrid = new int[xsize][ysize];
		for(int i = 0; i < ysize; i++)
		{
			for(int j = 0; j < xsize; j++)
			{
				this.MainGrid[j][i] = 0;
			}
		}
		this.MainGrid[0][0] = 203;
		this.MainGrid[0][ysize - 1] = 206;
		this.MainGrid[xsize - 1][0] = 204;
		this.MainGrid[xsize -1][ysize - 1] = 205;
		for(int i = 1; i < xsize - 1; i++)
		{
			this.MainGrid[i][0] = 202;
			this.MainGrid[i][ysize - 1] = 202;
		}
		
		for(int i = 1; i < ysize - 1; i++)
		{
			this.MainGrid[0][i] = 201;
			this.MainGrid[xsize - 1][i] = 201;
		}
		
	}
	
	public void setFinchLocation(int xloc, int yloc)
	{
		this.finchx = xloc;
		this.finchy = yloc;
		this.finchDirection = 1;
		this.MainGrid[xloc][yloc] = 101;
	}
	
	public int[] turnFinchClockwise()
	{
		int[] coords = new int[2];
		if(this.finchDirection == 8)
		{
			this.finchDirection = 1;
			this.MainGrid[finchx][finchy] = 101;
		}
		else
		{
			this.finchDirection ++;
			this.MainGrid[finchx][finchy] ++;
		}
		coords[0] = this.finchx;
		coords[1] = this.finchy;
		return coords;
	}
	
	public int[] turnFinchCounterClockwise()
	{
		int[] coords = new int[2];
		if(this.finchDirection == 1)
		{
			this.finchDirection = 8;
			this.MainGrid[finchx][finchy] = 108;
		}
		else
		{
			this.finchDirection --;
			this.MainGrid[finchx][finchy] --;
		}
		coords[0] = this.finchx;
		coords[1] = this.finchy;
		return coords;
		
	}
	
	public int[] moveForward()
	{
		int[] coords = new int[4];
		coords[0] = this.finchx;
		coords[1] = this.finchy;
		switch(this.finchDirection)
		{
			case 1:
				if(this.MainGrid[finchx][finchy - 1] == 0)
				{
					this.MainGrid[finchx][finchy - 1] = 101;
					this.MainGrid[finchx][finchy] = 0;
					finchy --;
					break;
				}
				else
				{
					return null;
				}
			case 2:
				if(this.MainGrid[finchx + 1][finchy - 1] == 0)
				{
					this.MainGrid[finchx + 1][finchy - 1] = 102;
					this.MainGrid[finchx][finchy] = 0;
					finchy --;
					finchx ++;
					break;
				}
				else
				{
					return null;
				}
			case 3:
				if(this.MainGrid[finchx + 1][finchy] == 0)
				{
					this.MainGrid[finchx + 1][finchy] = 103;
					this.MainGrid[finchx][finchy] = 0;
					finchx ++;
					break;
				}
				else
				{
					return null;
				}
			case 4:
				if(this.MainGrid[finchx + 1][finchy + 1] == 0)
				{
					this.MainGrid[finchx + 1][finchy + 1] = 104;
					this.MainGrid[finchx][finchy] = 0;
					finchx ++;
					finchy ++;
					break;
				}
				else
				{
					return null;
				}
			case 5:
				if(this.MainGrid[finchx][finchy + 1] == 0)
				{
					this.MainGrid[finchx][finchy + 1] = 105;
					this.MainGrid[finchx][finchy] = 0;
					finchy ++;
					break;
				}
				else
				{
					return null;
				}
			case 6:
				if(this.MainGrid[finchx - 1][finchy + 1] == 0)
				{
					this.MainGrid[finchx - 1][finchy + 1] = 106;
					this.MainGrid[finchx][finchy] = 0;
					finchx --;
					finchy ++;
					break;
				}
				else
				{
					return null;
				}
			case 7:
				if(this.MainGrid[finchx - 1][finchy] == 0)
				{
					this.MainGrid[finchx - 1][finchy] = 107;
					this.MainGrid[finchx][finchy] = 0;
					finchx --;
					break;
				}
				else
				{
					return null;
				}
			case 8:
				if(this.MainGrid[finchx -1][finchy - 1] == 0)
				{
					this.MainGrid[finchx - 1][finchy - 1] = 108;
					this.MainGrid[finchx][finchy] = 0;
					finchx --;
					finchy --;
					break;
				}
				else
				{
					return null;
				}				
		}
		
		
		coords[2] = this.finchx;
		coords[3] = this.finchy;
		return coords;
	}
	
	public int getTileType(int x, int y)
	{
		return this.MainGrid[x][y];
	}
	
	public void printGrid()
	{
		for(int i = 0; i < ylength; i++)
		{
			for(int j = 0; j < xlength; j++)
			{
				if(this.MainGrid[j][i] == 0)
				{
					System.out.print("|000|");
				}
				else
				{
					System.out.print("|" + this.MainGrid[j][i] + "|");
				}
			}
			System.out.println("");
		}
	}
	
}
