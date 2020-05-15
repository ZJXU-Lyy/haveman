package haveman.HaveMan;

/**
 * 译码输出标准
 *
 * @ClassName ExtraPrintCodeImp
 * @Description: TODO
 * @Author Lenovo
 * @Date 2020/5/15 0:14
 */
public interface ExtraPrintCodeImp<T extends TreeNode> {
    /**
     * 调用方通过该接口即可定义译码结果的输出形式
     *
     * @param node
     */
    void printCodeExtra(T node);
}
