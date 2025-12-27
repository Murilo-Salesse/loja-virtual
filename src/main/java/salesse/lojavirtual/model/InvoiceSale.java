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
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "invoice_sale")
@SequenceGenerator(name = "seq_invoice_sale", sequenceName = "seq_invoice_sale", allocationSize = 1, initialValue = 1)
public class InvoiceSale implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_invoice_sale")
	private Long id;

	@Column(nullable = false)
	private String number;
	
	@Column(nullable = false)
	private String serie;
	
	@Column(nullable = false)
	private String type;

	@Column(columnDefinition = "text", nullable = false)
	private String xml;

	@Column(columnDefinition = "text", nullable = false)
	private String pdf;

	@OneToOne
	@JoinColumn(name = "buy_sell_virtual_store_id", nullable = false, 
	foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "buy_sell_virtual_store_id_fk"))
	private BuySellVirtualStore buySellVirtualStore;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}

	public String getPdf() {
		return pdf;
	}

	public void setPdf(String pdf) {
		this.pdf = pdf;
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
		InvoiceSale other = (InvoiceSale) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "InvoiceSale [id=" + id + ", number=" + number + ", serie=" + serie + ", type=" + type + ", xml=" + xml
				+ ", pdf=" + pdf + "]";
	}

}
