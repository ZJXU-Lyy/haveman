package haveman;

/**
 * @ClassName HaveManMain
 * @Description: TODO
 * @Author Lenovo
 * @Date 2020/5/14 21:19
 */
public class HaveManMain {
    public static void main(String[] args) {
        String test = "hello world!";
        HaveManTree haveManTree = new HaveManTree(test);
        StringIntegerTreeNode treeNode = haveManTree.getTree(HaveManTree.Not_Default_Style);
        treeNode.printRootCode();
        haveManTree.format("10000001011011101",treeNode);
    }
}
