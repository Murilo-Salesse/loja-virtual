package salesse.lojavirtual.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "brand_product")
@SequenceGenerator(name = "seq_brand_product", sequenceName = "seq_brand_product", allocationSize = 1, initialValue = 1)
public class ProductBrand implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_brand_product")
	private Long id;

	@Column(nullable = false)
	private String nameDescription;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameDescription() {
		return nameDescription;
	}

	public void setNameDescription(String nameDescription) {
		this.nameDescription = nameDescription;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nameDescription);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductBrand other = (ProductBrand) obj;
		return Objects.equals(id, other.id) && Objects.equals(nameDescription, other.nameDescription);
	}
}
