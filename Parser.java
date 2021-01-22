public class Parser {
    public static BinaryTree<String> parseString(String string) {
        string = string.trim().replaceAll(",", ".");
        BinaryTree<String> tree = new BinaryTree<>("");
        char[] arr = string.toCharArray();
        int k = 0;

        for (int i = 0; i < string.length(); i++) {
            char c = arr[i];
            BinaryTree<String> left;
            BinaryTree<String> right;

            switch (c) {
                case '(':
                    int pos = string.indexOf(')', i);
                    if (pos == string.length() - 1)
                        tree.setRightTree(parseString(string.substring(i + 1, pos)));
                    i = pos;
                    break;
                case '*':
                    if (arr[i - 1] == ')')
                        left = parseString(string.substring(1, i - 1));
                    else
                        left = new BinaryTree<>(string.substring(0, i));
                    tree = new BinaryTree<>("*");
                    tree.setLeftTree(left);
                    k = i;
                    break;
                case '/':
                    if (arr[i - 1] == ')')
                        left = parseString(string.substring(1, i - 1));
                    else
                        left = new BinaryTree<>(string.substring(0, i));
                    tree = new BinaryTree<>("/");
                    tree.setLeftTree(left);
                    k = i;
                    break;
                case '+':
                    if (!tree.isEmpty() && (tree.getContent().equals("*") || tree.getContent().equals("/"))) {
                        if (arr[k + 1] == '(')
                            right = parseString(string.substring(k + 2, i - 1));
                        else
                            right = new BinaryTree<>(string.substring(k + 1, i));
                        tree.setRightTree(right);
                        tree = new BinaryTree<>("+", tree, parseString(string.substring(i + 1)));
                        return tree;
                    }
                    if (arr[i - 1] == ')')
                        left = parseString(string.substring(1, i - 1));
                    else
                        left = new BinaryTree<>(string.substring(0, i));
                    tree = new BinaryTree<>("+", left, parseString(string.substring(i + 1)));
                    return tree;
                case '-':
                    if (!tree.isEmpty() && (tree.getContent().equals("*") || tree.getContent().equals("/"))) {
                        if (arr[k + 1] == '(')
                            right = parseString(string.substring(k + 2, i - 1));
                        else
                            right = new BinaryTree<>(string.substring(k + 1, i));
                        tree.setRightTree(right);
                        tree = new BinaryTree<>("-", tree, parseString(string.substring(i + 1)));
                        return tree;
                    }
                    if (arr[i - 1] == ')')
                        left = parseString(string.substring(1, i - 1));
                    else
                        left = new BinaryTree<>(string.substring(0, i));
                    tree = new BinaryTree<>("-", left, parseString(string.substring(i + 1)));
                    return tree;
                default:
                    if (k > 0 && i == string.length() - 1) {
                        right = new BinaryTree<>(string.substring(k + 1));
                        tree.setRightTree(right);
                    } else if (i == string.length() - 1)
                        tree = new BinaryTree<>(string);
            }
        }
        return tree;
    }
}
