package com.mygdx.game.elements.complexenemy;

import com.badlogic.gdx.ai.pfa.GraphPath;
import com.mygdx.game.ia.MapGraph;
import com.mygdx.game.utils.NewItem;


//https://happycoding.io/tutorials/libgdx/pathfinding

public class IAPathFindingObject {
	
	private MapGraph graph;
	private GraphPath<NewItem> path;
	
	
	public IAPathFindingObject() {
	}
	
	public void initIAPathFinding(MapGraph _graph, NewItem inipos, NewItem posObj) {
		this.graph = _graph;
		this.path = this.graph.findPath(inipos, posObj);
	}
	
	
	
}
