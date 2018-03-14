package laboratory.service;


import laboratory.domain.Client;
import laboratory.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

@Service
@Transactional
public class ClientService {

    @Autowired
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    public List<Client> getAllClients() {
       ArrayList<Client> clients = new ArrayList<>();
        clientRepository.findAll()
                .forEach(clients::add);
        return clients;
    }

    public Vector createDataVector(){
        Vector vectorRows = new Vector();
        List<Client> clients = getAllClients();
        for(Client client : clients){
            Vector<Object> row = new Vector<>();
            row.addElement(String.valueOf(client.getClientId()));
            row.addElement(client.getName());
            row.addElement(client.getSurname());
            row.addElement(client.getPesel());
            vectorRows.addElement(row);
        }
        return vectorRows;
    }


    public Client getClient(Long id){
        return clientRepository.findOne(id);
    }

    public Client getClientPesel(String Pesel){
        return clientRepository.findOneByPesel(Pesel);
    }

    public void addClient(Client client){
        clientRepository.save(client);
    }

    public void updateClient(Long id, Client client){
        clientRepository.save(client);
    }

    public void deleteClient(Long id){
        clientRepository.delete(id);
    }

}
