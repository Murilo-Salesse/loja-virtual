package salesse.lojavirtual.model;

import java.io.Serializable;
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
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "store_sale_item")
@SequenceGenerator(name = "seq_store_sale_item", sequenceName = "seq_store_sale_item", allocationSize = 1, initialValue = 1)
public class StoreSaleItem implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_store_sale_item")
	private Long id;

	@Column(nullable = false)
	private Double quantity;

	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "product_id_fk"))
	private Product product;

	@ManyToOne
	@JoinColumn(name = "buy_sell_virtual_store_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "buy_sell_virtual_store_id_fk"))
	private BuySellVirtualStore buySellVirtualStore;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public BuySellVirtualStore getBuySellVirtualStore() {
		return buySellVirtualStore;
	}

	public void setBuySellVirtualStore(BuySellVirtualStore buySellVirtualStore) {
		this.buySellVirtualStore = buySellVirtualStore;
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
		StoreSaleItem other = (StoreSaleItem) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "StoreSaleItem [id=" + id + ", quantity=" + quantity + ", product=" + product + ", buySellVirtualStore="
				+ buySellVirtualStore + "]";
	}

}
