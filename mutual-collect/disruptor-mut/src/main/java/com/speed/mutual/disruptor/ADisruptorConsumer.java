package com.speed.mutual.disruptor;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

/**
 * 消费者抽象类
 * @author joey
 * @param <T>
 */
public abstract class ADisruptorConsumer<T> implements EventHandler<ObjectEvent<T>>, WorkHandler<ObjectEvent<T>> {
    public ADisruptorConsumer() {
    }

    @Override
    public void onEvent(ObjectEvent<T> event, long sequence, boolean endOfBatch) throws Exception {
        this.onEvent(event);
    }

    @Override
    public void onEvent(ObjectEvent<T> event) throws Exception {
        this.consume(event.getObj());
    }

    /**
     * 消息处理
     * @param var1
     */
    public abstract void consume(T var1);
}
