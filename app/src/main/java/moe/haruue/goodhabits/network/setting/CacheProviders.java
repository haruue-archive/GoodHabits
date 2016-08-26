package moe.haruue.goodhabits.network.setting;

import java.util.List;

import io.rx_cache.DynamicKey;
import io.rx_cache.EvictDynamicKey;
import io.rx_cache.Reply;
import moe.haruue.goodhabits.model.SchoolCourse;
import rx.Observable;

public interface CacheProviders {

    Observable<Reply<List<SchoolCourse>>> getCachedSchoolCourseList(Observable<List<SchoolCourse>> oCourseList, DynamicKey key, EvictDynamicKey evictDynamicKey);
}
