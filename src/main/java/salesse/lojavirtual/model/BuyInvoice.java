package salesse.lojavirtual.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "buy_invoice")
@SequenceGenerator(name = "seq_buy_invoice", sequenceName = "seq_buy_invoice", allocationSize = 1, initialValue = 1)
public class BuyInvoice implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_buy_invoice")
	private Long id;

	private String numberInvoice;
	private String serieInvoice;
	private String descriptionObservation;
	private BigDecimal totalValue;
	private BigDecimal descountValue;
	private BigDecimal icmstValue;
	private Date buyDate;

	@ManyToOne
	@JoinColumn(name = "person_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "person_fk"))
	private Person person;

	@ManyToOne
	@JoinColumn(name = "account_pay_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "account_pay_fk"))
	private AccountPay accountPay;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumberInvoice() {
		return numberInvoice;
	}

	public void setNumberInvoice(String numberInvoice) {
		this.numberInvoice = numberInvoice;
	}

	public String getSerieInvoice() {
		return serieInvoice;
	}

	public void setSerieInvoice(String serieInvoice) {
		this.serieInvoice = serieInvoice;
	}

	public String getDescriptionObservation() {
		return descriptionObservation;
	}

	public void setDescriptionObservation(String descriptionObservation) {
		this.descriptionObservation = descriptionObservation;
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

	public BigDecimal getIcmstValue() {
		return icmstValue;
	}

	public void setIcmstValue(BigDecimal icmstValue) {
		this.icmstValue = icmstValue;
	}

	public Date getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public AccountPay getAccountPay() {
		return accountPay;
	}

	public void setAccountPay(AccountPay accountPay) {
		this.accountPay = accountPay;
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
		BuyInvoice other = (BuyInvoice) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "BuyInvoice [id=" + id + ", numberInvoice=" + numberInvoice + ", serieInvoice=" + serieInvoice
				+ ", descriptionObservation=" + descriptionObservation + ", totalValue=" + totalValue
				+ ", descountValue=" + descountValue + ", icmstValue=" + icmstValue + ", buyDate=" + buyDate
				+ ", person=" + person + ", accountPay=" + accountPay + "]";
	}

}
