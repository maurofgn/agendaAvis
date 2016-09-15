package it.mesis.avis.bean.jpa;

import it.mesis.avis.enu.UserProfileType;
import it.mesis.avis.security.UserAttempts;
import it.mesis.util.model.State;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.envers.NotAudited;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.Interval;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.Assert;

@Entity
@Table(name = "APP_USER")
//@Audited
public class UserEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Size(min=2, max=30)
	@NotEmpty
	@Column(name = "SSO_ID", unique = true, nullable = false)
	private String ssoId;

	@Size(min=1, max=100)
	@NotEmpty
	@Column(name = "PASSWORD", nullable = true)
	private String password;

	@NotNull
	@Column(name = "ATTEMPTS", nullable = false)
	private int attempts = 0;

	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "LAST_ACCESS")
	private java.sql.Timestamp lastAccess;

	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "LAST_CHANGE_PSW")
	private java.sql.Timestamp lastChangePsw;

	@Size(min=1, max=30)
	@NotEmpty
	@Column(name = "STATE", nullable = false)
	private String state = State.ACTIVE.getState();

//	@Column(name = "UTENTI_ID")
//	private Short utentiId;
	
	@NotEmpty
	@Column(name = "asso_avis", nullable = false, length=1, columnDefinition = "varchar(1) NOT NULL default 'N'")
	private String assoAvis = "N";

//	@OneToOne(fetch = FetchType.LAZY, mappedBy = "utenti", cascade = CascadeType.ALL)
//    @JoinColumn(name="UTENTI_ID", referencedColumnName = "ID", insertable = false, updatable = false)
	@OneToOne(optional=true, fetch = FetchType.LAZY)
    @JoinColumn(name="UTENTI_ID", referencedColumnName = "ID")
    private UtentiEntity utenti;
	
	public UtentiEntity getUtenti() {
		return this.utenti;
	}
	
	public void setUtenti(UtentiEntity utenti) {
		this.utenti = utenti;
	}
	
	
//	@Size(min=0, max=50)
//	@Column(name = "CODINTERNODONAT")
//	private String codinternodonat;
	
	@OneToOne(optional=true, fetch = FetchType.LAZY)
