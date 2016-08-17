package moe.haruue.goodhabits.network.func;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import moe.haruue.goodhabits.App;
import moe.haruue.goodhabits.config.Const;
import moe.haruue.goodhabits.model.SchoolCourse;
import moe.haruue.goodhabits.model.Task;
import moe.haruue.goodhabits.util.SchoolCalendar;
import rx.functions.Func1;

/**
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public class SchoolCourseToTasksFunc implements Func1<SchoolCourse, List<Task>> {

    public SchoolCourseToTasksFunc(int nowWeek) {
        updateFirstDay(nowWeek);
    }

    @Override
    public List<Task> call(SchoolCourse course) {
        ArrayList<Task> tasks = new ArrayList<>(0);
        for (int w: course.week) {
            tasks.add(courseInWeekToTask(course, w));
        }
        return tasks;
    }

    private Task courseInWeekToTask(SchoolCourse course, int week) {
        Task task = new Task();
        task.title = "学校课程：" + course.course;
        task.content = course.day + " " + course.lesson + " " + course.classroom;
        task.id = 0;
        task.type = Const.TASK_TYPE_SCHOOL_COURSE;
        task.startTime = timeStampSchoolCourseStart(week, course.hash_day, course.begin_lesson);
        task.endTime = timeStampSchoolCourseEnd(task.startTime);
        task.plan = Const.TASK_TYPE_SCHOOL_COURSE;
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
     * 取北京时间当天 0 点的时间戳
     * @param timeStamp 北京时间某一天任意时间的时间戳
     * @return 当天 0 点的时间戳
     */
    private long timeStampToDayStartCCT(long timeStamp) {
        timeStamp += 28800;
        timeStamp = timeStamp / 86400 * 86400;
        return timeStamp - 28800;
    }

    /**
     * 取以秒为单位的时间戳<br>
     *     <del>让我用以毫秒为单位的时间戳我是拒绝的</del>
     * @param calendar 需要取值的 {@link Calendar} 对象
     * @return 以秒为单位的时间戳
     */
    private long getTimeStampOf(Calendar calendar) {
        return calendar.getTimeInMillis() / 1000;
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
