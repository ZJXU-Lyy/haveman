package haveman;

import haveman.HaveMan.ExtraPrintCodeImp;
import haveman.HaveMan.MakeTree;
import haveman.HaveMan.PasswordFormating;
import haveman.HaveMan.TreeNode;

import java.util.*;

/**
 * @ClassName HaveManTree
 * @Description: TODO
 * @Author Lenovo
 * @Date 2020/5/14 22:41
 */
public class HaveManTree implements MakeTree<StringIntegerTreeNode>, PasswordFormating<StringIntegerTreeNode>, TreeNode.MakeHaveManTree {

    /**
     * 需要求解最短长度哈夫曼编码的字符文本
     */
    private String string;

    /**
     * 哈夫曼树的初始节点序列
     */
    private List<StringIntegerTreeNode> stringIntegerTreeNodes;

    /**
     * 译码输出接口
     */
    private ExtraPrintCodeImp<StringIntegerTreeNode> extraPrintCodeImp;

    /**
     * 以根节点引用为索引持有整体哈夫曼树结构
     */
    StringIntegerTreeNode node;

    public HaveManTree(String string) {
        this.string = string;
    }

    public HaveManTree() {
    }

    /**
     * 以字符文本创建节点序列
     *
     * @return
     */
    @Override
    public List<StringIntegerTreeNode> makeList() {
        Map<Character, StringIntegerTreeNode> map = new HashMap<>();
        char[] chars = this.string.toCharArray();
        for (char aChar : chars) {
            if (map.get(aChar) == null) {
                StringIntegerTreeNode treeNode = new StringIntegerTreeNode(String.valueOf(aChar));
                //TODO 控制台打印使用默认方法即可
                /*treeNode.Call(new PrintCodeImp() {
                    @Override
                    public void printCode() {
                        System.out.println(treeNode.getNode() + "->" + treeNode.getRootCode());
                    }
                });*/
                map.put(aChar, treeNode);
            } else {
                map.get(aChar).autoAdd();
            }
        }
        List<StringIntegerTreeNode> list = new ArrayList<>();
        for (Character character : map.keySet())
            list.add(map.get(character));
        return list;
    }

    /**
     * 以字符数组和权值数组创建节点序列
     *
     * @param strings
     * @param ints
     * @return
     */
    @Override
    public List<StringIntegerTreeNode> makeList(String[] strings, int[] ints) {
        List<StringIntegerTreeNode> treeNodes = new ArrayList<>();
        for (int i = 0; i < strings.length; i++) {
            StringIntegerTreeNode stringIntegerTreeNode = new StringIntegerTreeNode(strings[i]);
            stringIntegerTreeNode.setValue(ints[i]);
            treeNodes.add(stringIntegerTreeNode);
        }
        return treeNodes;
    }

    /**
     * 以字符文本求解哈夫曼树
     */
    @Override
    public void makeTree() {
        stringIntegerTreeNodes = makeList();
        Make();
    }

