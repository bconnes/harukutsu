package com.brandon.harukutsu.model;


import org.hibernate.annotations.Type;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

/**
 * @author bconnes
 * @since 11/17/2019
 */
@MappedSuperclass
public class EntityWithUUID
{
    @Id
    @Type(type="pg-uuid")
    private UUID id;

    public EntityWithUUID()
    {
        this.id = UUID.randomUUID();
    }
}
