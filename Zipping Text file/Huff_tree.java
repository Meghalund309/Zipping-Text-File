import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
class Huff_tree {
	void buildHuffmanTree(String text) {
		// count frequency of appearance of each character
		// and store it in a map
		// System.out.println("My file = "+text);
		Map<Character, Integer> freq = new HashMap<>();
		for (int i = 0; i < text.length(); i++) {
			if (!freq.containsKey(text.charAt(i))) {
				freq.put(text.charAt(i), 0);
			}
			freq.put(text.charAt(i), freq.get(text.charAt(i)) + 1);
		}

		// Create a priority queue to store live nodes of Huffman tree
		PriorityQueue<Node> pq = new PriorityQueue<>((l, r) -> l.freq - r.freq);
		for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
			pq.add(new Node(entry.getKey(), entry.getValue()));
		}
		while (pq.size() != 1) {
			Node left = pq.poll();
			Node right = pq.poll();

			// Create a new internal node with these two nodes as children
			// and with frequency equal to the sum of the two nodes
			// frequencies. Add the new node to the priority queue.
			int sum = left.freq + right.freq;
			pq.add(new Node('\0', sum, left, right));
		}
		// root stores pointer to root of Huffman Tree
		Node root = pq.peek();

		// traverse the Huffman tree and store the Huffman codes in a map
		Map<Character, String> huffmanCode = new HashMap<>();

		Encode en = new Encode();
		Decode dn = new Decode();
		en.encode(root, "", huffmanCode);

		// print the Huffman codes

		// String outputFilePath = "C:\\Users\\user\\Desktop\\Encoded_File.txt";
		String outputFilePath = "C:\\Users\\user\\Desktop\\Project_DSA\\Encoded_File.txt";
		// 
		System.out.println("Huffman Codes are :\n");
		// for (Map.Entry<Character, String> entry : huffmanCode.entrySet()) {
		// 	System.out.println(entry.getKey() + " " + entry.getValue());
		// }
		File file = new File(outputFilePath);

		BufferedWriter bf = null;

		try {

			// create new BufferedWriter for the output file
			bf = new BufferedWriter(new FileWriter(file));

			// iterate map entries
			for (Map.Entry<Character, String> entry : huffmanCode.entrySet()) {
				// put key and value separated by a colon
				bf.write(entry.getKey() + ":"+ entry.getValue());
				// new line
				bf.newLine();
			}
			bf.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			try {

				// always close the writer
				bf.close();
			} catch (Exception e) {
			}
		}

		// System.out.println("\nOriginal string was :\n" + text);
				// print encoded string
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < text.length(); i++) {
					sb.append(huffmanCode.get(text.charAt(i)));
				}
				System.out.println("\nEncoded string is :\n" + sb);
				int index = -1;
				System.out.println("\nDecoded string is: \n");
				while (index < sb.length() - 2) {
					index = dn.decode(root, index, sb);
				}
			}
}