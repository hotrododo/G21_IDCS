/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSTCopyright.IDCS.controller;

import CSTCopyright.IDCS.model.UserAccount;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/**
 *
 * @author mac
 */
public class JsonServices {
    public ScanModel ExtractHostInfo(String data, String target, UserAccount user){
        JSONObject jObject = new JSONObject(data);
        ScanModel scan = new ScanModel();
        //get ipv4
        String ipv4 = jObject.getJSONObject("scan").getJSONObject(target).getJSONObject("addresses").getString("ipv4");
        //set ipv4
        scan.setIPv4(ipv4);
        //get DDNS
        String ddns = jObject.getJSONObject("scan").getJSONObject(target).getJSONArray("hostnames").getJSONObject(0).getString("name");
        //set DDNS
        scan.setDDNS(ddns);
        String output = jObject.getJSONObject("scan").getJSONObject(target).getJSONArray("hostscript").getJSONObject(0).getString("output");
        String arr[] = output.split("\\n");
        //get value from json
        String netname = arr[2].split(":")[1];
        String descr = arr[3].split(":")[1];
        String country = arr[4].split(":")[1];
        String person = arr[5].split(":")[1];
        String email = arr[6].split(":")[1];
        long millis=System.currentTimeMillis();
        Date dateScan = new Date(millis);  
        //set value 
        scan.setU_ID(user.getUserName());
        scan.setNETNAME(netname);
        scan.setDESCR(descr);
        scan.setCOUNTRY(country);
        scan.setPERSON(person);
        scan.setEMAIL(email);
        scan.setDATESCAN(dateScan);
        scan.setTARGET(target);
        return scan;
    }
    public List<PortModel> ExtractPortInfo(String data, String target, String sid){
        List<PortModel> list = new ArrayList<>();
        JSONObject jObject = new JSONObject(data);
        JSONObject tcp = jObject.getJSONObject("scan").getJSONObject(target).getJSONObject("tcp");
        for (String port : tcp.keySet()){
            PortModel p = new PortModel();
            String name = tcp.getJSONObject(port).getString("name");
            String version = tcp.getJSONObject(port).getString("version");
            String cpe = tcp.getJSONObject(port).getString("cpe");
            String product = tcp.getJSONObject(port).getString("product");
            String extrainfo = tcp.getJSONObject(port).getString("extrainfo");
            p.setS_ID(sid);
            p.setNAME(name);
            p.setCPE(cpe);
            p.setEXTRALINFO(extrainfo);
            p.setPORTNUM(port);
            p.setVERSION(version);
            p.setPRODUCT(product);
            list.add(p);
        }
        return list;
    }
    public ServiceModel ExtractServiceInfo(String data, String target, String port, String sid){
        ServiceModel serv = new ServiceModel();
        JSONObject jObject = new JSONObject(data);
        JSONObject script = jObject.getJSONObject("scan").getJSONObject(target).getJSONObject("tcp").getJSONObject(port).getJSONObject("script");
        
        String scpt = script.getString(script.keySet().iterator().next());
        serv.setS_ID(sid);
        serv.setPORTNUM(port);
        serv.setSCRIPT(scpt);
        return serv;
    }
}
