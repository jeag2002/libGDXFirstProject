package com.mygdx.game.ia;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.utils.NewItem;

public class MapConnection implements Connection<NewItem> {

	NewItem fromNode;
	NewItem toNode;
	float cost;
	
	public MapConnection(NewItem fromNode, NewItem toNode, float cost){
	    this.fromNode = fromNode;
	    this.toNode = toNode;
	    //cost = Vector2.dst(fromNode.getX(), fromNode.getY(), toNode.getX(), toNode.getY());
	    this.cost = cost;
	}

	@Override
	public float getCost() {
		return cost;
	}

	@Override
	public NewItem getFromNode() {
		return this.fromNode;
	}

	@Override
	public NewItem getToNode() {
		return this.toNode;
	}
	
	
	@Override
	public String toString() {
		return "EDGE " + fromNode.getIndex() + " TO " + toNode.getIndex(); 
	}
	
	 @Override
	 public boolean equals(Object obj) {
	        if (this == obj) return true;
	        if (obj == null) return false;
	        if (getClass() != obj.getClass()) return false;
	       
	        final MapConnection other = (MapConnection)obj;
	        
	        boolean test = (this.getFromNode().getIndex_X() == other.getFromNode().getIndex_X()) && 
	        		       (this.getFromNode().getIndex_Y() == other.getFromNode().getIndex_Y()) &&
	        		       (this.getToNode().getIndex_X() == other.getToNode().getIndex_X()) &&
	        		       (this.getToNode().getIndex_Y() == other.getToNode().getIndex_Y());
	        return test;
	    }
	
	
	
}
