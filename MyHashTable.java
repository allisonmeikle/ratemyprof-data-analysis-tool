package finalproject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Iterator;

public class MyHashTable<K,V> implements Iterable<MyPair<K,V>>{
     // num of entries to the table
     private int size;
     // num of buckets
     private int capacity = 16;
     // load factor needed to check for rehashing
     private static final double MAX_LOAD_FACTOR = 0.75;
     // ArrayList of buckets. Each bucket is a LinkedList of HashPair
     private ArrayList<LinkedList<MyPair<K,V>>> buckets;


     // constructors
     public MyHashTable() {
          this.capacity = 16;
          this.buckets = new ArrayList<LinkedList<MyPair<K,V>>>(this.capacity);
          for (int i = 0; i < this.capacity; i++){
               this.buckets.add(i, new LinkedList<MyPair<K,V>>());
          }
          this.size = 0;
     }

     public MyHashTable(int initialCapacity) {
          this.capacity = initialCapacity;
          this.buckets = new ArrayList<LinkedList<MyPair<K,V>>>(this.capacity);
          for (int i = 0; i < this.capacity; i++){
               this.buckets.add(i, new LinkedList<MyPair<K,V>>());
          }
          this.size = 0;
     }

     public int size() {
          return this.size;
     }

     public boolean isEmpty() {
          return this.size == 0;
     }

     public int numBuckets() {
          return this.capacity;
     }

     /**
      * Returns the buckets variable. Useful for testing  purposes.
      */
     public ArrayList<LinkedList< MyPair<K,V> > > getBuckets(){
          return this.buckets;
     }

     /**
      * Given a key, return the bucket position for the key.
      */
     public int hashFunction(K key) {
          int hashValue = Math.abs(key.hashCode())%this.capacity;
          return hashValue;
     }

     /**
      * Takes a key and a value as input and adds the corresponding HashPair
      * to this HashTable. Expected average run time  O(1)
      */
     public V put(K key, V value) {
          //  ADD YOUR CODE BELOW HERE
          this.size++;
          if (((double)this.size/this.buckets.size()) > this.MAX_LOAD_FACTOR){
               this.rehash();
          }
          LinkedList<MyPair<K,V>> bucket = this.buckets.get(this.hashFunction(key));

          // checks if key already exists in the hash
          for (MyPair<K,V> pair : bucket) {
               if (pair.getKey().equals(key)) {
                    V v = pair.getValue();
                    pair.setValue(value);
                    return v;
               }
          }
          bucket.add(new MyPair<K,V>(key,value));
          return null;
     }

     /**
      * Get the value corresponding to key. Expected average runtime O(1)
      */
     public V get(K key) {
          //ADD YOUR CODE BELOW HERE
          for (MyPair<K,V> pair : this.buckets.get(this.hashFunction(key))){
               if (pair.getKey().equals(key)){
                    return pair.getValue();
               }
          }
          return null;
          //ADD YOUR CODE ABOVE HERE
     }

     /**
      * Remove the HashPair corresponding to key . Expected average runtime O(1)
      */
     public V remove(K key) {
          //ADD YOUR CODE BELOW HERE
          for (MyPair<K,V> pair : this.buckets.get(this.hashFunction(key))){
               if (pair.getKey().equals(key)){
                    V value = pair.getValue();
                    this.buckets.get(this.hashFunction(key)).remove(pair);
                    return value;
               }
          }
          return null;
          //ADD YOUR CODE ABOVE HERE
     }


     /**
      * Method to double the size of the hashtable if load factor increases
      * beyond MAX_LOAD_FACTOR.
      * Made public for ease of testing.
      * Expected average runtime is O(m), where m is the number of buckets
      */
     public void rehash() {
          this.capacity *= 2;
          ArrayList<LinkedList<MyPair<K,V>>> replacement = new ArrayList<LinkedList<MyPair<K,V>>>(this.capacity);
          for (int i = 0; i < this.capacity; i++){
               replacement.add(i, new LinkedList<MyPair<K,V>>());
          }

          for (LinkedList<MyPair<K,V>> ll : this.buckets){
               if (ll.size() != 0){
                    for (MyPair<K,V> pair : ll){
                         int index = this.hashFunction(pair.getKey());
                         replacement.get(index).add(pair);
                    }
               }
          }
          this.buckets = replacement;
     }


     /**
      * Return a list of all the keys present in this hashtable.
      * Expected average runtime is O(m), where m is the number of buckets
      */

     public ArrayList<K> getKeySet() {
          //ADD YOUR CODE BELOW HERE
          ArrayList<K> list = new ArrayList<K>();
          for (LinkedList<MyPair<K,V>> ll : this.buckets){
               for (MyPair<K,V> pair : ll){
                    list.add(pair.getKey());
               }
          }

          return list;
          //ADD YOUR CODE ABOVE HERE
     }

     /**
      * Returns an ArrayList of unique values present in this hashtable.
      * Expected average runtime is O(m) where m is the number of buckets
      */
     public ArrayList<V> getValueSet() {
          //ADD CODE BELOW HERE
          ArrayList<V> list = new ArrayList<V>();
          for (LinkedList<MyPair<K,V>> ll : this.buckets){
               for (MyPair<K,V> pair : ll){
                    if (!list.contains(pair.getValue())){
                         list.add(pair.getValue());
                    }
               }
          }
          return list;
          //ADD CODE ABOVE HERE
     }


     /**
      * Returns an ArrayList of all the key-value pairs present in this hashtable.
      * Expected average runtime is O(m) where m is the number of buckets
      */
     public ArrayList<MyPair<K, V>> getEntries() {
          //ADD CODE BELOW HERE
          ArrayList<MyPair<K, V>> list = new ArrayList<MyPair<K, V>>();

          for (LinkedList<MyPair<K,V>> ll : this.buckets){
               list.addAll(ll);
          }
          return list;
          //ADD CODE ABOVE HERE
     }

     @Override
     public MyHashIterator iterator() {
          return new MyHashIterator();
     }

     private class MyHashIterator implements Iterator<MyPair<K,V>> {
          MyPair<K,V> cur;
          ArrayList<MyPair<K,V>> list = new ArrayList<MyPair<K,V>>(0);

          private MyHashIterator() {
               //ADD YOUR CODE BELOW HERE
               list = getEntries();
               cur = list.get(0);
               //ADD YOUR CODE ABOVE HERE
          }

          @Override
          public boolean hasNext() {
               //ADD YOUR CODE BELOW HERE
               return (cur != null);
               //ADD YOUR CODE ABOVE HERE
          }

          @Override
          public MyPair<K,V> next() {
               //ADD YOUR CODE BELOW HERE

               MyPair<K,V> temp = cur;
               if (list.indexOf(cur) < list.size()-1){
                    cur = list.get(list.indexOf(cur)+1);
               } else if (list.indexOf(cur) == list.size()-1){
                    cur = null;
               }
               return temp;
               //ADD YOUR CODE ABOVE HERE
          }
     }
}
