package io.github.codedumper.model.planarity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.github.codedumper.model.GameModel;

import java.awt.*;

//グラフの管理全般を行い、モデルに情報を渡すクラス
@SuppressWarnings("deprecation")
public class GraphManager {
    private List<Point> nodes;
    private List<Edge> edges;
    private GameModel model;
    private GraphGenerator generator;
    //コンストラクタ
    public GraphManager(GameModel model){
        this.nodes = new ArrayList<>();
        this.edges = new ArrayList<>();
        this.model = model;
        this.generator = new GraphGenerator(this);
    }
    //ノードを追加する関数
    public void addNode(Point node){
        nodes.add(node);
        model.notifyObservers();
    }
    //辺を追加する関数。辺はノードのPointを指すことで管理している。
    public void addEdge(int start, int end){
        edges.add(new Edge(start, end));
        model.notifyObservers();
    }
    //ノードを移動する関数
    public void moveNode(int index, java.awt.Point newPosition){
        Point p = new Point((int)newPosition.getX(), (int)newPosition.getY());
        nodes.set(index, p);
        System.out.println(isGameSolved());
        if(isGameSolved()){
            model.notifyObservers("SOLVED");
        }else{
            model.notifyObservers("UNSOLVED");
        }
    }
    //ノードを削除する関数。ガベージコレクションによりnodesとedgesを解放
    public void clearNodes(){
        nodes = null;
        edges = null;
    }

    //パズルを再生成する関数。
    public void recreatePuzzle(){
        clearNodes();
        generator.generateRandomGraph();
    }
    //交差している辺を取得し、Listとして返す関数。
    public List<Edge> getIntersectingEdges(){
        Set<Edge> intersectingEdges = new HashSet<Edge>();
        for(int i = 0; i < edges.size(); i++){
            for(int j = i + 1; j < edges.size(); j++){
                if(isIntersecting(edges.get(i), edges.get(j))){
                    intersectingEdges.add(edges.get(i));
                    intersectingEdges.add(edges.get(j));
                }
            }
        }
        return new ArrayList<>(intersectingEdges);
    }
    //ゲームがクリアされたかを示すbooleanを返す関数。交差している辺が存在していないかどうかで実装している
    public boolean isGameSolved(){
        List<Edge> intersectList = getIntersectingEdges();
        if(intersectList == null || intersectList.size() == 0 ){
            return true;
        }else{
            return false;
        }
    }

    //二つのエッジが交差しているかどうかを返す関数。
    private boolean isIntersecting(Edge e1, Edge e2){
        //e1,e2の辺を作るノードを取得する
        Point e1Start = nodes.get(e1.getStartIndex());
        Point e1End = nodes.get(e1.getEndIndex());
        Point e2Start = nodes.get(e2.getStartIndex());
        Point e2End = nodes.get(e2.getEndIndex());

        //ノードがつながっているのは辺が交差しているわけではないのでその場合はfalseを返す
        if (e1.getStartIndex() == e2.getStartIndex() ||
            e1.getStartIndex() == e2.getEndIndex() ||
            e1.getEndIndex() == e2.getStartIndex() ||
            e1.getEndIndex() == e2.getEndIndex()) {
            return false;
        }

        return isCrossing(e1Start, e1End, e2Start, e2End);
    }
    //ベクトルの外積(クロス積)により、4つのノードが交差しているかを返す関数。
    private boolean isCrossing(Point a, Point b, Point c, Point d) {
        double cross1 = crossProduct(b, a, c) * crossProduct(b, a, d);
        double cross2 = crossProduct(d, c, a) * crossProduct(d, c, b);
    
        // 条件1: 線分の端点が異なる側にある
        boolean differentSides = cross1 < 0 && cross2 < 0;
    
        // 条件2: 端点が線分上にある場合を考慮
        boolean endpointOnLine = (crossProduct(b, a, c) == 0 && isOnSegment(a, b, c)) ||
                                 (crossProduct(b, a, d) == 0 && isOnSegment(a, b, d)) ||
                                 (crossProduct(d, c, a) == 0 && isOnSegment(c, d, a)) ||
                                 (crossProduct(d, c, b) == 0 && isOnSegment(c, d, b));
    
        return differentSides || endpointOnLine;
    }
    
    // 点pが線分ab上に存在するか判定する関数
    private boolean isOnSegment(Point a, Point b, Point p) {
        return Math.min(a.getX(), b.getX()) <= p.getX() && p.getX() <= Math.max(a.getX(), b.getX()) &&
               Math.min(a.getY(), b.getY()) <= p.getY() && p.getY() <= Math.max(a.getY(), b.getY());
    }

    private double crossProduct(Point p1, Point p2, Point p3){
        return (p3.getX() - p1.getX()) * (p2.getY() - p1.getY()) - (p3.getY() - p1.getY()) * (p2.getX() - p1.getX());
    }

    // ノードとエッジの取得を行うゲッター
    public List<Point> getNodes() {
        return nodes;
    }
    
    public List<Edge> getEdges() {
        return edges;
    }
    

}
