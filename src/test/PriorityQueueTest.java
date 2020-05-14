package test;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueueTest {
    static class Pojo {
        int cost;
        String name;

        public int getCost() {
            return cost;
        }

        public void setCost(int cost) {
            this.cost = cost;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
        public Pojo(int cost, String name){
            this.cost = cost;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Pojo{" +
                    "cost=" + cost +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        PriorityQueue<Pojo> queue = new PriorityQueue<>(new Comparator<Pojo>() {
            @Override
            public int compare(Pojo o1, Pojo o2) {
                if (o1.cost < o2.cost) {
                    return -1;
                } else if (o1.cost > o2.cost) {
                    return 1;
                }
                return 0;
            }
        });

        for(int i=0;i <4; i++) {
            for(int j=0 ;j<4;j++) {
                queue.add(new Pojo(i+j,""+i+" "+j));
            }
        }
        System.out.println(queue);
        queue.poll();
        System.out.println(queue);
    }
}
