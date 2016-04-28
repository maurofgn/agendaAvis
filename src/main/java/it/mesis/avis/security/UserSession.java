package it.mesis.avis.security;

import java.util.List;

import it.mesis.avis.model.UserProfileType;
import it.mesis.util.model.DonaStatus;
import it.mesis.util.model.TipoDonaPuntoPrel;
import it.mesis.util.model.YearMonth;

//@Bean("userSession")
//@Scope("session")
/**
 * @author mauro
 *
 */
public class UserSession {

	public static final String USER_SESSION_KEY = "userSession";

	private DonaStatus donaStatus;
	private UserProfileType userProfileType;

	private List<TipoDonaPuntoPrel> listTipoDonazPuntiPrel;
	private List<YearMonth> listYearMonth;

	private TipoDonaPuntoPrel tipoDonaPuntoPrel;

	private YearMonth yearMonth;	//anno mese in elaborazione
	private TipoDonaPuntoPrel tipoDonaPuntoPrelSelected;	//tipo donazione e punto di prelievo in elaborazione
	
	public boolean isDonatore() {
		return donaStatus != null;
	}
	
	public DonaStatus getDonaStatus() {
		return donaStatus;
	}

	public void setDonaStatus(DonaStatus donaStatus) {
		this.donaStatus = donaStatus;
	}

	public UserProfileType getUserProfileType() {
		return userProfileType;
	}

	public void setUserProfileType(UserProfileType userProfileType) {
		this.userProfileType = userProfileType;
		
	}

	public void setTipoDonaPuntoPrel(int tdId, int ppId) {
		for (TipoDonaPuntoPrel tipoDonaPuntoPrel : listTipoDonazPuntiPrel) {
			if (tipoDonaPuntoPrel.getPuntoprelId() == ppId && tipoDonaPuntoPrel.getTipoDonaId() == tdId) {
				setTipoDonaPuntoPrel(tipoDonaPuntoPrel);
				break;
			}
		}
	}

	public boolean isAtLeastOneTipodonaz() {
		return listTipoDonazPuntiPrel.size() > 0;
	}

	public List<TipoDonaPuntoPrel> getListTipoDonazPuntiPrel() {
		return listTipoDonazPuntiPrel;
	}
	
	public void setListTipoDonazPuntiPrel(List<TipoDonaPuntoPrel> listTipoDonazPuntiPrel) {
		this.listTipoDonazPuntiPrel = listTipoDonazPuntiPrel;
		
		if (listTipoDonazPuntiPrel.size() == 1) {
			setTipoDonaPuntoPrel(listTipoDonazPuntiPrel.get(0));
		}
	}

	public void setTipoDonaPuntoPrel(TipoDonaPuntoPrel tipoDonaPuntoPrel) {
		this.tipoDonaPuntoPrel = tipoDonaPuntoPrel;
	}
	
	public TipoDonaPuntoPrel getTipoDonaPuntoPrel() {
		return tipoDonaPuntoPrel;
	}

	public List<YearMonth> getListYearMonth() {
		return listYearMonth;
	}

	public void setListYearMonth(List<YearMonth> listYearMonth) {
		this.listYearMonth = listYearMonth;
	}

	public YearMonth getYearMonth() {
		return yearMonth;
	}

	public void setYearMonth(YearMonth yearMonth) {
		this.yearMonth = yearMonth;
	}

	public TipoDonaPuntoPrel getTipoDonaPuntoPrelSelected() {
		return tipoDonaPuntoPrelSelected;
	}

	public void setTipoDonaPuntoPrelSelected(
			TipoDonaPuntoPrel tipoDonaPuntoPrelSelected) {
		this.tipoDonaPuntoPrelSelected = tipoDonaPuntoPrelSelected;
	}

}
