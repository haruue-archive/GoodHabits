package moe.haruue.goodhabits.network.redrock;

import moe.haruue.goodhabits.config.Const;
import moe.haruue.goodhabits.model.Course;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

/**
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public interface RedrockApi {

    @FormUrlEncoded
    @Headers("API_APP: android")
    @POST(Const.REDROCK_CLASS_SCHEDULE_API)
    Observable<Course.CourseWrapper> getCourse(@Field("stuNum") String stuNum,
                                               @Field("idNum") String idNum,
                                               @Field("week") String week);

}
