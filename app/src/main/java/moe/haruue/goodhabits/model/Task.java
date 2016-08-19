package moe.haruue.goodhabits.model;

/**
 * 任务，此 model 用于存储、显示，分析用户的安排<br>
 *     请注意，这是核心数据结构之一，对其进行的结构性更改将会影响到大量类<br>
 *     另外，在存入数据库之前，不要使用 HashSet 或者 HashMap 存储此结构，参考 {@link #hashCode()}
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public class Task {

    public String title = "";
    public String content = "";
    public String type = "";
    public String plan = "";
    public int id;
    public String imageUrl = "";
    public long startTime;
    public long endTime;
    public boolean isFinish;

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

    public static Task newEmptyTaskWithStartTimeAndEndTime(long startTimeStamp, long endTimeStamp) {
        Task task = new Task();
        task.startTime = startTimeStamp;
        task.endTime = endTimeStamp;
        return task;
    }

    @Override
    public String toString() {
        return
                "id: " + id + "\t" +
                "title: " + title + "\n" +
                "content: " + content + "\n" +
                "type: " + type + "\t" +
                "plan: " + plan + "\t" +
                "imageUrl: " + imageUrl + "\n" +
                "startTime: " + startTime + "\t" +
                "endTime: " + endTime + "\n" +
                "============================================" + "\n";
    }

    /**
     * 判等
     * 只有在存入数据库再重新读取出来时，这个方法才是可信任的，因为只有这样才能使 id 具有唯一性
     * @param obj 需要判等的对象
     * @return 结果
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof Task && ((Task) obj).id == this.id;
    }

    /**
     * 取 hashCode ，主要用于 HashSet 或者 HashMap 的唯一性确认
     * 只有在存入数据库再重新读取出来时，这个方法才是可信任的，因为只有这样才能使 id 具有唯一性
     * 因此，在存入数据库之前，不要使用 HashSet 或者 HashMap 存储此结构
     * @return hashCode
     */
    @Override
    public int hashCode() {
        return "TASK_OBJ_d5f4c24c7".hashCode() + id;
    }
}
