package tasks.first;

import java.util.ArrayDeque;
import java.util.HashSet;

public class FirstTaskSolution implements FirstTask {
    @Override
    public String breadthFirst(boolean[][] adjacencyMatrix, int startIndex) {
        HashSet<Integer> check = new HashSet<>();
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        String str = new String();

        check.add(startIndex);
        queue.offerLast(startIndex);
        while (check.size() != adjacencyMatrix.length) {
            int parent = queue.peekFirst();

            for (int i = 0; i < adjacencyMatrix.length; i++) {
                if ((adjacencyMatrix[parent][i]) && (!check.contains(i))) {
                    check.add(i);
                    queue.offerLast(i);
                }
            }
            str = str + queue.pollFirst() + ", ";

        }
        str = str + queue.pollFirst();
        return str;
    }


    @Override
    public Boolean validateBrackets(String s) {
        String openBracket = "{([";
        String closeBracket = "})]";
        ArrayDeque<String> queue = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            String str = String.valueOf(s.charAt(i));
            if (closeBracket.contains(str)) {
                if (queue.isEmpty())
                    return false;
                if (openBracket.indexOf(queue.pollLast()) != closeBracket.indexOf(str)) {
                    return false;
                }
            }
            if (openBracket.contains(str)) {
                queue.addLast(str);
            }

        }
        return queue.isEmpty();
    }


    @Override
    public Long polishCalculation(String s) throws IllegalArgumentException {
        ArrayDeque<Long> queue = new ArrayDeque();
        String[] mass = s.split(" ");
        int temp = 0;
        Long likeCount = null;
        String sign = "+-*/";
        while (!sign.contains(mass[temp])) {
            queue.push(Long.valueOf(mass[temp]));
            temp++;
        }
        for (int i = queue.size(); i < mass.length; i++) {
            likeCount = queue.pop();
            switch (mass[i]) {
                case "+":
                    likeCount += queue.pop();
                    break;
                case "/":
                    likeCount = likeCount / queue.pop();
                    break;
                case "*":
                    likeCount *= queue.pop();
                    break;
                case "-":
                    likeCount = likeCount - queue.pop();
                    break;
                default:
                    throw new IllegalArgumentException();
            }
            queue.push(likeCount);
        }
        return likeCount;

    }
}
