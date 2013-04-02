package com.microblog.core;

import java.io.Serializable;

import javax.persistence.AttributeOverrides;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "subscriptions")
public class Subscription implements Serializable {

	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private SubscriptionPK subscriptionPK;

	public Subscription() {
	}

	public Subscription(Integer idSubscriber, Integer idSubscribed) {
		super();
		subscriptionPK = new SubscriptionPK(idSubscriber, idSubscribed);
	}

	@EmbeddedId
	@AttributeOverrides({ @AttributeOverride(name = "idSubscriber", column = @Column(name = "idSubscriber", nullable = false)),
			@AttributeOverride(name = "idSubscribed", column = @Column(name = "idSubscribed", nullable = false)) })
	public SubscriptionPK getSubscriptionPK() {
		return this.subscriptionPK;
	}

	public void setSubscriptionPK(SubscriptionPK subscriptionPK) {
		this.subscriptionPK = subscriptionPK;
	}
}
