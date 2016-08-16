package moe.haruue.goodhabits.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public class ClassSchedule {

    @JSONField(name = "status")
    public int status;
    @JSONField(name = "version")
    public String version;
    @JSONField(name = "term")
    public String term;
    @JSONField(name = "stuNum")
    public int stuNum;
    @JSONField(name = "cachedTimestamp")
    public long cachedTimestamp;
    @JSONField(name = "outOfDateTimestamp")
    public long outOfDateTimestamp;
    @JSONField(name = "nowWeek")
    public int nowWeek;

    @JSONField(name = "data")
    public List<ClassScheduleItem> data;

    public static class ClassScheduleItem {
        @JSONField(name = "hash_day")
        public int hashDay;
        @JSONField(name = "hash_lesson")
        public int hashLesson;
        @JSONField(name = "begin_lesson")
        public int beginLesson;
        @JSONField(name = "day")
        public String day;
        @JSONField(name = "lesson")
        public String lesson;
        @JSONField(name = "course")
        public String course;
        @JSONField(name = "teacher")
        public String teacher;
        @JSONField(name = "classroom")
        public String classroom;
        @JSONField(name = "rawWeek")
        public String rawWeek;
        @JSONField(name = "weekModel")
        public String weekModel;
        @JSONField(name = "weekBegin")
        public int weekBegin;
        @JSONField(name = "weekEnd")
        public int weekEnd;
        @JSONField(name = "type")
        public String type;
        @JSONField(name = "status")
        public String status;
        @JSONField(name = "period")
        public int period;
        @JSONField(name = "_id")
        public String id;
        @JSONField(name = "week")
        public List<Integer> week;
    }

}
