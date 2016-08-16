package moe.haruue.goodhabits.network.func;

import io.rx_cache.Reply;
import rx.functions.Func1;

public class CacheMapFunc<T> implements Func1<Reply<T>, T> {

    @Override
    public T call(Reply<T> reply) {
        return reply.getData();
    }
}
