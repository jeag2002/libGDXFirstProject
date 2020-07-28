package com.mygdx.game.ia;

import com.badlogic.gdx.ai.pfa.Heuristic;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.utils.NewItem;

public class MapHeuristicManhattan implements Heuristic<NewItem> {
	
	@Override
	public float estimate(NewItem node, NewItem endNode) {
		return Math.abs(endNode.getIndex_X() - node.getIndex_X()) + Math.abs(endNode.getIndex_Y() - node.getIndex_Y());
	}

}
