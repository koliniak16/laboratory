package laboratory.controller;


import laboratory.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataControllerRest {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping(path="/api/dataInfo",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<String> getClients(){
        return ResponseEntity.ok(String.valueOf(clientRepository.countClients()));
    }

}
