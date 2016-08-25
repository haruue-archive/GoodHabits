package moe.haruue.goodhabits.model;

import java.util.List;

/**
 * 每天时间固定性活动
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public class TaskPlan extends Plan<TaskCreator> {

    /**
     * 此 Plan 中拥有的 TaskCreator ，兼容性考虑
     */
    public List<TaskCreator> taskCreators = array;

    public TaskPlan() {
        super();
    }
}
