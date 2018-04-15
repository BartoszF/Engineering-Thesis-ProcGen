package pl.bartoszf.procgen.Tree;

import java.util.ArrayList;
import java.util.List;

public class BSP<T> {

    private T leaf;
    private BSP<T> leftChild;
    private BSP<T> rightChild;

    public BSP(T leaf) {
        this.leaf = leaf;
    }

    public T getLeaf() {
        return leaf;
    }

    public void setLeaf(T leaf) {
        this.leaf = leaf;
    }

    public BSP<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(BSP<T> leftChild) {
        this.leftChild = leftChild;
    }

    public BSP<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(BSP<T> rightChild) {
        this.rightChild = rightChild;
    }

    public List<T> getLeafs() {
        if (this.getLeftChild() == null && this.getRightChild() == null) {
            return new ArrayList<T>() {{
                add(getLeaf());
            }};
        } else {
            return new ArrayList<T>() {{
                addAll(getLeftChild().getLeafs());
                addAll(getRightChild().getLeafs());
            }};
        }
    }

    public List<T> getLevel(int level, List<T> queue) {
        if (queue == null) {
            queue = new ArrayList<>();
        }
        if (level == 0) {
            queue.add(this.leaf);
        } else {
            if (this.getLeftChild() != null) {
                this.getLeftChild().getLevel(level - 1, queue);
            }
            if (this.getRightChild() != null) {
                this.getRightChild().getLevel(level - 1, queue);
            }
        }

        return queue;
    }
}
