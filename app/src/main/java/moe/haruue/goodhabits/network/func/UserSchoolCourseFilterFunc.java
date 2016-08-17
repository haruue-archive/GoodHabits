package moe.haruue.goodhabits.network.func;


import java.util.ArrayList;
import java.util.List;

import moe.haruue.goodhabits.model.SchoolCourse;
import rx.functions.Func1;

public class UserSchoolCourseFilterFunc implements Func1<List<SchoolCourse>, List<SchoolCourse>> {
    private int week;

    public UserSchoolCourseFilterFunc(int week) {
        this.week = week;
    }

    @Override
    public List<SchoolCourse> call(List<SchoolCourse> courses) {

        ArrayList<SchoolCourse> list = new ArrayList<>();

        for (int i = 0; i < courses.size(); i++) {
            SchoolCourse c = courses.get(i);
            if (week == 0 || c.week.contains(week)) {
                list.add(c);
            }
        }
        return list;
    }
}
