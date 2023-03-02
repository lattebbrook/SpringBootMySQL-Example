package Application;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class Service {
    
    @RequestMapping("/list-all-user")
    Object show(){
        DBConnector db = new DBConnector();
        for(Object element : db.listAllStaff()){
            return element;
        }   
        return null;
    }
  
    
    
}