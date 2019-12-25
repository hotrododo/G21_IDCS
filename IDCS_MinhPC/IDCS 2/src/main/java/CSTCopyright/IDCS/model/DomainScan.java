/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSTCopyright.IDCS.model;

/**
 *
 * @author mac
 */
public class DomainScan {
    private String domain;
    private String services;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public DomainScan(String domain, String services) {
        this.domain = domain;
        this.services = services;
    }

    public DomainScan() {
    }
    
}
