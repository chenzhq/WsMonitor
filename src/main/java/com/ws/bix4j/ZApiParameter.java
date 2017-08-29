package com.ws.bix4j;

/**
 * @Date 2017/4/4
 * @Author chen
 */
public class ZApiParameter {
    public static final int ZABBIX_CLIENT_DEFAULT_PORT = 10050;

    public static final int ITEM_DELAY_DEFAULT = 60;

    private ZApiParameter() {
    }

/**
 * 主机组参数
  */
public static enum HOSTGROUP_INTERNAL {
    NOT_INTERNAL(0),INTERNAL(1);

    public int value = 0;

    private HOSTGROUP_INTERNAL(int value) {
        this.value = value;
    }

}

/**
 *主机参数
 */
    /**
     * 主机监控类型
     */
    public static enum HOST_INTERFACE_TYPE {
        AGENT(1), SNMP(2), IPMI(3), JMX(4);


        public int value = 1;

        private HOST_INTERFACE_TYPE(int value) {
            this.value = value;
        }

    }

    /**
     *
     */
    public static enum HOST_AGENT_ACCESS_INTERFACE {
        HOST_DNS(0), IP_ADDRESS(1);

        public int value = 0;

        private HOST_AGENT_ACCESS_INTERFACE(int value) {
            this.value = value;
        }
    }

    /**
     * 主机停用 启用状态
     */
    public static enum HOST_MONITOR_STATUS {
        MONITORED_HOST(0), UNMONITORED_HOST(1);

        public int value;

        private HOST_MONITOR_STATUS(int value) {
            this.value = value;
        }

    }

    /**
     * 适用于agent,ipmi,jmx,snmp四个类型的状态值
     */
    public static enum HOST_AVAILABLE {
        UNKOWN_HOST(0), AVAILABLE_HOST(1),UNAVAILABLE_HOST(2);

        public int value;

        private HOST_AVAILABLE(int value) {
            this.value = value;
        }

    }

    /**
     * 主机维护状态值
     * NO_MAINTENANCE(0) 非维护状态
     * MAINTENANCE_IN_EFFECT(1) 维护状态
     */
    public static enum HOST_MAINTENANCE_STATUS  {
        NO_MAINTENANCE(0), MAINTENANCE_IN_EFFECT(1);

        public int value;

        private HOST_MAINTENANCE_STATUS(int value) {
            this.value = value;
        }

    }

    /**
     * 设备、监控点、业务平台、监控项的问题指标字段 custom_state
     * CUSTOM_STATE_OK(0) 对象状态 0表示正常 1表示警告 2表示严重
     * CUSTOM_STATE_WARNING(1)
     * CUSTOM_STATE_HIGHT(2)
     */
    public static enum OBJECT_STATE  {
        CUSTOM_STATE_OK(0), CUSTOM_STATE_WARNING(1),CUSTOM_STATE_HIGHT(2);

        public int value;

        private OBJECT_STATE(int value) {
            this.value = value;
        }

    }

    /**
     * 设备的问题指标字段 custom_available_state
     * CUSTOM_AVAILABLE_STATE_OK(0)  只有主机才有这个字段，主机联合CUSTOM_STATE一同判断对象状态，0表示4种接口中全部正常
     * CUSTOM_AVAILABLE_STATE_PROBLEM(1)  1表示4中接口中其中至少有一个有问题
     */
    public static enum HOST_AVAILABLE_STATE  {
        CUSTOM_AVAILABLE_STATE_OK(0), CUSTOM_AVAILABLE_STATE_PROBLEM(1);

        public int value;

        private HOST_AVAILABLE_STATE(int value) {
            this.value = value;
        }

    }

    /**
     * 主机维护获取数据type
     * WITH_DATA(0) 维护状态获取数据
     * WITHOUT_DATA(1) 维护状态不获取数据
     */
    public static enum HOST_MAINTENANCE_TYPE  {
        WITH_DATA(0), WITHOUT_DATA(1);

        public int value;

        private HOST_MAINTENANCE_TYPE(int value) {
            this.value = value;
        }

    }

    /**
     * IPMI验证算法
     */
    public static enum IPMI_AUTH_TYPE {
        DEFAULT(-1), NONE(0), MD2(1), MD5(2), STRAIGHT(3), OEM(4), RMCP_PLUS(5);

        public int value = -1;

        private IPMI_AUTH_TYPE(int value) {
            this.value = value;
        }
    }

    public static enum IPMI_PRIVILEAGE {
        CALLBACK(1), USER(2), OPERATOR(3), ADMIN(4), OEM(5);

        public int value;

        private IPMI_PRIVILEAGE(int value) {
            this.value = value;
        }
    }


/**
 * 触发器类型
 */
    /**
     * 触发器state状态
     */
    public static enum TRIGGER_STATE  {
        UP_TO_DATE(0),
        UNKNOWN(1);
        public int value;
        private TRIGGER_STATE(int value) {
            this.value = value;
        }

    }

