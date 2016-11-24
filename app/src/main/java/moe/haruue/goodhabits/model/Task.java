package moe.haruue.goodhabits.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.Serializable;

/**
 * 任务，此 model 用于存储、显示，分析用户的安排<br>
 *     请注意，这是核心数据结构之一，对其进行的结构性更改将会影响到大量类<br>
 *     另外，在存入数据库之前，不要使用 HashSet 或者 HashMap 存储此结构，参考 {@link #hashCode()}
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public class Task implements Comparable<Task>, Serializable, Cloneable, Parcelable {

    public String title = "";
    public String content = "";
    public String type = "";
    public String plan = "";
    public int id;
    public String imageUrl = "";
    public long startTime;
    public long endTime;
    public boolean isFinish;
    public String note = "";

    public Task() {

    }

    protected Task(Parcel in) {
        title = in.readString();
        content = in.readString();
        type = in.readString();
        plan = in.readString();
        id = in.readInt();
        imageUrl = in.readString();
        startTime = in.readLong();
        endTime = in.readLong();
        isFinish = in.readByte() != 0;
        note = in.readString();
    }

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

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

    public static Task newMetaTask(String title,
                                   String content,
                                   String type,
                                   String imageUrl,
                                   String note) {
        Task task = new Task();
        task.title = title;
        task.content = content;
        task.type = type;
        task.imageUrl = imageUrl;
        task.note = note;
        return task;
    }

    @Override
    public String toString() {
        // 这个方法在数据库操作中进行 MD5 计算以保证唯一性时使用，请勿将可变量（defaultNote 和 id 加入这里）
        return
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

    @Override
    public int compareTo(@NonNull Task task) {
        if (startTime < task.startTime) return -1;
        else if (startTime == task.startTime) return 0;
        else return 1;
    }

    @Override
    public Task clone() {
        Task task;
        try {
            task = (Task) super.clone();
        } catch (Throwable e) {
            Log.w("Task", "clone", e);
            task = new Task();
        }
        task.title = title;
        task.content = content;
        task.type = type;
        task.plan = plan;
        task.imageUrl = imageUrl;
        task.startTime = startTime;
        task.endTime = endTime;
        task.note = note;
        return task;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(content);
        parcel.writeString(type);
        parcel.writeString(plan);
        parcel.writeInt(id);
        parcel.writeString(imageUrl);
        parcel.writeLong(startTime);
        parcel.writeLong(endTime);
        parcel.writeByte((byte) (isFinish ? 1 : 0));
        parcel.writeString(note);
    }
}
