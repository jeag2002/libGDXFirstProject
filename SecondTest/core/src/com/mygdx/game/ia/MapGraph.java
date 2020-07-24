package com.mygdx.game.ia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.DefaultGraphPath;
import com.badlogic.gdx.ai.pfa.GraphPath;
import com.badlogic.gdx.ai.pfa.indexed.IndexedAStarPathFinder;
import com.badlogic.gdx.ai.pfa.indexed.IndexedGraph;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.mygdx.game.utils.NewItem;

public class MapGraph implements IndexedGraph<NewItem>{
	
	MapHeuristic mHeuristic = new MapHeuristic();
	
	Array<NewItem> nodes = new Array<NewItem>();
	Array<MapConnection> edges = new Array<MapConnection>();
	ObjectMap<NewItem, Array<Connection<NewItem>>> edgesMap = new ObjectMap<>();
	
	private int lastNodeIndex = 0;
	
	
	public void addNode(NewItem node) {
		node.setIndex(lastNodeIndex);
		lastNodeIndex++;
		nodes.add(node);
	}
	
	/*
	public Array<NewItem> getNodes(){
		return nodes;
	}
	*/
	
	public ArrayList<NewItem> getNodes(){
		ArrayList<NewItem> response = new ArrayList<NewItem>(Arrays.asList(nodes.toArray(NewItem.class)));
		return response;
		
	}
	
	public List<MapConnection> getEdges(){
		List<MapConnection> response = Arrays.asList(edges.toArray(MapConnection.class));
		return response;
	}
	
	
	public void addEdge(NewItem fromNode, NewItem toNode) {
		MapConnection edge = new MapConnection(fromNode, toNode);
		if (!edgesMap.containsKey(fromNode)) {
			edgesMap.put(fromNode, new Array<Connection<NewItem>>());
		}
		
		edgesMap.get(fromNode).add(edge);
		edges.add(edge);
	}
	
	public GraphPath<NewItem> findPath(NewItem fromNode, NewItem toNode){
		GraphPath<NewItem> nodePath = new DefaultGraphPath<>();
	    new IndexedAStarPathFinder<>(this).searchNodePath(fromNode, toNode, mHeuristic, nodePath);
	    return nodePath;
	}
	@Override
	public Array<Connection<NewItem>> getConnections(NewItem fromNode) {
		if(edgesMap.containsKey(fromNode)){
		      return edgesMap.get(fromNode);
		}
		return new Array<>(0);
	}
	
	@Override
	public int getIndex(NewItem node) {
		return node.getIndex();
	}
	@Override
	public int getNodeCount() {
		return this.lastNodeIndex;
	}

	
}
