package moe.haruue.goodhabits.data.file.plan.exception;

/**
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public class BasePlanFileStorageException extends RuntimeException {

    public String planId;

    public BasePlanFileStorageException(String message, String planId) {
        super(message);
        this.planId = planId;
    }

    public String getPlanId() {
        return planId;
    }
}
