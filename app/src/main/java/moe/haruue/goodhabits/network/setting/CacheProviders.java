package moe.haruue.goodhabits.network.setting;

import java.util.List;

import io.rx_cache.DynamicKey;
import io.rx_cache.EvictDynamicKey;
import io.rx_cache.Reply;
import moe.haruue.goodhabits.model.Course;
import rx.Observable;

public interface CacheProviders {

    Observable<Reply<List<Course>>> getCachedCourseList(Observable<List<Course>> oCourseList, DynamicKey key, EvictDynamicKey evictDynamicKey);
}
