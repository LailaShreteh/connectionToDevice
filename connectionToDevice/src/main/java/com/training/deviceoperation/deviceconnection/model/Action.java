package com.training.deviceoperation.deviceconnection.model;

public enum Action {
	
	BANDWIDTH("bandwidth"),
	FAIR_QUEUE("fair-queue"),
	DROP("drop"),
	POLICY("policy"),
	POLICE("police"),
	police_rate_pdp("police rate pdp"),
	priority("priority"),
	queue_limit("queue-limit"),
	random_detect("random-detect"),
	random_detect_discard_class("random-detect discard-class"),
	random_detect_discard_class_based("random-detect discard-class-based"),
	random_detect_ecn("random-detect ecn"),
	random_detect_precedence("random-detect precedence"),
	service_policy("service-policy"),
	set_atm_clp("set atm-clp"),
	set_cos("set cos"),
	set_discard_class("set discard-class"),
	set_ip_dscp("set [ip] dscp"),
	set_fr_de("set fr-de"),
	set_mpls_experimental("set mpls experimental"),
	set_precedence("set precedence"),
	set_qos_group("set qos-group"),
	shape("shape"),
	shape_adaptive("shape adaptive"),
	shape_fecn_adapt("shape fecn-adapt");
	
	private String displayName;

    Action(String displayName) {
        this.displayName = displayName;
    }

    public String displayName() { return displayName; }

    // Optionally and/or additionally, toString.
    @Override public String toString() { return displayName; }
}

