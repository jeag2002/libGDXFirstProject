package com.mygdx.game.ia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.DefaultGraphPath;
import com.badlogic.gdx.ai.pfa.GraphPath;
import com.badlogic.gdx.ai.pfa.PathFinder;
import com.badlogic.gdx.ai.pfa.PathFinderRequest;
import com.badlogic.gdx.ai.pfa.indexed.IndexedAStarPathFinder;
import com.badlogic.gdx.ai.pfa.indexed.IndexedGraph;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.mygdx.game.utils.NewItem;

public class MapGraph implements IndexedGraph<NewItem>{
	
	MapHeuristic mHeuristic = new MapHeuristic();
	MapHeuristicManhattan mHeuristicManhattan = new MapHeuristicManhattan();
	
	Array<NewItem> nodes = new Array<NewItem>();
	Array<MapConnection> edges = new Array<MapConnection>();
	HashMap<Integer, Array<Connection<NewItem>>> edgesMap = new HashMap<>();
	
	public void addNode(NewItem node) {
		nodes.add(node);
	}
	
	public ArrayList<NewItem> getNodes(){
		ArrayList<NewItem> response = new ArrayList<NewItem>(Arrays.asList(nodes.toArray(NewItem.class)));
		return response;
		
	}
	
	public List<MapConnection> getEdges(){
		List<MapConnection> response = Arrays.asList(edges.toArray(MapConnection.class));
		return response;
	}
	
	public HashMap<Integer, Array<Connection<NewItem>>> getConnectionsMap(){
		return edgesMap;
	}
	
	
	public void addEdge(NewItem fromNode, NewItem toNode, float cost) {
		MapConnection edge = new MapConnection(fromNode, toNode, cost);
		if (!edgesMap.containsKey(fromNode.getIndex())) {
			edgesMap.put(fromNode.getIndex(), new Array<Connection<NewItem>>());
		}
		
		edgesMap.get(fromNode.getIndex()).add(edge);
		edges.add(edge);
	}
	
	public GraphPath<Connection<NewItem>> findPath(NewItem fromNode, NewItem toNode){
		
	   GraphPath<Connection<NewItem>> nodePath = new DefaultGraphPath<>();
	   final PathFinder<NewItem> pathFinder = new IndexedAStarNewItemPathFinder(this,false);
	   final boolean searchResult = pathFinder.searchConnectionPath(fromNode, toNode, mHeuristic, nodePath); 	   
	   return nodePath;
	}
	
	@Override
	public Array<Connection<NewItem>> getConnections(NewItem fromNode) {
		if(edgesMap.get(fromNode.getIndex())!=null){
		      return edgesMap.get(fromNode.getIndex());
		}
		return new Array<>(0);
	}
	
	@Override
	public int getIndex(NewItem node) {
		return node.getIndex();
	}
	
	@Override
	public int getNodeCount() {
		return nodes.size;
	}

	
}
