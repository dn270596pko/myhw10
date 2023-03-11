package task3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class WordFrequencyCounter {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\dp270\\IdeaProjects\\myhw10\\src\\task3\\words.txt"; // шлях до файлу
        PriorityQueue<Map.Entry<String, Integer>> wordFrequency = countWordFrequency(filePath);
        while (!wordFrequency.isEmpty()) {
            Map.Entry<String, Integer> entry = wordFrequency.poll();
            String word = entry.getKey();
            int frequency = entry.getValue();
            System.out.println(word + " " + frequency);
        }
    }

    public static PriorityQueue<Map.Entry<String, Integer>> countWordFrequency(String filePath) {
        Map<String, Integer> wordFrequencyMap = new HashMap<>();
        try {
            Scanner scanner = new Scanner(new File(filePath));
            while (scanner.hasNext()) {
                String word = scanner.next();

                word = word.replaceAll("[\\n\\r.,;:!?(){}\\[\\]]", "");

                word = word.toLowerCase();
                if (!word.isEmpty()) {
                    int count = wordFrequencyMap.getOrDefault(word, 0);
                    wordFrequencyMap.put(word, count + 1);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        PriorityQueue<Map.Entry<String, Integer>> wordFrequency = new PriorityQueue<>(
                Comparator.comparing(Map.Entry<String, Integer>::getValue).reversed()
        );
        for (Map.Entry<String, Integer> entry : wordFrequencyMap.entrySet()) {
            wordFrequency.add(Map.entry(entry.getKey(), entry.getValue()));
        }
        return wordFrequency;
    }
}
