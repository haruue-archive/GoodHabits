package moe.haruue.goodhabits.data.file.plan.exception;

/**
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public class PlanIdConflictException extends BasePlanFileStorageException {


    public PlanIdConflictException(String planId) {
        super("Plan id: " + planId + " is exist.", planId);
    }

}