//    @JoinColumn(name="CODINTERNODONAT", referencedColumnName = "CODINTERNODONAT", insertable = false, updatable = false, nullable=true)
    @JoinColumn(name="CODINTERNODONAT", referencedColumnName = "CODINTERNODONAT")
    private DonatoreEntity donatore;
	
	public DonatoreEntity getDonatore() {
		return this.donatore;
	}
	
	public void setDonatore(DonatoreEntity donatore) {
		this.donatore = donatore;
	}

	@NotAudited
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "APP_USER_USER_PROFILE", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "USER_PROFILE_ID") })
	private Set<UserProfileEntity> userProfiles = new HashSet<UserProfileEntity>();

	/**
	 * 
	 * @param type
	 * @return true se l'user ha il ruolo
	 */
	public boolean hasRole(UserProfileType type) {
		for (UserProfileEntity userProfile : userProfiles) {
			if (userProfile.getType().equals(type.getUserProfileType()))
				return true;
		}
		return false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSsoId() {
		return ssoId;
	}

	public void setSsoId(String ssoId) {
		this.ssoId = ssoId;
	}

	/**
	 * 
	 * @return psw dell'utente se è definita, diversamente torna la password definita in app_user (donatore)
	 */
	public String getPasswordDefault() {
		return getUtenti() != null && getUtenti().getPassword() != null && !getUtenti().getPassword().isEmpty() 
				? getUtenti().getPassword() 
				: password;
	}
	
	public boolean hasPassword() {
		return getPasswordDefault() != null && !getPasswordDefault().isEmpty();
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public State getState() {
		return State.valueOf(state.toUpperCase()) ;
	}

	public void setState(State state) {
		this.state = state.getState();
	}
	
//	public Short getUtentiId() {
//		return utentiId;
//	}
//	public void setUtentiId(Short utentiId) {
//		this.utentiId = utentiId;
//	}
	
//	public String getCodinternodonat() {
//		return codinternodonat;
//	}
//	public void setCodinternodonat(String codinternodonat) {
//		this.codinternodonat = codinternodonat;
//	}

	public java.sql.Timestamp getLastChangePsw() {
		return lastChangePsw;
	}
	public void setLastChangePsw(java.sql.Timestamp lastChangePsw) {
		this.lastChangePsw = lastChangePsw;
	}
	
	public java.sql.Timestamp getLastAccess() {
		return lastAccess;
	}
	public void setLastAccess(java.sql.Timestamp lastAccess) {
		this.lastAccess = lastAccess;
	}

	public int getAttempts() {
		return attempts;
	}
	public void setAttempts(int attempts) {
		this.attempts = attempts;
	}

	public Set<UserProfileEntity> getUserProfiles() {
		return userProfiles;
	}

	public void setUserProfiles(Set<UserProfileEntity> userProfiles) {
		this.userProfiles = userProfiles;
	}
	
	public String getName() {
		return donatore != null ? donatore.getCognomeenome() : (utenti != null ? utenti.getNome() : null);
	}
	
	public String getAssoAvis() {
		return assoAvis;
	}

	public void setAssoAvis(String assoAvis) {
		this.assoAvis = assoAvis;
	}

	public boolean isEnabled() {
		boolean retValue = false;
		switch (getState()) {
		case ACTIVE:
			retValue = true;
			break;
		case INACTIVE:
		case DELETED:
		case LOCKED:
		default:
			retValue = false;
			break;
		}
		
		return retValue;
	}
	
	public boolean isNotLocked() {
		return !State.LOCKED.equals(getState());
	}
	
	public boolean isNotExpired() {
		return !State.DELETED.equals(getState());
	}

	public boolean pswNotExpired(int days) {
		return getDonatore() != null ? daysPasswordExpiry() <= days : true;
	}

	/**
	 * 
	 * @return delta in giorni trascorsi dalla data ultimo cambio password ad oggi, se ultimo cambio password is null ==> 2147483647
	 * 
	 */
	public int daysPasswordExpiry() {
		return getLastChangePsw() == null ? Integer.MAX_VALUE : daysPast(getLastChangePsw(), new Date());
	}
	
	/**
	 * giorni trascorsi da dateFrom a dateTo, se dateFrom > dateTo il risultato sarà negativo. (usa joda-time)
	 * 
	 * @param dateFrom cannot be null
	 * @param dateTo cannot be null
	 * @return giorni trascorsi
	 * @throws IllegalArgumentException 
	 */
	private Integer daysPast(Date dateFrom, Date dateTo) {
		Assert.notNull(dateFrom, "'dateFrom' is required.");
		Assert.notNull(dateTo, "'dateTo' is required.");
		boolean sign = dateFrom.compareTo(dateTo) < 0;
		Interval interval = sign 
			? new Interval(dateFrom.getTime(), dateTo.getTime()) 
			: new Interval(dateTo.getTime(), dateFrom.getTime());
		
		return interval.toDuration().toStandardDays().getDays() * (sign ? 1 :-1);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((ssoId == null) ? 0 : ssoId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserEntity other = (UserEntity) obj;
		if (id != other.id)
			return false;
		if (ssoId == null) {
			if (other.ssoId != null)
				return false;
		} else if (!ssoId.equals(other.ssoId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", ssoId=" + ssoId + ", password=" + password
				+ ", state=" + state + ", userProfiles=" + userProfiles + "]";
	}

	public void addAttempts() {
		attempts++;
		lastAccess = new Timestamp(System.currentTimeMillis());
	}

	public UserAttempts getUserAttempts() {
		return new UserAttempts(id, ssoId, attempts, lastAccess);
	}
	
	public void addDefaultRole() {
		
		if (!hasRole(UserProfileType.DONA) && getDonatore() != null) {
			userProfiles.add(new UserProfileEntity(UserProfileType.DONA));
		}
		
		if (getUtenti() != null && getUtenti().getId() > 0) {
			if ("N".equalsIgnoreCase(assoAvis))
				userProfiles.add(new UserProfileEntity(UserProfileType.OPERA));
			else
				userProfiles.add(new UserProfileEntity(UserProfileType.AVIS));
		} else if (getDonatore() == null)
			userProfiles.add(new UserProfileEntity(UserProfileType.OPERA));
		
	}

}
