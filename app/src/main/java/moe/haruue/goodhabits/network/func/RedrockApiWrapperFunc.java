package moe.haruue.goodhabits.network.func;

import moe.haruue.goodhabits.config.Const;
import moe.haruue.goodhabits.model.RedrockApiWrapper;
import moe.haruue.goodhabits.network.exception.RedrockApiException;
import rx.functions.Func1;

public class RedrockApiWrapperFunc<T> implements Func1<RedrockApiWrapper<T>, T> {

    @Override
    public T call(RedrockApiWrapper<T> wrapper) {
        if (wrapper.status != Const.REDROCK_API_STATUS_SUCCESS) {
            throw new RedrockApiException(wrapper.info);
        }
        return wrapper.data;
    }
}
