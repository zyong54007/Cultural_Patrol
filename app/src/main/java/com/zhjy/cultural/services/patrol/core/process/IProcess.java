package com.zhjy.cultural.services.patrol.core.process;

/**
 * IProcess <br/>
 */
public interface IProcess<P> {

    void process(String key, P param);
}