class Decode {
     int decode(Node root, int index, StringBuilder sb)
	{
		if (root == null)
			return index;

		// found a leaf node
		if (root.left == null && root.right == null)
		{
			System.out.print(root.ch);
			return index;
		}

		index++;

		if (sb.charAt(index) == '0')
			index = decode(root.left, index, sb);
		else
			index = decode(root.right, index, sb);

		return index;
	}
}
