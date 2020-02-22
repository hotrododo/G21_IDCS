/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSTCopyright.IDCS.model;

import java.sql.Date;

/**
 *
 * @author mac
 */
public class HostModel {

    public HostModel() {
    }

    public HostModel(String ipv4, String ipv6, String dns, String net_name, String country, String oraganization, String asn, Date last_updated) {
        this.ipv4 = ipv4;
        this.ipv6 = ipv6;
        this.dns = dns;
        this.net_name = net_name;
        this.country = country;
        this.oraganization = oraganization;
        this.asn = asn;
        this.last_updated = last_updated;
    }

    public String getIpv4() {
        return ipv4;
    }

    public void setIpv4(String ipv4) {
        this.ipv4 = ipv4;
    }

    public String getIpv6() {
        return ipv6;
    }

    public void setIpv6(String ipv6) {
        this.ipv6 = ipv6;
    }

    public String getDns() {
        return dns;
    }

    public void setDns(String dns) {
        this.dns = dns;
    }

    public String getNet_name() {
        return net_name;
    }

    public void setNet_name(String net_name) {
        this.net_name = net_name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getOraganization() {
        return oraganization;
    }

    public void setOraganization(String oraganization) {
        this.oraganization = oraganization;
    }

    public String getAsn() {
        return asn;
    }

    public void setAsn(String asn) {
        this.asn = asn;
    }

    public Date getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(Date last_updated) {
        this.last_updated = last_updated;
    }
    private String ipv4;
    private String ipv6;
    private String dns;
    private String net_name;
    private String country;
    private String oraganization;
    private String asn;
    private Date last_updated;
}
