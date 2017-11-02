package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import controller.BitPayException;
import utils.DateDeserializer;
import utils.DateSerializer;

import java.util.List;

/**
 * See https://bitpay.com/api#resource-Bills
 * @author kobus
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Bill {
    public static final String STATUS_COMPLETE = "complete";
	public static final String STATUS_PAID = "paid";
	public static final String STATUS_DRAFT = "draft";
	public static final String STATUS_SENT = "sent";

	private String guid = "";
    private String token = "";
	private String id;

	private String status;
    private String subscription;
    private String currency;
    private String number;
    private String name;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zip;
    private String country;
    private String email;
    private String phone;
    private long createdDate;
    private long dueDate;

    private Boolean delivered;
    private Boolean archived;
    private Boolean showRate;

    private List<BillItem> items;

    public Bill() {}

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public List<BillItem> getItems() {
        return items;
    }

    public void setItems(List<BillItem> items) {
        this.items = items;
    }

    @JsonSerialize(using= DateSerializer.class)
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    public long getDueDate() {
        return dueDate;
    }

    @JsonDeserialize(using= DateDeserializer.class)
    public void setDueDate(long dueDate) {
        this.dueDate = dueDate;
    }

    @JsonSerialize(using= DateSerializer.class)
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    public long getCreatedDate() {
        return createdDate;
    }

    @JsonDeserialize(using= DateDeserializer.class)
    public void setCreatedDate(long createdDate) {
        this.createdDate = createdDate;
    }

    public String getSubscription() {
        return subscription;
    }

    public void setSubscription(String subscription) {
        this.subscription = subscription;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isArchived() {
        return archived;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }


    public Boolean isShowRate() {
        return showRate;
    }

    public void setShowRate(Boolean showRate) {
        this.showRate = showRate;
    }

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(Boolean delivered) {
        this.delivered = delivered;
    }

	public String getCurrency() {
		return currency;
	}
	
	public void setCurrency(String currency) throws BitPayException {
        if (currency.length() != 3)
        {
            throw new BitPayException("Error: currency code must be exactly three characters");
        }
		this.currency = currency;
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

    @Override
    public String toString() {
        return "Bill{" +
                "guid='" + guid + '\'' +
                ", id='" + id + '\'' +
                ", status='" + status + '\'' +
                ", subscription='" + subscription + '\'' +
                ", token='" + token + '\'' +
                ", currency='" + currency + '\'' +
                ", number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                ", country='" + country + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", createdDate=" + createdDate +
                ", dueDate=" + dueDate +
                ", delivered=" + delivered +
                ", archived=" + archived +
                ", showRate=" + showRate +
                ", items=" + items +
                '}';
    }
}