    /**
     * 以给定字符集大小求解哈夫曼树
     *
     * @param n
     */
    @Override
    public void makeTree(int n) {
        String[] strings = new String[n];
        int[] ints = new int[n];
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < n; i++) {
            strings[i] = scanner.next();
            ints[i] = scanner.nextInt();
        }
        stringIntegerTreeNodes = makeList(strings, ints);
        Make();
    }

    /**
     * 当给定的参数小于等于0时，则认为调用者是直接以字符文本的形式构建哈夫曼树
     *
     * @param Style 传入正自然数表示按照默认手动输入的形式给定节点的数据和权值构造哈夫曼树
     *              Not_Default_Style表示按照自定义的其他构造方法构造哈夫曼树
     * @return  返回哈夫曼树的根节点，以根节点的引用代表树的引用
     */
    @Override
    public StringIntegerTreeNode getTree(int Style) {
        if (Style <= Not_Default_Style) {
            makeTree();
        } else {
            makeTree(Style);
        }
        return node;
    }

    /**
     * 获取译码输出格式，不指定输出格式则认为按照控制台打印的默认输出
     *
     * @return
     */
    public ExtraPrintCodeImp<StringIntegerTreeNode> getExtraPrintCodeImp() {
        if (extraPrintCodeImp == null) {
            extraPrintCodeImp = new ExtraPrintCodeImp<StringIntegerTreeNode>() {
                @Override
                public void printCodeExtra(StringIntegerTreeNode node) {
                    System.out.print(node.getNode());
                }
            };
        }
        return extraPrintCodeImp;
    }

    /**
     * 构造哈夫曼树核心算法
     *
     * 每次从节点集合中取出权值最小和次小的节点构成一个子树，并把子树的根节点加入到节点序列，
     * 然后将左右子节点从节点集合中移除，直到有且仅有一个节点的时候，就是哈夫曼树的根节点，而且该节点
     * 必定已经连接了所有子树，即构造哈夫曼树完成
     *
     * 因为节点集合是无序的，所以在设计节点时实现了排序接口，每次操作前后都对集合进行排序
     *
     * 循环的时候不使用for循环，因为需要移除和加入一些节点，导致每次集合的长度不一致，一定会出现异常
     *
     * 同时不能使用Iterator迭代器，虽然迭代器在改变长度的时候保持原子性不会出现异常，但是使用迭代器之后，每次排序需要重新
     * 使用迭代器构建一次，效率低，代码量大，没有使用的必要，所以使用while循环最适合本算法
     */
    @Override
    public void Make() {
        Collections.sort(stringIntegerTreeNodes);
        while (stringIntegerTreeNodes.size() > 1) {
            StringIntegerTreeNode left = stringIntegerTreeNodes.get(0);
            StringIntegerTreeNode right = stringIntegerTreeNodes.get(1);
            StringIntegerTreeNode root = new StringIntegerTreeNode();
            root.setLeft(left);
            root.setRight(right);
            root.setValue(left.getValue() + right.getValue());
            stringIntegerTreeNodes.remove(left);
            stringIntegerTreeNodes.remove(right);
            stringIntegerTreeNodes.add(root);
            Collections.sort(stringIntegerTreeNodes);
        }
        node = stringIntegerTreeNodes.get(0); // 最后剩下的节点即为根节点
    }

    /**
     * 回调译码输出
     *
     * @param extraPrintCodeImp
     * @return
     */
    public HaveManTree Call(ExtraPrintCodeImp<StringIntegerTreeNode> extraPrintCodeImp) {
        this.extraPrintCodeImp = extraPrintCodeImp;
        return this;
    }

    /**
     * 给定密码和树结构即可翻译哈夫曼编码
     *
     * 该方法是译码过程的核心代码
     *
     * 首先读入一个字符，根据哈夫曼编码的特点，一定是0或1，
     * 当读入0的时候则从当前节点向左子节点跳转，若左子节点已经是叶子节点，则输出左子节点对应的数据
     * 反正向右子节点跳转，若右子节点是叶子节点，则输出右子节点对应的数据
     * 若跳转后的节点不是叶子节点，则继续读入一个字符，重复以上步骤
     *
     * @param pwd
     * @param node 必须以树结构的根节点为参数
     */
    @Override
    public void format(String pwd, StringIntegerTreeNode node) {
        char[] chars = pwd.toCharArray();
        StringIntegerTreeNode treeNode = node;
        for (char aChar : chars) {
            if (aChar == '0') {
                treeNode = (StringIntegerTreeNode) treeNode.getLeft();
            } else {
                treeNode = (StringIntegerTreeNode) treeNode.getRight();
            }
            if (treeNode.getLeft() == null && treeNode.getRight() == null) {
                getExtraPrintCodeImp().printCodeExtra(treeNode);
                treeNode = node;    // 回退到根节点
            }
        }
    }
}
