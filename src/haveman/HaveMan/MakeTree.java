package haveman.HaveMan;

import java.util.List;

/**
 * @ClassName MakeTree
 * @Description: TODO
 * @Author Lenovo
 * @Date 2020/5/14 22:43
 */
public interface MakeTree<T extends TreeNode> extends BaseMakeTree<T> {
    /**
     * 先将需要编码的对象转化成List数组类型的节点数组
     *
     * @return
     */
    List<T> makeList();

    /**
     * 基础要求的n个字符及每个字符对应的权值（次数）构造哈夫曼树
     *
     * @param strings
     * @param ints
     * @return
     */
    List<T> makeList(String[] strings, int[] ints);

    /**
     * 再根据哈夫曼树的构造方法对List进行转化
     */
    void makeTree();

    /**
     * 基础要求输入一个数再输入相关参数构造哈夫曼树
     *
     * @param n
     */
    void makeTree(int n);
}
