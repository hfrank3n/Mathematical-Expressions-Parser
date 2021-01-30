public class Parser {
    static final String defaultInput = "5+3";
    //static BinaryTree<String> termbaum = parseString("(5 + 3 + 4 + 6)");


    public static BinaryTree<String> parseString(String string) {
        string = string.trim().replaceAll(",", ".");
        BinaryTree<String> tree = new BinaryTree<>("");
        char[] stringArray = string.toCharArray();
        int operatorPosition = 0;

        for (int index = 0; index < string.length(); index++) {
            char currentChar = stringArray[index];

            switch (currentChar) {
                case '(' -> {
                    int pos = string.indexOf(')', index);
                    if (pos == string.length() - 1)
                        tree.setRightTree(parseString(string.substring(index + 1, pos)));
                    index = pos;
                }

                case '*', '/' -> {
                    tree = calculateHigherLevelOperator(string, stringArray, index, tree);
                    operatorPosition = index;
                }

                case '+', '-' -> {
                    return calculateLowLevelOperator(string, stringArray, index, operatorPosition, tree);
                }

                default -> {
                    if (operatorPosition > 0 && index == string.length() - 1) {
                        tree.setRightTree(new BinaryTree<>(string.substring(operatorPosition + 1)));
                    } else if (index == string.length() - 1)
                        tree = new BinaryTree<>(string);
                }
            }
        }
        return tree;
    }

    public static BinaryTree<String> calculateLowLevelOperator(String string, char[] stringArray, int index, int operatorPosition, BinaryTree<String> tree) {
        String currOperator = String.valueOf(stringArray[index]);

        if (!tree.isEmpty() && (tree.getContent().equals("*") || tree.getContent().equals("/"))) {
            if (stringArray[operatorPosition + 1] == '(')
                tree.setRightTree(parseString(string.substring(operatorPosition + 2, index - 1)));
            else
                tree.setRightTree(new BinaryTree<>(string.substring(operatorPosition + 1, index)));
            tree = new BinaryTree<>(currOperator, tree, parseString(string.substring(index + 1)));
            return tree;
        }
        if (stringArray[index - 1] == ')')
            tree = new BinaryTree<>(currOperator, parseString(string.substring(1, index - 1)), parseString(string.substring(index + 1)));
        else
            tree = new BinaryTree<>(currOperator, parseString(string.substring(0, index)), parseString(string.substring(index + 1)));
        return tree;
    }

    public static BinaryTree<String> calculateHigherLevelOperator(String string, char[] stringArray, int index, BinaryTree<String> tree) {
        String currOperator = String.valueOf(stringArray[index]);

        tree = new BinaryTree<>(currOperator);
        if (stringArray[index - 1] == ')') {
            tree.setLeftTree(parseString(string.substring(1, index - 1)));
        } else {
            tree.setLeftTree(parseString(string.substring(0, index)));
        }
        return tree;
    }
}
