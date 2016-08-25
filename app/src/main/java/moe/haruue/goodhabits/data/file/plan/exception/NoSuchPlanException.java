package moe.haruue.goodhabits.data.file.plan.exception;

/**
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public class NoSuchPlanException extends BasePlanFileStorageException {

    public NoSuchPlanException(String planId) {
        super("No such plan which id=" + planId + "in files", planId);
    }

}
