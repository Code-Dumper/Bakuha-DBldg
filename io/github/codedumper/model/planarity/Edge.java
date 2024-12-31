package io.github.codedumper.model.planarity;

public class Edge {
    int startIndex, endIndex;
    public Edge(int start, int end){
        this.startIndex = start;
        this.endIndex = end;
    }
    public int getStartIndex(){
        return startIndex;
    }
    public int getEndIndex(){
        return endIndex;
    }
}