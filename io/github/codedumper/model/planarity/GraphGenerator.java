package io.github.codedumper.model.planarity;
import java.util.Random;
import java.awt.*;

//グラフの作成のみを扱うクラス。
public class GraphGenerator {
    final int NUM_NODES = 7;
    final int MAX_EDGES = 4;
    GraphManager manager;

    //コンストラクタ
    public GraphGenerator(GraphManager manager){
        this.manager = manager;
        //パズルの作成を行う
        generateRandomGraph();
    }
    //ランダムグラフを作成する関数
    public void generateRandomGraph() {
        // ノードの追加
        for (int i = 0; i < NUM_NODES; i++) {
            int x = new Random().nextInt(400) + 50; // ランダムにX座標を生成 (50-450)
            int y = new Random().nextInt(400) + 50; // ランダムにY座標を生成 (50-450)
            manager.addNode(new Point(x, y));
        }
        // エッジの追加
        for (int i = 0; i < NUM_NODES; i++) {
            int numEdges = new Random().nextInt(MAX_EDGES) + 1; // 各ノードから1本か2本のエッジを生成
            for (int j = 0; j < numEdges; j++) {
                int targetNode = new Random().nextInt(NUM_NODES);
                if (i != targetNode) {  // 同じノードにエッジを追加しない
                    manager.addEdge(i, targetNode);
                }
            }
        }
    }
}
