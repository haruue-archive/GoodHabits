package moe.haruue.goodhabits.model;

/**
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public class Task {

    public String title;
    public String content;
    public String type;
    public String plan;
    public int id;
    public String imageUrl;
    public long startTime;
    public long endTime;

    public static Task newEmptyTaskWithId(int id) {
        Task task = new Task();
        task.id = id;
        return task;
    }

    public static Task newEmptyTaskWithType(String type) {
        Task task = new Task();
        task.type = type;
        return task;
    }

    public static Task newEmptyTaskWithPlan(String plan) {
        Task task = new Task();
        task.plan = plan;
        return task;
    }

}
