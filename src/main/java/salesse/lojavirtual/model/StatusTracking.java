package salesse.lojavirtual.model;

import java.io.Serializable;
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
@Table(name = "status_tracking")
@SequenceGenerator(name = "seq_status_tracking", sequenceName = "seq_status_tracking", allocationSize = 1, initialValue = 1)
public class StatusTracking implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_status_tracking")
	private Long id;

	private String distributionCenter;
	private String city;
	private String state;
	private String status;
	
	@ManyToOne
	@JoinColumn(name = "buy_sell_virtual_store_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "buy_sell_virtual_store_id_fk"))
	private BuySellVirtualStore buySellVirtualStore;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDistributionCenter() {
		return distributionCenter;
	}

	public void setDistributionCenter(String distributionCenter) {
		this.distributionCenter = distributionCenter;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
		StatusTracking other = (StatusTracking) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "StatusTracking [id=" + id + ", distributionCenter=" + distributionCenter + ", city=" + city + ", state="
				+ state + ", status=" + status + "]";
	}

}
