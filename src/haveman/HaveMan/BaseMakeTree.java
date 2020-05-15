package haveman.HaveMan;

/**
 * 本接口作为哈夫曼树的基本接口，所有构建哈夫曼树设计的接口必须继承本接口
 * <p>
 * 具体的构造哈夫曼树需要根据实际情况的参数进行设计
 * <p>
 * 所以在本接口中只定义树的获取方法，规定由根节点表示哈夫曼树
 *
 * @ClassName BaseMakeTree
 * @Description: TODO
 * @Author Lenovo
 * @Date 2020/5/15 23:30
 */
public interface BaseMakeTree<T extends TreeNode> {

    /**
     * 不使用默认风格获取哈夫曼树根节点
     */
    static final int Not_Default_Style = 0;

    /**
     * 默认以输入需要编码的个数进行构造
     * <p>
     * 其他构造方法需要实现类自行修改逻辑
     *
     * @param Style
     * @return
     */
    T getTree(int Style);
}
