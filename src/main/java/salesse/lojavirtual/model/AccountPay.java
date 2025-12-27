package salesse.lojavirtual.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import salesse.lojavirtual.enums.StatusAccountPay;

@Entity
@Table(name = "account_pay")
@SequenceGenerator(name = "seq_account_pay", sequenceName = "seq_account_pay", allocationSize = 1, initialValue = 1)
public class AccountPay implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_account_pay")
	private Long id;
	
	@Column(nullable = false)
	private String description;
	
	@Column(nullable = false)
	private BigDecimal totalValue;
	private BigDecimal descountValue;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private StatusAccountPay status;
	
	@Column(nullable = false)
	private Date expireDate;
	private Date paymentDate;

	@ManyToOne
	@JoinColumn(name = "person_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "person_fk"))
	private Person person;

	@ManyToOne
	@JoinColumn(name = "person_supplier_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "person_supplier_fk"))
	private Person person_supplier;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public StatusAccountPay getStatus() {
		return status;
	}

	public void setStatus(StatusAccountPay status) {
		this.status = status;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Person getPerson_supplier() {
		return person_supplier;
	}

	public void setPerson_supplier(Person person_supplier) {
		this.person_supplier = person_supplier;
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
		AccountPay other = (AccountPay) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "AccountPay [id=" + id + ", description=" + description + ", totalValue=" + totalValue
				+ ", descountValue=" + descountValue + ", status=" + status + ", expireDate=" + expireDate
				+ ", paymentDate=" + paymentDate + ", person=" + person + ", person_supplier=" + person_supplier + "]";
	}

}