    /**
     *
     */
    public static enum TRIGGER_VALUE  {
        OK(0),
        PROBLEM(1);
        public int value;
        private TRIGGER_VALUE(int value) {
            this.value = value;
        }

    }

    /**
     *触发器 priority 值
     */
    public static enum TRIGGER_PRIORITY  {
        NOT_CLASSIFIED(0),
        INFORMATION(1),
        WARNING(2),
        AVERAGE(3),
        HIGH(4),
        DISASTER(5);
        public int value;
        private TRIGGER_PRIORITY(int value) {
            this.value = value;
        }

    }

    /**
     * 触发器问题是否可手动关闭
     */
    public static enum TRIGGER_MANUAL_CLOSE  {
        NO(0),
        YES(1);
        public int value;
        private TRIGGER_MANUAL_CLOSE(int value) {
            this.value = value;
        }

    }

    /**
     * ACKNOWLEDGE 确认 action
     */
    public static enum ACKNOWLEDGE_ACTION  {
        UNACKNOWLEDGED(0),
        ACKNOWLEDGED(1);
        public int value;
        private ACKNOWLEDGE_ACTION(int value) {
            this.value = value;
        }

    }


    /**
     * ALERT 告警状态类型
     */

    public static enum ALERT_MESSAGE_STATUS  {
        MESSAGE_SENDING(0),
        MESSAGE_SENT(1),
        MESSAGE_FAILED(2);
        public int value;
        private ALERT_MESSAGE_STATUS(int value) {
            this.value = value;
        }

    }

    public static enum ALERT_COMMAND_STATUS  {
        COMMAND_WORK(0),
        COMMAND_UNWORK(1);
        public int value;
        private ALERT_COMMAND_STATUS(int value) {
            this.value = value;
        }

    }

/*
 * 事件类型 event
 */
    public static enum EVENT_VALUE  {
        OK(0),
        PROBLEM(1);
        public int value;
        private EVENT_VALUE(int value) {
            this.value = value;
        }

    }


/**
 * 监控项类型
 */
    /**
     * 监控项数据类型
     */
    public static enum ITEM_VALUE_TYPE {
        NUMERIC_FLOAT(0), CHARACTOR(1), LOG(2), NUMERIC_UNSIGNED(3), TEXT(4);

        public int value;

        private ITEM_VALUE_TYPE(int value) {
            this.value = value;
        }
    }




/**
 * 用户类型
 */
    /**
     * 用户状态
     */
    public static enum USER_STATUS {
        DISABLE("1"), ENABLE("0");

        public String value;

        private USER_STATUS(String value) {
            this.value = value;
        }
    }

    /**
     * 用户类型
     */
    public static enum USER_TYPE {
        USER(1),
        ADMIN(2),
        SUPER_ADMIN(3);

        public int value;

        private USER_TYPE(int value) {
            this.value = value;
        }
    }
    /**
     * 权限
     */
    public static enum USERGROUP_PERMISSION {
        ACCESS_DENIED(0),
        READ_ONLY(2),
        READ_WRITE(3);

        public int value;

        private USERGROUP_PERMISSION(int value) {
            this.value = value;
        }
    }

/**
 * 其他
 */
    public static enum DEBUG_MODE {
        DISABLE("0"), ENABLE("1");

        public String value;

        private DEBUG_MODE(String value) {
            this.value = value;
        }
    }

    public static enum GUI_ACCESS {
        SYS_DEFAULT_AUTH("0"), INTERNAL_AUTH("1"), DISABLE_ACCESS_FRONTEND("2");

        public String value;

        private GUI_ACCESS(String value) {
            this.value = value;
        }
    }

    public static enum ACCESS_LEVEL {
        ACCESS_DENIED(0), READONLY(1), READ_WRITE(2);

        public int value;

        private ACCESS_LEVEL(int value) {
            this.value = value;
        }
    }

    public static enum QUERY {
        count, shorten, refer, extend;
    }

    /**
     * 事件源类型
     */
    public static enum SOURCE {
        TRIGGER(0),
        DISCOVERY_RULE(1),
        AUTO_REGISTRATION(2),
        INTERNAL_EVENT(3);

        public int value;

        private SOURCE(int value) {
            this.value = value;
        }
    }

    /**
     * 对象类型
     */
    public static enum OBJECT {
        TRIGGER(0),
        DISCOVERED_HOST(1),
        DISCOVERED_SERVICE(2),
        AUTO_REGISTERED_HOST(3),
        ITEM(4),
        LLD_RULE(5);

        public int value;

        private OBJECT(int value) {
            this.value = value;
        }
    }


    /**
     * mediatype 的告警方式类型
     */
    public static enum ALERT_MEDIATYPE {
        EMAIL(0),
        SCRIPT(1),
        SMS(2),
        JABBER(3),
        EZ_TEXTING(100);

        public int value;

        private ALERT_MEDIATYPE(int value) {
            this.value = value;
        }
    }
}
