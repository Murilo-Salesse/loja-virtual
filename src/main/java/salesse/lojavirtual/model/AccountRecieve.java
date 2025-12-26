package salesse.lojavirtual.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

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
import salesse.lojavirtual.enums.StatusAccountRecieve;

@Entity
@Table(name = "account_recieve")
@SequenceGenerator(name = "seq_account_recieve", sequenceName = "seq_account_recieve", allocationSize = 1, initialValue = 1)
public class AccountRecieve implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_account_recieve")
	private Long id;

	private String description;

	@Enumerated(EnumType.STRING)
	private StatusAccountRecieve status;

	private Date expireDate;
	private Date paymentDate;

	private BigDecimal totalValue;

	@ManyToOne
	@JoinColumn(name = "person_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "person_fk"))
	private Person person;

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

	public StatusAccountRecieve getStatus() {
		return status;
	}

	public void setStatus(StatusAccountRecieve status) {
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

	public BigDecimal getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(BigDecimal totalValue) {
		this.totalValue = totalValue;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
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
		AccountRecieve other = (AccountRecieve) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "AccountRecieve [id=" + id + ", description=" + description + ", status=" + status + ", expireDate="
				+ expireDate + ", paymentDate=" + paymentDate + ", totalValue=" + totalValue + ", person=" + person
				+ "]";
	}

}
