package moe.haruue.goodhabits.network.func;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import moe.haruue.goodhabits.App;
import moe.haruue.goodhabits.config.Const;
import moe.haruue.goodhabits.model.Course;
import moe.haruue.goodhabits.model.Task;
import moe.haruue.goodhabits.util.SchoolCalendar;
import rx.functions.Func1;

/**
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public class CourseToTasksFunc implements Func1<Course, List<Task>> {

    public CourseToTasksFunc(int nowWeek) {
        updateFirstDay(nowWeek);
    }

    @Override
    public List<Task> call(Course course) {
        ArrayList<Task> tasks = new ArrayList<>(0);
        for (int w: course.week) {
            tasks.add(courseInWeekToTask(course, w));
        }
        return tasks;
    }

    private Task courseInWeekToTask(Course course, int week) {
        Task task = new Task();
        task.title = "学校课程：" + course.course;
        task.content = course.day + " " + course.lesson + " " + course.classroom;
        task.id = 0;
        task.type = Const.TASK_TYPE_COURSE;
        task.startTime = timeStampCourseStart(week, course.hash_day, course.begin_lesson);
        task.endTime = timeStampCourseEnd(task.startTime);
        task.plan = Const.TASK_TYPE_COURSE;
        return task;
    }

    private long timeStampCourseStart(int week, int day, int beginLesson) {
        long timeCourseBegin;
        switch (beginLesson) {
            case 1:
                timeCourseBegin = 28800; // 8:00
                break;
            case 3:
                timeCourseBegin = 36300; // 10:05
                break;
            case 5:
                timeCourseBegin = 50400; // 14:00
                break;
            case 7:
                timeCourseBegin = 57900; // 16:05
                break;
            case 9:
                timeCourseBegin = 68400; // 19:00
                break;
            case 11:
                timeCourseBegin = 75000; // 20:50
                break;
            default:
                timeCourseBegin = 0; // 什么鬼课嘛，逃了算了
                break;
        }
        return timeStampOf(week, day, timeCourseBegin);

    }

    private long timeStampCourseEnd(long timeStampCourseStart) {
        return timeStampCourseStart + 6000; // 1:40
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
