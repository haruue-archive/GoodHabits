package moe.haruue.goodhabits.network.redrock;

import moe.haruue.goodhabits.config.Const;
import moe.haruue.goodhabits.model.ClassSchedule;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public interface RedrockApi {

    @FormUrlEncoded
    @POST(Const.REDROCK_CLASS_SCHEDULE_API)
    Observable<ClassSchedule> getClassSchedule(@Field("stuNum") String stuNum, @Field("week") int week);

}
