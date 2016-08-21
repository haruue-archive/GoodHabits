package moe.haruue.goodhabits.model;

/**
 * Created by simonla on 2016/8/13.
 * Have a good day.
 */

public class Step {

    // 标题，将会直接传送给 Task
    public String title = "";
    // 这一步的内容，将会直接传送给 Task
    public String content = "";
    // 隶属的 Plan，将会直接传送给 Task
    public String planId = "";
    // 预留，图片 Url，将会直接传送给 Task
    public String imageUrl = "";
    // 默认的 note ，将会直接传送给 Task 的 note
    public String defaultNote = "";
    // 完成这一步需要的最少时间，以秒计
    public long minTime;
    // 完成这一步需要的最多时间，以秒计
    public long maxTime;
    // 注意： noLaterTime - noEarlyTime > minTime
    // 该活动不能早于这个时间（一天中的秒数），设置成 0 不考虑（非常不推荐）
    public long noEarlyTime = 0;
    // 该活动不能晚于这个时间（一天中的秒数），设置成 86400 不考虑（非常不推荐）
    public long noLaterTime = 86400;
    // 该活动必须是晴天
    public boolean requireSunnyDay;

}
