package duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class DataStore {
    final private static String PATH = "data/tasks.csv";

    public static void saveData(TaskList tasks){
        File csvFile = new File(PATH);
        try {
            if (!csvFile.isFile()) {
                csvFile.createNewFile();
            }
            FileWriter csvWriter = new FileWriter(PATH);

            for (int i = 1; i <= tasks.getLength(); i++) {
                Task task = tasks.getTask(i);
                String[] details = task.getDetails();

                csvWriter.write(details[0]);
                csvWriter.write(',');
                csvWriter.write(details[1]);
                csvWriter.write(',');
                csvWriter.write(details[2]);
                csvWriter.write(',');
                csvWriter.write(details[3]);
                csvWriter.write("\n");
            }
            csvWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static TaskList loadData() {
        TaskList tasks = new TaskList();
        File csvFile = new File(PATH);
        try {
            if (csvFile.isFile()) {
                BufferedReader csvReader = new BufferedReader(new FileReader(PATH));
                String row = csvReader.readLine();
                while (row != null) {
                    String[] inputs = row.split(",");
                    tasks.load(parseTaskType(inputs[0]), Arrays.copyOfRange(inputs, 1, 4));
                    row = csvReader.readLine();
                }
                csvReader.close();
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return tasks;
    }

    public static TaskType parseTaskType(String taskType) {
        taskType = taskType.toLowerCase();
        if (taskType.equals("todo")) {
            return TaskType.TODO;
        } else if (taskType.equals("event")){
            return TaskType.EVENT;
        } else if (taskType.equals("deadline")){
            return TaskType.DEADLINE;
        }
        return null;
    }
}
