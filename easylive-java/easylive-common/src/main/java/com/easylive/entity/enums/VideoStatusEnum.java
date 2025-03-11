package com.easylive.entity.enums;

public enum VideoStatusEnum {
    STATUS0(0,"转码中"),
    STATUS1(0,"转码失败"),
    STATUS2(0,"待审核"),
    STATUS3(0,"审核成功"),
    STATUS4(0,"审核不通过");
    private Integer status;
    private String desc;
    VideoStatusEnum(Integer status,String desc){
        this.status=status;
        this.desc=desc;
    }
    public static VideoStatusEnum getByStatus(Integer status) {
        for (VideoStatusEnum item : VideoStatusEnum.values()) {
            if (item.getStatus().equals(status)) {
                return item;
            }
        }
        return null;
    }

    public Integer getStatus() {
        return status;
    }
    public String getDesc() {
        return desc;
    }
}
