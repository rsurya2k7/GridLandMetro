import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
public class Demo
{
  static int[] array;
  public static void main(String args[])
  {
      Scanner sc=new Scanner(System.in);
      long n=sc.nextLong();
      long m=sc.nextLong();
      int k=sc.nextInt();
      HashMap<Integer,ArrayList<Interval>> hMap=new HashMap<>();
      for(int i=0;i<k;i++)
      {
         int key=sc.nextInt();
         int start=sc.nextInt();
         int end=sc.nextInt();
         if(hMap.containsKey(key))
         {
             ArrayList<Interval> al=hMap.get(key);
             al.add(new Interval(start,end));
         }
         else
         {
             ArrayList<Interval> al=new ArrayList<>();
             al.add(new Interval(start,end));
             hMap.put(key,al);
         }
      }
      long count=0;
      for(Entry<Integer,ArrayList<Interval>> e:hMap.entrySet())
      {
          ArrayList<Interval> al=e.getValue();
          al=(ArrayList<Interval>) merge(al);
          Iterator i=al.iterator();
         
          while(i.hasNext())
          {
              Interval t=(Interval) i.next();
              count+=t.end-t.start+1;
          }
      }
      long result=n*m-count;
      System.out.println(result);
  }
  public static List<Interval> merge(List<Interval> interval) {
        List<Interval> result = new ArrayList<Interval>();
     
        if(interval==null||interval.size()==0)
            return result;
     
        Collections.sort(interval, new Comparator<Interval>(){
            public int compare(Interval i1, Interval i2){
                if(i1.start!=i2.start)
                    return i1.start-i2.start;
                else
                    return i1.end-i2.end;
            }
        });
     
        Interval previous = interval.get(0);
        for(int i=0; i<interval.size(); i++){
            Interval current = interval.get(i);
            if(current.start>previous.end){
                result.add(previous);
                previous = current;
            }else{
                Interval merged = new Interval(previous.start, Math.max(previous.end, current.end));
                previous = merged;
            }
        }
        result.add(previous);
     
        return result;
    }
}
class Interval{
       int start=0;
       int end=0;
       Interval(int start,int end)
       {
           this.start=start;
           this.end=end;
       }
}