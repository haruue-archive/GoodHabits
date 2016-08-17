package moe.haruue.goodhabits.network.func;


import java.util.ArrayList;
import java.util.List;

import moe.haruue.goodhabits.model.Course;
import rx.functions.Func1;

public class UserCourseFilterFunc implements Func1<List<Course>, List<Course>> {
    private int week;

    public UserCourseFilterFunc(int week) {
        this.week = week;
    }

    @Override
    public List<Course> call(List<Course> courses) {

        ArrayList<Course> list = new ArrayList<>();

        for (int i = 0; i < courses.size(); i++) {
            Course c = courses.get(i);
            if (week == 0 || c.week.contains(week)) {
                list.add(c);
            }
        }
        return list;
    }
}
