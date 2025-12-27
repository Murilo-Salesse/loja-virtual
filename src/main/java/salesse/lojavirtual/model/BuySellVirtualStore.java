package salesse.lojavirtual.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "buy_sell_virtual_store")
@SequenceGenerator(name = "seq_buy_sell_virtual_store", sequenceName = "seq_buy_sell_virtual_store", allocationSize = 1, initialValue = 1)
public class BuySellVirtualStore implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_buy_sell_virtual_store")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "person_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "person_fk"))
	private Person person;

	@ManyToOne
	@JoinColumn(name = "delivery_address_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "delivery_address_id_fk"))
	private Address deliveryAddress;

	@ManyToOne
	@JoinColumn(name = "charge_address_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "charge_address_id_fk"))
	private Address chargeAddress;

	@Column(nullable = false)
	private BigDecimal totalValue;
	private BigDecimal descountValue;

	@ManyToOne
	@JoinColumn(name = "method_payment_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "method_payment_id_fk"))
	private MethodPayment methodPayment;

	@OneToOne
	@JoinColumn(name = "invoice_sale_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "invoice_sale_id_fk"))
	private InvoiceSale invoiceSale;

	@ManyToOne
	@JoinColumn(name = "discount_coupon_id", foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "discount_coupon_id_fk"))
	private DiscountCoupon discountCoupon;

	@Column(nullable = false)
	private BigDecimal freightValue;
	
	@Column(nullable = false)
	private Integer deliveryDays;
	
	@Column(nullable = false)
	private Date saleDate;
	
	@Column(nullable = false)
	private Date deliveredDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Address getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(Address deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public Address getChargeAddress() {
		return chargeAddress;
	}

	public void setChargeAddress(Address chargeAddress) {
		this.chargeAddress = chargeAddress;
	}

	public BigDecimal getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(BigDecimal totalValue) {
		this.totalValue = totalValue;
	}

	public BigDecimal getDescountValue() {
		return descountValue;
	}

	public void setDescountValue(BigDecimal descountValue) {
		this.descountValue = descountValue;
	}

	public MethodPayment getMethodPayment() {
		return methodPayment;
	}

	public void setMethodPayment(MethodPayment methodPayment) {
		this.methodPayment = methodPayment;
	}

	public InvoiceSale getInvoiceSale() {
		return invoiceSale;
	}

	public void setInvoiceSale(InvoiceSale invoiceSale) {
		this.invoiceSale = invoiceSale;
	}

	public DiscountCoupon getDiscountCoupon() {
		return discountCoupon;
	}

	public void setDiscountCoupon(DiscountCoupon discountCoupon) {
		this.discountCoupon = discountCoupon;
	}

	public BigDecimal getFreightValue() {
		return freightValue;
	}

	public void setFreightValue(BigDecimal freightValue) {
		this.freightValue = freightValue;
	}

	public Integer getDeliveryDays() {
		return deliveryDays;
	}

	public void setDeliveryDays(Integer deliveryDays) {
		this.deliveryDays = deliveryDays;
	}

	public Date getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}

	public Date getDeliveredDate() {
		return deliveredDate;
	}

	public void setDeliveredDate(Date deliveredDate) {
		this.deliveredDate = deliveredDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BuySellVirtualStore other = (BuySellVirtualStore) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "BuySellVirtualStore [id=" + id + ", person=" + person + ", deliveryAddress=" + deliveryAddress
				+ ", chargeAddress=" + chargeAddress + ", totalValue=" + totalValue + ", descountValue=" + descountValue
				+ ", methodPayment=" + methodPayment + ", invoiceSale=" + invoiceSale + ", discountCoupon="
				+ discountCoupon + ", freightValue=" + freightValue + ", deliveryDays=" + deliveryDays + ", saleDate="
				+ saleDate + ", deliveredDate=" + deliveredDate + "]";
	}

}
