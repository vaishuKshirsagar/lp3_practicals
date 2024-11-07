import java.util.*;

public class Huffman {

    public static void main(String[] args) {
        String text = "abracadabra";

        // Build frequency table
        Map<Character, Integer> freq = new HashMap<>();
        for (char c : text.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        // Build Huffman tree
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.freq));
        for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
            pq.add(new Node(entry.getKey(), entry.getValue()));
        }

        while (pq.size() > 1) {
            Node left = pq.poll();
            Node right = pq.poll();
            pq.add(new Node('\0', left.freq + right.freq, left, right));
        }

        Node root = pq.poll();

        // Generate Huffman codes
        Map<Character, String> codes = new HashMap<>();
        generateCodes(root, "", codes);

        // Encode the text
        StringBuilder encodedText = new StringBuilder();
        for (char c : text.toCharArray()) {
            encodedText.append(codes.get(c));
        }
        System.out.println("Encoded Text: " + encodedText);

        // Decode the text
        String decodedText = decode(root, encodedText.toString());
        System.out.println("Decoded Text: " + decodedText);
    }

    private static void generateCodes(Node node, String code, Map<Character, String> codes) {
        if (node.left == null && node.right == null) {
            codes.put(node.ch, code);
            return;
        }
        generateCodes(node.left, code + "0", codes);
        generateCodes(node.right, code + "1", codes);
    }

    private static String decode(Node root, String encodedText) {
        StringBuilder decodedText = new StringBuilder();
        Node current = root;
        for (char bit : encodedText.toCharArray()) {
            if (bit == '0') {
                current = current.left;
            } else {
                current = current.right;
            }
            if (current.left == null && current.right == null) {
                decodedText.append(current.ch);
                current = root;
            }
        }
        return decodedText.toString();
    }

    static class Node {
        char ch;
        int freq;
        Node left, right;

        Node(char ch, int freq) {
            this.ch = ch;
            this.freq = freq;
        }

        Node(char ch, int freq, Node left, Node right) {
            this(ch, freq);
            this.left = left;
            this.right = right;
        }
    }
}