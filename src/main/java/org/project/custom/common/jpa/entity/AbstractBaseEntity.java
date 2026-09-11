package org.project.custom.common.jpa.entity;

import jakarta.persistence.*;
import org.project.custom.common.util.DateUtil;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;

@MappedSuperclass
@EntityListeners( AuditingEntityListener.class )
public abstract class AbstractBaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @CreatedBy
    @Column( name = "insert_user", length = 255, updatable = false)
    protected String insertUser;

    @Column( name = "insert_time", nullable = false, updatable = false)
    protected long insertTime;

    @LastModifiedBy
    @Column( name = "update_user", length = 255)
    protected String updateUser;

    @Column( name = "update_time", nullable = false)
    protected long updateTime;

    @PrePersist
    protected void onPersist() {
        long now = DateUtil.currentTime();
        if( this.insertTime == 0L){
            this.insertTime = now;
        }
        this.updateTime = this.insertTime;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updateTime = DateUtil.currentTime();
    }

    public String getInsertUser() {
        return insertUser;
    }

    public long getInsertTime() {
        return insertTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    @Transient
    public String getInsertTimeText(){
        return DateUtil.format(insertTime);
    }

    @Transient
    public String getUpdateTimeText(){
        return DateUtil.format(updateTime);
    }

    protected void applyInsertTime( long epochMilli){
        this.insertTime = epochMilli;
    }
}
