package haveman.HaveMan;

/**
 * 译码
 *
 * @ClassName PasswordFormating
 * @Description: TODO
 * @Author Lenovo
 * @Date 2020/5/15 0:12
 */
public interface PasswordFormating<T extends TreeNode> {
    /**
     * 根据加密后的哈夫曼编码以及建立成功后的哈夫曼树（以根节点的形式）进行译码
     *
     * @param pwd
     * @param node
     */
    void format(String pwd, T node);
}
