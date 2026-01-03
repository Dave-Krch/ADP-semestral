package cz.cvut.fit.niadp.mvcgame.model;

import java.io.Serializable;

public class Position implements Serializable {
    private int dimX = 0;
	private int dimY = 0;
	
	public Position() {
	}

	public Position(int posX, int posY) {
		this.dimX = posX;
		this.dimY = posY;
	}

    public Position clone() {
        return new Position(this.dimX, this.dimY);
    }

	public int getX() {
		return dimX;
	}
    
    public int getY() {
		return dimY;
	}
    
    public void setY(int y) {
		this.dimY = y;
	}
    
    public void setX(int x) {
		this.dimX = x;
	}

	public void add(Vector vector) {
		setX(getX() + vector.getDX());
		setY(getY() + vector.getDY());
	}
}