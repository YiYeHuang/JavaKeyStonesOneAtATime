package leetcode.heap.TimeBasedKeyValueStore;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class TimeBasedKeyValyeStorePQ {

  private Map<String, PriorityQueue<Entry>> map;

  public TimeBasedKeyValyeStorePQ() {
    map = new HashMap<>();
  }

  public void set(String key, String value, int timestamp) {
    PriorityQueue<Entry> pq;
    if(!map.containsKey(key)){
      pq = new PriorityQueue<>(new Comparator<Entry>(){
        @Override
        public int compare(Entry e1, Entry e2){
          return e2.timeStamp - e1.timeStamp;
        }
      });
      map.put(key, pq);
    }else{
      pq = map.get(key);
    }
    pq.add(new Entry(value, timestamp));

  }

  public String get(String key, int timestamp) {
    String result = "";
    if(map.containsKey(key)){
      PriorityQueue<Entry> pq = map.get(key);
      Deque<Entry> dq = new ArrayDeque<>();

      if(pq.peek().timeStamp == timestamp) {
        return pq.peek().value;
      }

      while(!pq.isEmpty() && pq.peek().timeStamp > timestamp) {
        dq.add(pq.poll());
      }

      if(!pq.isEmpty()) {
        result = pq.peek().value;
      }

      while(!dq.isEmpty()) {
        pq.add(dq.remove());
      }
    }
    return result;
  }

  class Entry{
    String value;
    int timeStamp;

    Entry(String value, int timeStamp){
      this.value = value;
      this.timeStamp = timeStamp;
    }
  }

}
