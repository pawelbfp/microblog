package com.microblog.core;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public  class SubscriptionPK implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Integer idSubscriber;
	protected Integer idSubscribed;

    public SubscriptionPK() {}

    public SubscriptionPK(Integer idSubscriber, Integer idSubscribed) {
        this.idSubscriber = idSubscriber;
        this.idSubscribed = idSubscribed;
    }
    
	public Integer getIdSubscriber() {
		return idSubscriber;
	}
	
	
	public void setIdSubscriber(Integer idSubscriber) {
		this.idSubscriber= idSubscriber;
	}


	public Integer getIdSubscribed() {
		return idSubscribed;
	}
	
	
	public void setIdSubscribed(Integer idSubscribed) {
		this.idSubscribed= idSubscribed;
	}	    
}