package io.github.codedumper.model;
import java.awt.Point;
import java.util.List;
import io.github.codedumper.model.planarity.Edge;

//グラフを管理する性質。
public interface IGraphManager {
    /**
     * @return パズルにセットされているノードのリスト
     */
    List<Point> getNodes();

    /**
     * @return 現在パズルにセットされているエッジのリスト
     */
    List<Edge> getEdges();

    /**
     * @param index 移動するノードのインデックス
     * @param newPosition 移動する位置の座標
     */
    void moveNode(int index, Point newPosition);

    /**
     * @return パズルがとかれたかどうかのbooleanフラグ
     */
    boolean isPuzzleSolved();

    /**
     * @brief パズルを再生成するメソッド
     */
    void recreatePuzzle();
  
    /**
     * @return パズル内で交差しているエッジのリスト
     */
    List<Edge> getIntersectingEdges();
}
