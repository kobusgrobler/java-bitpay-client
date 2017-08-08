package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import utils.DateDeserializer;
import utils.DateSerializer;

/**
 *
 * Created by kobus on 2017/08/08.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class Subscription {
    public static final String STATUS_DRAFT = "draft";
    public static final String STATUS_ACTIVE = "active";
    public static final String STATUS_CANCELLED = "cancelled";

    private String guid = "";
    private String token = "";
    private String id;

    private String status;
    private String schedule="monthly";
    private long nextDelivery;
    private Bill billData;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    @JsonSerialize(using= DateSerializer.class)
    public long getNextDelivery() {
        return nextDelivery;
    }

    @JsonDeserialize(using= DateDeserializer.class)
    public void setNextDelivery(long nextDelivery) {
        this.nextDelivery = nextDelivery;
    }

    public Bill getBillData() {
        return billData;
    }

    public void setBillData(Bill billData) {
        this.billData = billData;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "guid='" + guid + '\'' +
                ", token='" + token + '\'' +
                ", id='" + id + '\'' +
                ", status='" + status + '\'' +
                ", schedule='" + schedule + '\'' +
                ", nextDelivery=" + nextDelivery +
                ", billData=" + billData +
                '}';
    }
}
