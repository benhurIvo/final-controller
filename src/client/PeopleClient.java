package client;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import final1.ws.People;
import final1.ws.PeopleService;
import final1.ws.Person;
import final1.ws.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author benhur
 */
public class PeopleClient{
    private static final String PEOPLE_XML = "people.xml";
    public static void main(String[] args) throws Exception {
//        PeopleService service = new PeopleService();
//        People people = service.getPeopleImplPort();
//        Person p = people.readPerson(2);
//        List<Person> pList = people.getPeopleList();
//	
//       // System.out.println("Result ==> "+people.readMeasureTypes());
//        System.out.println("Result ==> "+pList);
//        System.out.println("First Person in the list ==> "+pList.get(0).getFirstname());
   PeopleService service = new PeopleService();
  People pService = service.getPeopleImplPort();
//	JAXBContext context = JAXBContext.newInstance(Type.class);
//	    Marshaller m = context.createMarshaller();
//	    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//	    m.marshal(pService.getAllType(), System.out);
//    Map<String, String> map = new HashMap<String, String>();
//
//map.put("first", "First Value");
//
//map.put("second", "Second Value");
//JSONObject obj=new JSONObject(map);
//JSONArray array=new JSONArray(("["+obj.toString()+"]"));
  Person p = new Person();
  p.setFirstname("ivo");
  p.setLastname("kay");
  p.setBirthdate("18-12-88");
  //pService.savePerson(p.toString());
	System.out.println("hehe "+pService.getAllPeople().get(1).getFirstname());
    }
}
