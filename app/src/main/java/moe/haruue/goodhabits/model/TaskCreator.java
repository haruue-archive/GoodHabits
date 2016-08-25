package moe.haruue.goodhabits.model;

import java.io.Serializable;

/**
 * Task 创建器，包含元数据和时间相关信息
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public class TaskCreator implements Serializable {

    public Task metaTask;
    public long startTime = 0;
    public long timeLength = 0;
    public int count = 0;
    public int dayInterval = 1;

    public TaskCreator() {

    }

    public TaskCreator(Task metaTask,
                       long startTime,
                       long timeLength,
                       int count,
                       int dayInterval) {
        this.metaTask = metaTask;
        this.startTime = startTime;
        this.timeLength = timeLength;
        this.count = count;
        this.dayInterval = dayInterval;
    }

    public TaskCreator(String title,
                       String content,
                       String type,
                       String imageUrl,
                       String note,
                       long startTime,
                       long timeLength,
                       int count,
                       int dayInterval) {
        this(Task.newMetaTask(title, content, type, imageUrl, note),
                startTime,
                timeLength,
                count,
                dayInterval);
    }

}
