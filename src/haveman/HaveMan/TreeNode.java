package haveman.HaveMan;

/**
 * 哈夫曼树节点底层抽象类，定义大部分操作方法
 *
 * @param <T> 指定节点对象，例如对字符串编码，则T可以为char的包装类或者String
 * @param <V> 权重对象，一般使用int的包装类，复杂情况下可以使用float和double的包装类
 * @ClassName TreeNode
 * @Description: TODO
 * @Author Lenovo
 * @Date 2020/5/14 21:29
 */
public abstract class TreeNode<T, V> {

    /**
     * 节点持有的对象引用
     */
    private T node;

    /**
     * 左子节点
     */
    private TreeNode<T, V> left = null;

    /**
     * 右子节点
     */
    private TreeNode<T, V> right = null;

    /**
     * 权重
     */
    private V value;

    /**
     * 根节点到本节点的编码
     */
    private String rootCode = "";

    /**
     * 输出哈夫曼树结构接口
     */
    private PrintCodeImp printCodeImp;

    public TreeNode() {
    }

    public TreeNode(T node) {
        this.node = node;
    }

    /**
     * 遍历编码对象，权重自增
     */
    public abstract void autoAdd();

    /**
     * 添加左子节点
     *
     * @param left
     */
    public void addLeft(TreeNode<T, V> left) {
        this.left = left;
    }

    /**
     * 添加右子节点
     *
     * @param right
     */
    public void addRight(TreeNode<T, V> right) {
        this.right = right;
    }

    /**
     * 追加根编码
     *
     * @param root
     * @param code
     */
    public void addRootCode(String root, String code) {
        this.rootCode = root + code;
    }

    /**
     * 调用哈夫曼树结构输出函数，不指定具体输出过程则调用默认方法
     *
     * @return
     */
    public PrintCodeImp getPrintCodeImp() {
        if (printCodeImp == null) {
            printCodeImp = new PrintCodeImp() {
                @Override
                public void printCode() {
                    System.out.println(node.toString() + "->" + rootCode);
                }
            };
        }
        return printCodeImp;
    }

    /**
     * 传入对应的哈夫曼树结构输出函数
     *
     * @param printCodeImp
     * @return
     */
    public TreeNode<T, V> Call(PrintCodeImp printCodeImp) {
        this.printCodeImp = printCodeImp;
        return this;
    }

    /**
     * 递归打印，从根节点启动
     */
    public void printRootCode() {
        if (this.left == null) {
            if (this.right == null) {
                // 说明本节点为叶子节点，则输出节点的编码
                getPrintCodeImp().printCode();
                // 成功则需要拦截该叶子节点向下转播输出信号
                return;
            } else {
                // 说明本节点有且仅有右子节点，使其根编码+"1"
                this.right.addRootCode(getRootCode(), "1");
            }
        } else {
            if (this.right == null) {
                // 说明本节点有且仅有左子节点，使其根编码+"0"
                this.left.addRootCode(getRootCode(), "0");
            } else {
                // 左右子节点同时存在，使其根编码加上对应编码
                this.left.addRootCode(getRootCode(), "0");
                this.right.addRootCode(getRootCode(), "1");
            }
        }
        // 向下传递输出信号
        this.left.printRootCode();
        this.right.printRootCode();
    }

    public T getNode() {
        return node;
    }

    public void setNode(T node) {
        this.node = node;
    }

    public TreeNode<T, V> getLeft() {
        return left;
    }

    public void setLeft(TreeNode<T, V> left) {
        this.left = left;
    }

    public TreeNode<T, V> getRight() {
        return right;
    }

    public void setRight(TreeNode<T, V> right) {
        this.right = right;
    }

    public String getRootCode() {
        return rootCode;
    }

    public void setRootCode(String rootCode) {
        this.rootCode = rootCode;
    }

    public void setPrintCodeImp(PrintCodeImp printCodeImp) {
        this.printCodeImp = printCodeImp;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    /**
     * 构造哈夫曼树
     */
    public interface MakeHaveManTree{
        /**
         * 核心构造方法
         */
        void Make();
    }

}
