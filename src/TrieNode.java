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
class Trie {

    TrieNode root;//declaring the  root for trieNode
    int flag = 0;

    // Insert the word  into the Trie

    public void insert(String s) {

        int len = s.length();//calculating the length of each word


        // 'itr' is used to iterate the Trie Nodes

        TrieNode itr = root;

        for (int i = 0; i < len; i++) {

            // Check if the s[i] is already present in Trie

            TrieNode nextNode = itr.child.get(s.charAt(i));//getting the value of child node on basis of character key

            if (nextNode == null) {

                // If not found then create a new TrieNode

                nextNode = new TrieNode();


                // Insert into the HashMap

                itr.child.put(s.charAt(i), nextNode);

            }


            // Move the iterator('itr') ,to point to next Trie Node

            itr = nextNode;


            // If its the last character of the string 's'

            // then mark 'isEnd' as true

            if (i == len - 1)

                itr.isEnd = true;

        }

    }
    // Insert all the data into the Trie

    public void insertDataIntoTrie(String data[]) {

        root = new TrieNode();//initiallizing the root

        int n = data.length;//calculating total length of data in terms of words

        for (int i = 0; i < n; i++) {

            insert(data[i]);//calling the insert function defined above

        }

    }

    //this function tells us if the searched word is found or not

    void isFound(String str) {

        TrieNode prevNode = root;

        int len = str.length();
        String text = "";//a string to find the original word containing the text

        int i;

        for (i = 0; i < len; i++) {

            text += Character.toString(str.charAt(i));
            // Get the last character entered to find node followed by it
            char lastChar = str.charAt(i);
            // Find the Node corresponding to the last character of 'str' which is pointed by prevNode of the Trie

            TrieNode curNode = prevNode.child.get(lastChar);

            // If nothing found, then break the loop and set flag as 1

            if (curNode == null) {

                System.out.println("NO!-->  YOUR TEXT IS NOT FOUND. ");

                i++;
                flag = 1;
                break;

            }
            // Change prevNode for next prefix
            prevNode = curNode;
            int a = len - 1;
            //a loop in order to find the complete word containing youy text
            if (i == a) {
                for (char j = 'a'; j <= 'z'; j++) {//checking the next node according to key character

                    TrieNode nextNode = prevNode.child.get(j);
                    //if  node is not null add the character to complete the word

                    if (nextNode != null) {
                        text += Character.toString(j);
                        j = 96;//again start from character'a'
                        prevNode = nextNode;//assign previous node the value of nextnode
                    }
                }
            }
        }
        //if flag is still 0
        if (flag == 0)
            System.out.println("YES!--> YOUR TEXT IS FOUND IN WORD --> " + text);
        }
    }

// Main code

class Main {

    public static void main(String args[]) {

        Trie trie = new Trie();//object of Trie class

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the passage/paragraph/line below on which you want to perform searching:\n");

        String data[] = scanner.nextLine().toLowerCase().split(" ");//string array for data


        trie.insertDataIntoTrie(data);//calling insertDataIntoTrie function

        System.out.println("Please enter the word below you are looking for:-\n ");

        String word = scanner.nextLine().toLowerCase();//taking the query word/text

        trie.isFound(word);//calling the isFound function

    }
}
