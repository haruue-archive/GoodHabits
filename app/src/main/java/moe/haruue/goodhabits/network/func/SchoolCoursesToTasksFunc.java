package moe.haruue.goodhabits.network.func;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import moe.haruue.goodhabits.App;
import moe.haruue.goodhabits.config.Const;
import moe.haruue.goodhabits.model.SchoolCourse;
import moe.haruue.goodhabits.model.Task;
import moe.haruue.goodhabits.util.SchoolCalendar;
import moe.haruue.goodhabits.util.TimeUtils;
import rx.functions.Func1;

import static moe.haruue.goodhabits.util.TimeUtils.getTimeStampOf;
import static moe.haruue.goodhabits.util.TimeUtils.timeStampToDayStartCCT;

/**
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public class SchoolCoursesToTasksFunc implements Func1<List<SchoolCourse>, List<Task>> {

    public SchoolCoursesToTasksFunc(int nowWeek) {
        updateFirstDay(nowWeek);
    }

    @Override
    public List<Task> call(List<SchoolCourse> courses) {
        ArrayList<Task> tasks = new ArrayList<>(0);
        for (SchoolCourse c: courses) {
            for (int w: c.week) {
                tasks.add(courseInWeekToTask(c, w));
            }
        }
        return tasks;
    }

    private Task courseInWeekToTask(SchoolCourse course, int week) {
        Task task = new Task();
        task.title = course.course;
        task.content = course.lesson + "\n" + course.classroom;
        task.id = 0;
        task.type = Const.TASK_TYPE_SCHOOL_COURSE;
        task.startTime = timeStampSchoolCourseStart(week, course.hash_day, course.begin_lesson) + 86400;
        task.endTime = timeStampSchoolCourseEnd(task.startTime);
        task.plan = Const.TASK_TYPE_SCHOOL_COURSE;
        task.isFinish = task.endTime < TimeUtils.getTimeStampOf(TimeUtils.getDayStartOf(new GregorianCalendar()));
        return task;
    }

    private long timeStampSchoolCourseStart(int week, int day, int beginLesson) {
        long timeSchoolCourseBegin;
        switch (beginLesson) {
            case 1:
                timeSchoolCourseBegin = 28800; // 8:00
                break;
            case 3:
                timeSchoolCourseBegin = 36300; // 10:05
                break;
            case 5:
                timeSchoolCourseBegin = 50400; // 14:00
                break;
            case 7:
                timeSchoolCourseBegin = 57900; // 16:05
                break;
            case 9:
                timeSchoolCourseBegin = 68400; // 19:00
                break;
            case 11:
                timeSchoolCourseBegin = 75000; // 20:50
                break;
            default:
                timeSchoolCourseBegin = 0; // 什么鬼课嘛，逃了算了
                break;
        }
        return timeStampOf(week, day, timeSchoolCourseBegin);

    }

    private long timeStampSchoolCourseEnd(long timeStampSchoolCourseStart) {
        return timeStampSchoolCourseStart + 6000; // 1:40
    }

    private long timeStampOf(int week, int day, long time) {
        return timeStampToDayStartCCT(getTimeStampOf(new SchoolCalendar(week, day).getCalendar())) + time;
    }

    /**
     * 取学期第一天并存储起来
     * @param nowWeek
     */
    private void updateFirstDay(int nowWeek) {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.DATE, -((nowWeek - 1) * 7 + (now.get(Calendar.DAY_OF_WEEK) + 5) % 7));
        App.getCommonSharedPreferences().edit().putLong(Const.KEY_FIRST_SCHOOL_DAY, now.getTimeInMillis()).apply();
    }

}
