import java.util.Map;
class Encode {
    void encode(Node root, String str, Map<Character, String> huffmanCode) {
        if (root == null)
            return;
        // found a leaf node
        if (root.left == null && root.right == null) {
            huffmanCode.put(root.ch, str);
        }

        encode(root.left, str + "0", huffmanCode);
        encode(root.right, str + "1", huffmanCode);
    }
}
