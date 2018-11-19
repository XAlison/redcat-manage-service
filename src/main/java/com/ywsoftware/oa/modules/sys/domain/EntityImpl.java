package com.ywsoftware.oa.modules.sys.domain;

import com.ywsoftware.oa.common.utils.UUIDGenerator;
import java.io.Serializable;
import java.util.Objects;

public abstract class EntityImpl implements Entity, Serializable {

    private String id;

    protected EntityImpl() {
        // 实体创建时生成 id
        id = UUIDGenerator.sequentialUUIDString();
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.id == null || getClass() != o.getClass()) {
            return false;
        }
        EntityImpl entity = (EntityImpl) o;
        return Objects.equals(id, entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
