package salesse.lojavirtual.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "product")
@SequenceGenerator(name = "seq_product", sequenceName = "seq_product", allocationSize = 1, initialValue = 1)
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_product")
	private Long id;

	private String unitType;
	private String name;
	
	@Column(columnDefinition = "text", length = 2000)
	private String description;
	
	/* Nota item produto - ASSOCIAR*/
	
	private Double weight;
	private Double width;
	private Double height;
	private Double depth;
	private BigDecimal saleValue = BigDecimal.ZERO;
	private Integer stockQuantity = 0;
	private Integer stockQuantityAlert = 0;
	private String youtubeLink;
	private Boolean stockAlert = Boolean.FALSE;
	private Boolean active = Boolean.TRUE;
	private Integer clickQuantity = 0;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUnitType() {
		return unitType;
	}
	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public Double getWidth() {
		return width;
	}
	public void setWidth(Double width) {
		this.width = width;
	}
	public Double getHeight() {
		return height;
	}
	public void setHeight(Double height) {
		this.height = height;
	}
	public Double getDepth() {
		return depth;
	}
	public void setDepth(Double depth) {
		this.depth = depth;
	}
	public BigDecimal getSaleValue() {
		return saleValue;
	}
	public void setSaleValue(BigDecimal saleValue) {
		this.saleValue = saleValue;
	}
	public Integer getStockQuantity() {
		return stockQuantity;
	}
	public void setStockQuantity(Integer stockQuantity) {
		this.stockQuantity = stockQuantity;
	}
	public Integer getStockQuantityAlert() {
		return stockQuantityAlert;
	}
	public void setStockQuantityAlert(Integer stockQuantityAlert) {
		this.stockQuantityAlert = stockQuantityAlert;
	}
	public String getYoutubeLink() {
		return youtubeLink;
	}
	public void setYoutubeLink(String youtubeLink) {
		this.youtubeLink = youtubeLink;
	}
	public Boolean getStockAlert() {
		return stockAlert;
	}
	public void setStockAlert(Boolean stockAlert) {
		this.stockAlert = stockAlert;
	}
	public Integer getClickQuantity() {
		return clickQuantity;
	}
	public void setClickQuantity(Integer clickQuantity) {
		this.clickQuantity = clickQuantity;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
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
		Product other = (Product) obj;
		return Objects.equals(id, other.id);
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", unitType=" + unitType + ", name=" + name + ", description=" + description
				+ ", weight=" + weight + ", width=" + width + ", height=" + height + ", depth=" + depth + ", saleValue="
				+ saleValue + ", stockQuantity=" + stockQuantity + ", stockQuantityAlert=" + stockQuantityAlert
				+ ", youtubeLink=" + youtubeLink + ", stockAlert=" + stockAlert + ", clickQuantity=" + clickQuantity
				+ "]";
	}
	
	
	
}
