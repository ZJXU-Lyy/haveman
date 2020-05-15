package haveman;

import haveman.HaveMan.TreeNode;

/**
 * @ClassName StringIntegerTreeNode
 * @Description: TODO
 * @Author Lenovo
 * @Date 2020/5/14 22:31
 */
public class StringIntegerTreeNode extends TreeNode<String, Integer> implements Comparable<StringIntegerTreeNode> {

    public StringIntegerTreeNode() {
        super();
        setValue(1);
    }

    public StringIntegerTreeNode(String s) {
        super(s);
        setValue(1);
    }

    @Override
    public void autoAdd() {
        setValue(getValue() + 1);
    }

    @Override
    public int compareTo(StringIntegerTreeNode stringIntegerTreeNode) {
        return getValue() - stringIntegerTreeNode.getValue();
    }

}
