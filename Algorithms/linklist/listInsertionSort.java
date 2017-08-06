/**
 * Book: Data Structures and Algorithms in Java, by Robert LaFore
 * Chapter 5:
 *  listInsertionSort.java
 *  demonstrates sorted list used for sorting
 *  to compile this code: javac listInsertionSort.java
 *  to run this program:  java ListInsertionSortApp
 */
class Link {
    public long dData;                  // data item
    public Link next;                   // next link in list

    public Link(long d) {               // constructor
        dData = d;
    }

    public void displayLink() {         // display this link
        System.out.print(dData + " ");
    }
} // end class Link

class SortedList {
    private Link first;                 // ref to first item on list

    public SortedList() {               // constructor (no args)
        first = null;                   // initialize list
    }

    public SortedList(Link[] linkArr) { // constructor (array as argument)
        first = null;                   // initialize list
                                        // copy array to list
        for (int j=0; j<linkArr.length; j++)
            insert(linkArr[j]);
    }

    public void insert(Link k) {        // insert (in order)
        Link previous = null;           // start at first
        Link current = first;
                                        // until end of list,
        while(current != null && k.dData > current.dData) {
                                        // or key > current,
            previous = current;
            current = current.next;     // go to next item
        }
        if (previous==null)             // at beginning of list
            first = k;                  // first --> k
        else                            // not at beginning
            previous.next = k;          // old prev --> k
        k.next = current;               // k --> old currnt
    }

    public Link remove() {              // return & delete first link
                                        // (assumes non-empty list)
        Link temp = first;              // save first
        first = first.next;             // delete first
        return temp;                    // return value
    }
} // end class SortedList

class ListInsertionSortApp {
   public static void main(String[] args) {
        int size = 10;
                                         // create array of links
        Link[] linkArray = new Link[size]; 

        for (int j=0; j<size; j++) {     // fill array with links random number
            int n = (int)(java.lang.Math.random()*99);
            Link newLink = new Link(n);  // make link
            linkArray[j] = newLink;      // put in array
        }
                                         // display array contents
        System.out.print("Unsorted array: ");
        for (int j=0; j<size; j++)
            System.out.print( linkArray[j].dData + " " );
        System.out.println("");

                                         // create new list
        SortedList theSortedList = new SortedList(linkArray);

        for(int j=0; j<size; j++)        // links from list to array
            linkArray[j] = theSortedList.remove();

                                         // display array contents
        System.out.print("Sorted Array: ");
        for (int j=0; j<size; j++)
            System.out.print(linkArray[j].dData + " ");
        System.out.println("");
    }
}