import java.util.HashMap;
import java.util.Scanner;


public class TrieNode {

    // Each Trie Node contains a Map 'child'

    // where each alphabet points to a Trie which has has value as Node.

    HashMap<Character, TrieNode> child;//here character is key and the TrieNode is its value


    // 'isEnd' is true if the node represents end of the data entered

    boolean isEnd;


    // Default Constructor

    public TrieNode() {

        child = new HashMap<Character, TrieNode>();//initiallizing child


        // initially initialize all the Trie nodes with NULL

        for (char i = 'a'; i <= 'z'; i++)//in this searching we will operate on lowercase font

            child.put(i, null);//assigning the value


        isEnd = false;//initially making the isEnd false

    }
}

//the class below is created to assign functions to trie
public class Trie {

    TrieNode root;//declaring the  root for trieNode

    // Insert a Contact into the Trie

    public void insert(String s) {

        int len = s.length();


        // 'itr' is used to iterate the Trie Nodes

        TrieNode itr = root;

        for (int i = 0; i < len; i++) {

            // Check if the s[i] is already present in

            // Trie

            TrieNode nextNode = itr.child.get(s.charAt(i));

            if (nextNode == null) {

                // If not found then create a new TrieNode

                nextNode = new TrieNode();


                // Insert into the HashMap

                itr.child.put(s.charAt(i), nextNode);

            }


            // Move the iterator('itr') ,to point to next

            // Trie Node

            itr = nextNode;


            // If its the last character of the string 's'

            // then mark 'isLast' as true

            if (i == len - 1)

                itr.isLast = true;

        }

    }
    // Insert all the data into the Trie

    public void insertIntoTrie(String contacts[]) {

        root = new TrieNode();//initiallizing the root

        int n = contacts.length;//calculating total length of data in terms of words

        for (int i = 0; i < n; i++) {

            insert(contacts[i]);//calling the insert function

        }

    }


    // This function simply displays all dictionary words

    // going through current node.  String 'prefix'

    // represents string corresponding to the path from

    // root to curNode.

    public void displayContactsUtil(TrieNode curNode, String prefix) {


        // Check if the string 'prefix' ends at this Node

        // If yes then display the string found so far

        if (curNode.isEnd)

            System.out.println(prefix);


        // Find all the adjacent Nodes to the current

        // Node and then call the function recursively

        // This is similar to performing DFS on a graph

        for (char i = 'a'; i <= 'z'; i++) {

            TrieNode nextNode = curNode.child.get(i);

            if (nextNode != null) {

                displayContactsUtil(nextNode, prefix + i);

            }

        }

    }


    // Display suggestions after every character enter by

    // the user for a given string 'str'

    void displayContacts(String str) {

        TrieNode prevNode = root;


        // 'flag' denotes whether the string entered

        // so far is present in the Contact List


        String prefix = "";

        int len = str.length();


        // Display the contact List for string formed

        // after entering every character

        int i;

        for (i = 0; i < len; i++) {

            // 'str' stores the string entered so far

            prefix += str.charAt(i);


            // Get the last character entered

            char lastChar = prefix.charAt(i);


            // Find the Node corresponding to the last

            // character of 'str' which is pointed by

            // prevNode of the Trie

            TrieNode curNode = prevNode.child.get(lastChar);


            // If nothing found, then break the loop as

            // no more prefixes are going to be present.

            if (curNode == null) {

                System.out.println("nNo Results Found for " + prefix + "");

                i++;

                break;

            }


            // If present in trie then display all

            // the contacts with given prefix.

            System.out.println("nSuggestions based on " + prefix + " are");

            displayContactsUtil(curNode, prefix);


            // Change prevNode for next prefix

            prevNode = curNode;

        }


        for (; i < len; i++) {

            prefix += str.charAt(i);

            System.out.println("nNo Results Found for" + prefix + "");

        }

    }
}


// Driver code

class Main {

    public static void main(String args[]) {

        Trie trie = new Trie();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the passage/paragraph/line below on which you want to perform searching:\n");

        String contacts[] = scanner.nextLine().toLowerCase().split(" ");


        trie.insertIntoTrie(contacts);

        System.out.println("Please enter the word below you are looking for:-\n ");

        String query = scanner.nextLine().toLowerCase();


        // Note that the user will enter 'g' then 'e' so

        // first display all the strings with prefix as 'g'

        // and then all the strings with prefix as 'ge'

        trie.displayContacts(query);

    }
}
