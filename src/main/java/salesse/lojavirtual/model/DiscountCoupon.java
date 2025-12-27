package salesse.lojavirtual.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "discount_coupon")
@SequenceGenerator(name = "seq_discount_coupon", sequenceName = "seq_discount_coupon", allocationSize = 1, initialValue = 1)
public class DiscountCoupon implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_discount_coupon")
	private Long id;
	
	@Column(nullable = false)
	private String DescriptionCode;
	
	private BigDecimal realValueDescription;
	private BigDecimal percentValueDescription;
	
	@Column(nullable = false)
	private Date validateDateCupon;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescriptionCode() {
		return DescriptionCode;
	}
	public void setDescriptionCode(String descriptionCode) {
		DescriptionCode = descriptionCode;
	}
	public BigDecimal getRealValueDescription() {
		return realValueDescription;
	}
	public void setRealValueDescription(BigDecimal realValueDescription) {
		this.realValueDescription = realValueDescription;
	}
	public BigDecimal getPercentValueDescription() {
		return percentValueDescription;
	}
	public void setPercentValueDescription(BigDecimal percentValueDescription) {
		this.percentValueDescription = percentValueDescription;
	}
	public Date getValidateDateCupon() {
		return validateDateCupon;
	}
	public void setValidateDateCupon(Date validateDateCupon) {
		this.validateDateCupon = validateDateCupon;
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
		DiscountCoupon other = (DiscountCoupon) obj;
		return Objects.equals(id, other.id);
	}
	@Override
	public String toString() {
		return "DiscountCoupon [id=" + id + ", DescriptionCode=" + DescriptionCode + ", realValueDescription="
				+ realValueDescription + ", percentValueDescription=" + percentValueDescription + ", validateDateCupon="
				+ validateDateCupon + "]";
	}

	
}
