/** TreeExample.java from
 *  http://www.codejava.net/java-se/swing/jtree-basic-tutorial-and-examples
 *  also usefull this link
 *  http://spec-zone.ru/RU/Java/Tutorials/uiswing/components/tree.html
 */
import javax.swing.*;
import javax.swing.tree.*;

public class TreeExample extends JFrame {
    private JTree tree;

    public TreeExample() {

        //create the root node
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");

        //create the child nodes
        DefaultMutableTreeNode vegetableNode = 
            new DefaultMutableTreeNode("Vegetables");
        vegetableNode.add(new DefaultMutableTreeNode("Capsicum"));
        vegetableNode.add(new DefaultMutableTreeNode("Carrot"));
        vegetableNode.add(new DefaultMutableTreeNode("Tomato"));
        vegetableNode.add(new DefaultMutableTreeNode("Potato"));

        DefaultMutableTreeNode fruitNode = new DefaultMutableTreeNode("Fruits");
        fruitNode.add(new DefaultMutableTreeNode("Banana"));
        fruitNode.add(new DefaultMutableTreeNode("Mango"));
        fruitNode.add(new DefaultMutableTreeNode("Apple"));
        fruitNode.add(new DefaultMutableTreeNode("Grapes"));
        fruitNode.add(new DefaultMutableTreeNode("Orange"));

        //add the child nodes to the root node
        root.add(vegetableNode);
        root.add(fruitNode);

        //create the tree by passing in the root node
        tree = new JTree(root);
        add(new JScrollPane(tree)); //add(tree);
        tree.setShowsRootHandles(true); // show special sign for root node
        //tree.setRootVisible(false); // hiding root node

        setTitle("JTree Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(300, 300);
        setSize(250, 400);
        //pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TreeExample();
            }
        });
    }
}