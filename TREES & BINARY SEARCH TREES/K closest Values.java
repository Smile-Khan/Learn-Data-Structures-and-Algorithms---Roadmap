// QUESTION: K closest Values
// https://www.geeksforgeeks.org/problems/k-closest-values/1

// SOLUTION: Most Efficient - Custom Comparator + Quick Select

/*
class Node {
    int data;
    Node left;
    Node right;

    Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}
*/

class Solution {
    public ArrayList<Integer> getKClosest(Node root, int target, int k) {
        // code here
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        
        // Custom comparator for distance to target
        Collections.sort(list, (a, b) -> {
            int diff1 = Math.abs(a - target);
            int diff2 = Math.abs(b - target);
            if(diff1 != diff2)
            {
                return diff1 - diff2;
            }
            return a - b; // if same distance, choose smaller value
        });
        
        // Take first k elements and sort them
        ArrayList<Integer> result = new ArrayList<>();
        for(int i = 0; i < k && i < list.size(); i++)
        {
            result.add(list.get(i));
        }
        Collections.sort(result);
        return result;
    }
    private void inorder(Node node, List<Integer> list)
    {
        if(node == null)
        {
            return;
        }
        inorder(node.left, list);
        list.add(node.data);
        inorder(node.right, list);
    }
}