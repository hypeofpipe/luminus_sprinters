package com.hypeofpipe.sprinters.hypeengine.interfaces;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.utils.ImmutableArray;

/**
 * Created by Volodymyr on 10/14/2017.
 */

public interface Tickable {
    void start(ImmutableArray<Entity> entities);
    void pause(ImmutableArray<Entity> entities);
    void resume(ImmutableArray<Entity> entities);
    void update(ImmutableArray<Entity> entities);

}
