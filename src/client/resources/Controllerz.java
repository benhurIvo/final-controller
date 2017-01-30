/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.resources;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import client.domain.Goal;
import client.domain.Healthprofile;
import client.domain.Person;
import client.domain.Type;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.*;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import org.json.JSONObject;

/**
 *
 * @author benhur
 */
@Stateless // will work only inside a Java EE application
@LocalBean // will work only inside a Java EE application
@Path("/control")
public class Controllerz {

    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    ObjectMapper mapper = new ObjectMapper();
    JSONObject obj;
    String arrayToJson = "";
    static String data = "";
    private static final String targetURL = "https://powerful-refuge-96497.herokuapp.com/sdelab/person";

//Adding a new person, his health profile and types and their values
    @POST
    @Path("/user")
    public String addUser(String dta) {
	System.out.println("hp " + dta);
	try {

	    String[] st1 = dta.split("~~");
	    Person p = mapper.readValue(st1[0], Person.class);

	    //Saving the person
	    obj = new JSONObject(p);
	    String tagt = targetURL;
	    String per = sendurl(tagt, "POST", obj.toString());
	    System.out.println("pers " + per);
	    TypeReference<Person> mapPer = new TypeReference<Person>() {
	    };
	    Person prs = mapper.readValue(per, mapPer);
	    System.out.println("st " + prs.getBirthdate() + " " + prs.getPid() + " " + prs.getFirstname());

//saving the first type ie weight
	    tagt = targetURL + "/typ";
	    String wtt = sendurl(tagt + "/weight", "GET", "");
	    if (wtt.equals("[]") || wtt.equals(null) || wtt.equals("")) {
		JSONObject wt = new JSONObject();
		wt.put("type", "weight");
		wt.put("measure", "kg");
		tagt = targetURL + "/typ";

//Saving the value of the weight
		wtt = sendurl(tagt, "POST", wt.toString());
	    }

//Saving the second type ie height
	    tagt = targetURL + "/typ/height";
	    String htt = sendurl(tagt, "GET", "");
	    if (htt.equals("[]")) {
		JSONObject ht = new JSONObject();
		ht.put("type", "height");
		ht.put("measure", "m");
		tagt = targetURL + "/typ";

//Saving the value of the height
		htt = sendurl(tagt, "POST", ht.toString());
	    }

	    TypeReference<Type> mapType = new TypeReference<Type>() {
	    };
	    Type Htlist = null;
	    Type Wtlist = null;
	    if (htt.contains("[")) {
		Htlist = mapper.readValue(htt.substring(1, htt.length() - 1), mapType);
	    } else {
		Htlist = mapper.readValue(htt, mapType);
	    }
	    if (wtt.contains("[")) {
		Wtlist = mapper.readValue(wtt.substring(1, wtt.length() - 1), mapType);
	    } else {
		Wtlist = mapper.readValue(wtt, mapType);
	    }
	    System.out.println("tag " + Htlist.getTid() + " " + Wtlist.getTid());

	    obj = new JSONObject(st1[2]);
	    System.out.println("sts " + obj.getString("ht"));

//saving the person's health profile, the height and the weight
	    System.out.println("\n----- saving weight-----\n");
	    Healthprofile hp = new Healthprofile();
	    hp.setPid(prs);
	    Date det = new Date();
	    hp.setDatecreated(new SimpleDateFormat("yyyy-MM-dd").format(det));
	    hp.setTid(Wtlist);
	    obj = new JSONObject(st1[1]);
	    System.out.println("sts " + obj.getString("wt"));
	    hp.setValue(obj.getString("wt"));
	    obj = new JSONObject(hp);
	    tagt = targetURL + "/hp";
	    sendurl(tagt, "POST", obj.toString());

	    System.out.println("\n----- saving height-----\n");
	    //height
	    Healthprofile hp1 = new Healthprofile();
	    hp1.setPid(prs);
	    hp1.setDatecreated(new SimpleDateFormat("yyyy-MM-dd").format(det));
	    hp1.setTid(Htlist);
	    obj = new JSONObject(st1[2]);
	    System.out.println("sts " + obj.getString("ht"));
	    hp1.setValue(obj.getString("ht"));
	    obj = new JSONObject(hp1);
	    tagt = targetURL + "/hp";
	    sendurl(tagt, "POST", obj.toString());

	} catch (Exception ex) {
	    ex.printStackTrace();

	}
	return dta;
    }

//Editing a person and his health profile
    @PUT
    @Path("/user")
    public String editUser(String dta) {
	System.out.println("hp put " + dta);
	try {

	    String[] st1 = dta.split("~~");
	    System.out.println("length " + st1.length);
	    Person p = mapper.readValue(st1[0], Person.class);
	    if (st1.length == 1) {
		obj = new JSONObject(p);
		String tagt = targetURL + "/user";
		String per = sendurl(tagt, "PUT", obj.toString());
		System.out.println("pers " + per);
	    } else if (st1.length > 1) {
		String tagt = targetURL + "/typ";
		String wtt = sendurl(tagt + "/weight", "GET", "");
		if (wtt.equals("[]") || wtt.equals(null) || wtt.equals("")) {
		    JSONObject wt = new JSONObject();
		    wt.put("type", "weight");
		    wt.put("measure", "kg");
		    tagt = targetURL + "/typ";
		    wtt = sendurl(tagt, "POST", wt.toString());
		}

		tagt = targetURL + "/typ/height";
		String htt = sendurl(tagt, "GET", "");
		if (htt.equals("[]")) {
		    JSONObject ht = new JSONObject();
		    ht.put("type", "height");
		    ht.put("measure", "m");
		    tagt = targetURL + "/typ";
		    htt = sendurl(tagt, "POST", ht.toString());
		}

		TypeReference<Type> mapType = new TypeReference<Type>() {
		};
		Type Htlist = null;
		Type Wtlist = null;
		if (htt.contains("[")) {
		    Htlist = mapper.readValue(htt.substring(1, htt.length() - 1), mapType);
		} else {
		    Htlist = mapper.readValue(htt, mapType);
		}
		if (wtt.contains("[")) {
		    Wtlist = mapper.readValue(wtt.substring(1, wtt.length() - 1), mapType);
		} else {
		    Wtlist = mapper.readValue(wtt, mapType);
		}
		System.out.println("tag " + Htlist.getTid() + " " + Wtlist.getTid());

		Date det = new Date();

		System.out.println("\n----- saving weight-----\n");
		Healthprofile hp = new Healthprofile();
		hp.setPid(p);
		hp.setDatecreated(new SimpleDateFormat("yyyy-MM-dd").format(det));
		if (st1[1].contains("wt")) {
		    hp.setTid(Wtlist);
		    obj = new JSONObject(st1[1]);
		    System.out.println("sts " + obj.getString("wt"));
		    hp.setValue(obj.getString("wt"));
		} else if (st1[1].contains("ht")) {
		    hp.setTid(Htlist);
		    obj = new JSONObject(st1[1]);
		    System.out.println("sts " + obj.getString("ht"));
		    hp.setValue(obj.getString("ht"));
		}
		obj = new JSONObject(hp);
		tagt = targetURL + "/hp";
		sendurl(tagt, "PUT", obj.toString());

		System.out.println("\n----- saving height-----\n");

		if (st1.length > 2) {

		    Healthprofile hp1 = new Healthprofile();
		    hp1.setPid(p);
		    hp1.setDatecreated(new SimpleDateFormat("yyyy-MM-dd").format(det));
		    if (st1[2].contains("wt")) {
			hp1.setTid(Wtlist);
			obj = new JSONObject(st1[2]);
			System.out.println("sts " + obj.getString("wt"));
			hp1.setValue(obj.getString("wt"));
		    } else if (st1[2].contains("ht")) {
			hp1.setTid(Htlist);
			obj = new JSONObject(st1[2]);
			System.out.println("sts " + obj.getString("ht"));
			hp1.setValue(obj.getString("ht"));
		    }
		    obj = new JSONObject(hp1);
		    tagt = targetURL + "/hp";
		    sendurl(tagt, "PUT", obj.toString());
		}
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();

	}
	return dta;
    }

//Getting h.profile of a given person
    @GET
    @Path("/user_hp/{pid}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public String getPersonhp(@PathParam("pid") int id) {
	String tagt = targetURL + "/hp_detls/" + id;
	String bk1 = sendurl(tagt, "GET", "");
	System.out.println("hpty " + bk1 + " " + id);
	return bk1;
    }

    @GET
    @Path("/authc/{auth}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public String authc(@PathParam("auth") String auth) {

	System.out.println("hpty " + auth);
	return auth;
    }

//History of a person's health profile
    @GET
    @Path("/hp_hist/{pid}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public String getPersonhp_hist(@PathParam("pid") int id) {
	String tagt = targetURL + "/hp_hist/" + id;
	String bk1 = sendurl(tagt, "GET", "");
	System.out.println("hpty " + bk1 + " " + id);
	return bk1;
    }

//Get a list of all users in the system
    @GET
    @Path("/user_all")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public String getPeople() {
	System.out.println(" data got ");
	String tagt = targetURL;
	return sendurl(tagt, "GET", "");
    }

//Deleting a particular person
    @DELETE
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public String delpsn(@PathParam("id") String id) {
	System.out.println(" data del " + id);
	String tagt = targetURL + "/" + id;
	System.out.println("taggget " + tagt);
	return sendurl(tagt, "DELETE", "");
    }

//Deleting a particular health profile
    @DELETE
    @Path("/hp/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public String delhp(@PathParam("id") String id) {
	System.out.println(" data del " + id);
	String tagt = targetURL + "/hp/" + id;
	System.out.println("taggget " + tagt);
	return sendurl(tagt, "DELETE", "");
    }

//Saving a person's goal, method is the same as saving h.profile
    @POST
    @Path("/goal")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void addgoal(String gdt) {
	try {
	    System.out.println(" data got " + gdt);
	    String[] dt = gdt.split("~~");
	    Person p = new Person();
	    p.setPid(Integer.parseInt(dt[0]));

	    String tagt = targetURL + "/typ/running";
	    String htt = sendurl(tagt, "GET", "");
	    if (htt.equals("[]")) {
		JSONObject ht = new JSONObject();
		ht.put("type", "running");
		ht.put("measure", "km");
		tagt = targetURL + "/typ";
		htt = sendurl(tagt, "POST", ht.toString());
	    }

	    TypeReference<Type> mapType = new TypeReference<Type>() {
	    };
	    Type Htlist = null;
	    if (htt.contains("[")) {
		Htlist = mapper.readValue(htt.substring(1, htt.length() - 1), mapType);
	    } else {
		Htlist = mapper.readValue(htt, mapType);
	    }

	    System.out.println("tag " + Htlist.getTid() + " " + Htlist);

	    Goal hp = new Goal();
	    hp.setPid(p);
	    hp.setTid(Htlist);
	    hp.setGoal(new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "~~" + dt[1]);

	    obj = new JSONObject(hp);

	    tagt = targetURL + "/gol";
	    String sty = sendurl(tagt, "POST", obj.toString());
	    System.out.println("return " + sty);
	    //return "";
	} catch (Exception ex) {
	    Logger.getLogger(Controllerz.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

//Getting measure types saved in the system
    @GET
    @Path("/measure")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public String getMeasure() {
	String tagt = targetURL + "/typ";
	String bk1 = sendurl(tagt, "GET", "");
	System.out.println("hpty " + bk1 + " ");
	return bk1;
    }

//Get a person's goals
    @GET
    @Path("/goal/{pid}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public String getPersonGol(@PathParam("pid") int id) {
	String tagt = targetURL + "/gol/" + id;
	String bk1 = sendurl(tagt, "GET", "");
	System.out.println("hpty " + bk1 + " ");
	return bk1;
    }

//Getting distance run by the person
    @GET
    @Path("/miles/{authc}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public String distance(@PathParam("authc") String authc) {
	String ds = "";
	try {

	    String tagt = "https://quiet-taiga-62056.herokuapp.com/sdelab/runkip/authc/" + authc;
	    ds = sendurl(tagt, "GET", "");
	    System.out.println("miles out " + ds);
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
	return ds;
    }

//Getting distance run by the person, call runkeeper service
    @GET
    @Path("/pic/{mls}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public String picture(@PathParam("mls") String mls) {
	String ds = "";
	try {
	    System.out.println("hpty " + mls + " ");
	    String[] dt = mls.split("~~");

	    String tagt = targetURL + "/gol/" + mls;
	    String bk1 = sendurl(tagt, "GET", "");
	    int ii = 0;

	    String tagt2 = "https://hidden-savannah-56220.herokuapp.com/sdelab/flicka/miles/" + bk1 + "~~" + dt[1];
	    ds = sendurl(tagt2, "GET", "");
	    System.out.println("miles out " + ds);
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
	return ds;
    }

//Deleting a particular goal
    @DELETE
    @Path("/goal/{gid}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public String delPersonGol(@PathParam("gid") int id) {
	String tagt = targetURL + "/gol/" + id;
	String bk1 = sendurl(tagt, "DELETE", "");
	System.out.println("hpty " + bk1 + " ");
	return bk1;
    }

//Method to send a url to the server
    String sendurl(String target, String mtd, String input) {
	try {

	    URL targetUrl = new URL(target);

	    HttpURLConnection httpConnection = (HttpURLConnection) targetUrl.openConnection();
	    httpConnection.setDoOutput(true);
	    httpConnection.setRequestMethod(mtd);

	    if (mtd.equals("POST") || mtd.equals("PUT")) {
		httpConnection.setRequestProperty("Content-Type", "application/json");
		OutputStream outputStream = httpConnection.getOutputStream();
		outputStream.write(input.getBytes());
		outputStream.flush();
	    }
	    if (httpConnection.getResponseCode() != 200) {
		throw new RuntimeException("Failed : HTTP error code : "
			+ httpConnection.getResponseCode());
	    }

	    BufferedReader responseBuffer = new BufferedReader(new InputStreamReader(
		    (httpConnection.getInputStream())));

	    String output = "";
	    System.out.println("Output from Server:\n");

	    while ((output = responseBuffer.readLine()) != null) {
		data = output;
	    }
	    System.out.println("datas " + data);
	    httpConnection.disconnect();

	} catch (MalformedURLException e) {

	    e.printStackTrace();

	} catch (IOException e) {

	    e.printStackTrace();

	}
	return data;
    }
}
