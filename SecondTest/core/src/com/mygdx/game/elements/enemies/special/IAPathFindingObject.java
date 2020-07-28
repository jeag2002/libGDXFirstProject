package com.mygdx.game.elements.enemies.special;


import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.GraphPath;
import com.badlogic.gdx.utils.Queue;
import com.mygdx.game.SecondTestGDX;
import com.mygdx.game.ia.MapGraph;
import com.mygdx.game.utils.NewItem;


//https://happycoding.io/tutorials/libgdx/pathfinding

public class IAPathFindingObject {
	
	private MapGraph graph;
	private GraphPath<Connection<NewItem>> path;
	
	private NewItem inipos;
	private NewItem finpos;
	
	private Queue<NewItem> nodePath;
	
	
	public IAPathFindingObject() {
		this.nodePath = new Queue<NewItem>();
	}
	
	public void initIAPathFinding(MapGraph _graph, NewItem inipos, NewItem pos) {
		this.graph = _graph;
		this.inipos = normalizeItem(inipos, graph.getNodes());
		this.finpos = normalizeItem(pos, graph.getNodes());
		this.path = this.graph.findPath(this.inipos, this.finpos);
		processPath();
	}
	
	
	
	
	private NewItem normalizeItem(NewItem pos, ArrayList<NewItem> nodes) {
		
		NewItem result = new NewItem(pos);
		
		boolean found = false;
		
		for(int i=0; i<nodes.size() && !found; i++) {
			
			NewItem node = nodes.get(i);
			
			float x_inf = node.getX();
			float y_inf = node.getY();
			
			float x_sup = node.getX() + SecondTestGDX.tileWidth_TL;
			float y_sup = node.getY() + SecondTestGDX.tileHeight_TL;
			
			
			
			found = (pos.getX() >= x_inf) && (pos.getX() < x_sup) && (pos.getY() >= y_inf) && (pos.getY() < y_sup);			
			if (found) {
				//System.out.println("EVALUATE NODE (" + node.getIndex() + ") (" + pos.getX() + "," + pos.getY() + ") (xinf,yinf):=("+x_inf+","+y_inf+") (xsup,ysup):=("+x_sup+","+y_sup+") found " + found);
				result = new NewItem(node);
			}
			
			
		}

		return result;
		
		
	}
	
	
	
	
	private void processPath() {
		
		String path_found = "";
		
		this.nodePath.addFirst(this.inipos);
		
		for (int i = 1; i < path.getCount(); i++) {
			path_found += "(" +  path.get(i).getFromNode().getIndex() + "),"; 
			this.nodePath.addLast(path.get(i).getFromNode());
		}
		
		this.nodePath.addLast(this.finpos);
		
		Gdx.app.log("[TANK-IA]","PATH FOUND " + this.nodePath.toString());
	}
	
	
	public Queue<NewItem> getPath(){
		return nodePath;
	}
	
	
	
	
}
