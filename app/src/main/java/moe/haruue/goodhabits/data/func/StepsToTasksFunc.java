package moe.haruue.goodhabits.data.func;

import java.util.List;

import moe.haruue.goodhabits.model.Step;
import moe.haruue.goodhabits.model.Task;
import rx.functions.Func1;

/**
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public class StepsToTasksFunc implements Func1<List<Step>, List<Task>> {
    @Override
    public List<Task> call(List<Step> steps) {
        return null;
    }
}
