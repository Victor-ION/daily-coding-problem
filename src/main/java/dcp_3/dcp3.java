package dcp_3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

public class dcp3 {
    public static void main(String[] args) {
        Node node = new Node("root",
                new Node("left", new Node("left.left"), null),
                new Node("right"));
        System.out.println(node);
        String serNode = Node.serialize(node);
        System.out.println(serNode);
        Node desNode = Node.deserialize(serNode);
        System.out.println(desNode);
        String serNode2 = Node.serialize(desNode);
        System.out.println(serNode2);
        System.out.println(serNode.equals(serNode2));

    }

    public static class Node {
        private static final String DELIMITER = "|";
//        private static Integer idx = 0;
        private String val;
        private Node left;
        private Node right;

        private Node(String val) {
            this.val = val;
        }

        public Node(String val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        public static String serialize(Node root) {
            return convertSelf(root, new AtomicInteger(0));
        }

        private static String convertSelf(Node node, AtomicInteger counter) {
            String s = node.getVal() + DELIMITER;
            int currIdx = counter.getAndIncrement();
            if (node.getLeft() != null) {
                s = s + currIdx + "L" + convertSelf(node.getLeft(), counter);
            }
            if (node.getRight() != null) {
                s = s + currIdx + "R" + convertSelf(node.getRight(), counter);
            }
            return s;
        }

        public static Node deserialize(String s) {
            int idxFirstDelimiter = s.indexOf(DELIMITER);
            Node root = new Node(s.substring(0, idxFirstDelimiter));
            String[] parts = s.substring(idxFirstDelimiter + 1).split(Pattern.quote(DELIMITER));
            List<Node> nodes = new ArrayList<Node>(parts.length + 1);
            nodes.add(root);
            for (String part : parts) {
                convertPart(part, nodes);
            }
            return root;
        }

        private static void convertPart(String part, List<Node> nodes) {
            char[] chars = part.toCharArray();
            StringBuffer sb = new StringBuffer();
            char direction = '0';
            for (char c : chars) {
                if (!Character.isDigit(c)){
                    direction = c;
                    break;
                }
                sb.append(c);
            }
            int idxParent = Integer.parseInt(sb.toString());
            String nodeVal = part.substring(part.indexOf(direction) + 1);
            Node currNode = new Node(nodeVal);
            nodes.add(currNode);
            if (direction == 'R'){
                nodes.get(idxParent).setRight(currNode);
            }
            if (direction == 'L'){
                nodes.get(idxParent).setLeft(currNode);
            }
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val='" + val + '\'' +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }

        public String getVal() {
            return val;
        }

        public void setVal(String val) {
            this.val = val;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }
}
