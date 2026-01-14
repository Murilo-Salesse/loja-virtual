package salesse.lojavirtual.model;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "users")
@SequenceGenerator(name = "seq_users", sequenceName = "seq_users", allocationSize = 1, initialValue = 1)
public class User implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_users")
	private Long id;

	@Column(nullable = false)
	private String login;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private Date dateCurrencyPassword;

	@ManyToOne
	@JoinColumn(name = "person_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "person_fk"))
	private Person person;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "users_access", uniqueConstraints = @UniqueConstraint(columnNames = { "user_id",
			"access_id" }, name = "unique_access_user"), joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id", table = "users", unique = false, foreignKey = @ForeignKey(name = "user_fk", value = ConstraintMode.CONSTRAINT)), inverseJoinColumns = @JoinColumn(name = "access_id", unique = false, referencedColumnName = "id", table = "access", foreignKey = @ForeignKey(name = "access_fk", value = ConstraintMode.CONSTRAINT)))
	private List<Access> access;

	/*
	 * Autoridades = São os acessos, ou seja ROLE_ADMIN, ROLE_SECRETARIO,
	 * ROLE_FINANCEIRO
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return this.access;
	}

	@Override
	public String getPassword() {

		return this.password;
	}

	@Override
	public String getUsername() {

		return this.login;
	}

	public Date getDateCurrencyPassword() {
		return dateCurrencyPassword;
	}

	public void setDateCurrencyPassword(Date dateCurrencyPassword) {
		this.dateCurrencyPassword = dateCurrencyPassword;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public List<Access> getAccess() {
		return access;
	}

	public void setAccess(List<Access> access) {
		this.access = access;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
