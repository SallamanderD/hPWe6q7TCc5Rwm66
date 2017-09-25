package ua.nure.dorotenko.Practice6.part2;

import java.util.*;

public class Part2 implements Iterable<String> {

    List<String> circle;
    int index;
    public Part2(List<String> str, int k) {
        this.circle = str;
        this.index = k;
    }

    public static void main(String[] args) {
        System.out.println("~~~ ArrayList");
        List<String> str = new ArrayList<String>(Arrays.asList(new String[]{"A", "B", "C", "D", "E", "F", "G"}));
        Part2 p2 = new Part2(str, 2);
        p2.loopRemove();
        System.out.println("~~~ LinkedList");
        str = new LinkedList<String>(Arrays.asList(new String[]{"A", "B", "C", "D", "E", "F", "G"}));
        p2 = new Part2(str, 2);
        p2.loopRemove();
    }

    public void loopRemove() {
        long current = System.currentTimeMillis();
        Iterator<String> iterator = this.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
            System.out.println(this.circle);
        }
        System.out.println(System.currentTimeMillis() - current);
    }

    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>() {
            int cursor = -1;

            @Override
            public boolean hasNext() {
                if (circle.size() != 1)
                    return true;
                return false;
            }

            @Override
            public String next() {
                if (cursor + index >= circle.size()) {
                    cursor = cursor + index - circle.size();
                } else {
                    cursor += index;
                }
                return circle.get(cursor);
            }

            @Override
            public void remove() {
                circle.remove(cursor);
                cursor = cursor - 1;
            }
        };
    }
}
