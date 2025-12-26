package salesse.lojavirtual.model;

import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "person_fisic")
@PrimaryKeyJoinColumn(name = "id")
public class PersonPF extends Person {

	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private String cpf;

	private Date birthdayDate;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getBirthdayDate() {
		return birthdayDate;
	}

	public void setBirthdayDate(Date birthdayDate) {
		this.birthdayDate = birthdayDate;
	}

}
