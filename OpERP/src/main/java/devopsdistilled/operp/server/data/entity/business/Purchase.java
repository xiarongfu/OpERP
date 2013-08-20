package devopsdistilled.operp.server.data.entity.business;

import javax.persistence.Entity;

import devopsdistilled.operp.server.data.entity.party.Vendor;

@Entity
public class Purchase extends Business<Vendor> {

	private static final long serialVersionUID = 4680542154771058958L;

	@Override
	public Vendor getParty() {
		return party;
	}

	@Override
	public void setParty(Vendor party) {
		this.party = party;
	}
}
